package org.polytech.zaprosweb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polytech.zapros.VdaZaprosFactory;
import org.polytech.zapros.bean.Answer;
import org.polytech.zapros.bean.AnswerCheckResult;
import org.polytech.zapros.bean.BuildingQesCheckResult;
import org.polytech.zapros.bean.Criteria;
import org.polytech.zapros.bean.MethodType;
import org.polytech.zapros.bean.QuasiExpert;
import org.polytech.zapros.bean.QuasiExpertConfig;
import org.polytech.zapros.bean.ReplacedAnswer;
import org.polytech.zapros.bean.alternative.AlternativeResult;
import org.polytech.zapros.service.main.VdaZaprosService;
import org.polytech.zaprosweb.bean.AlternativePackage;
import org.polytech.zaprosweb.bean.FullAlternativeResult;
import org.polytech.zaprosweb.bean.Project;
import org.polytech.zaprosweb.bean.User;
import org.polytech.zaprosweb.dao.AnswerDAO;
import org.polytech.zaprosweb.dao.ProjectDAO;
import org.polytech.zaprosweb.dao.QuasiExpertDAO;
import org.polytech.zaprosweb.dao.UserDAO;
import org.polytech.zaprosweb.dao.entity.QuasiExpertEntity;
import org.polytech.zaprosweb.dao.entity.UserEntity;
import org.polytech.zaprosweb.exception.AnswersAlreadyExistsException;
import org.polytech.zaprosweb.exception.QesAlreadyExistsException;
import org.polytech.zaprosweb.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZaprosService {

    @Autowired private UserDAO userDAO;
    @Autowired private ProjectDAO projectDAO;
    @Autowired private AnswerDAO answerDAO;
    @Autowired private QuasiExpertDAO quasiExpertDAO;

    private final Log log = LogFactory.getLog(this.getClass());

    private Map<MethodType, VdaZaprosService> vdaZaprosServiceMap;

    @PostConstruct
    private void init() {
        vdaZaprosServiceMap = new HashMap<>();
        vdaZaprosServiceMap.put(MethodType.ZAPROS_II, VdaZaprosFactory.getService(MethodType.ZAPROS_II));
        vdaZaprosServiceMap.put(MethodType.ARACE, VdaZaprosFactory.getService(MethodType.ARACE));
        vdaZaprosServiceMap.put(MethodType.ZAPROS_III, VdaZaprosFactory.getService(MethodType.ZAPROS_III));
        vdaZaprosServiceMap.put(MethodType.ARACE_QV, VdaZaprosFactory.getService(MethodType.ARACE_QV));
    }

    public AnswerCheckResult askFirst(Long userId) throws UserNotFoundException, AnswersAlreadyExistsException {
        UserEntity user = identifyUser(userId);
        if (answerDAO.answersAlreadyExists(userId)) {
            throw new AnswersAlreadyExistsException("there are already exists answers for userId " + userId);
        }

        Project project = identifyProject(user);

        MethodType methodType = user.getMethodType();
        return vdaZaprosServiceMap.get(methodType).askFirst(project.getCriteriaList());
    }

    public AnswerCheckResult addAnswer(Long userId, AnswerCheckResult answerCheckResult, Answer.AnswerType answerType) throws UserNotFoundException {
        UserEntity user = identifyUser(userId);

        MethodType methodType = user.getMethodType();
        return vdaZaprosServiceMap.get(methodType).addAnswer(answerCheckResult, answerType);
    }

    private UserEntity identifyUser(Long userId) throws UserNotFoundException {
        return userDAO.getUserById(userId);
    }

    private AlternativePackage identifyAlternativePackage(UserEntity user) {
        return user.getAlternativePackage().toModel();
    }

    private Project identifyProject(UserEntity user) {
        return user.getAlternativePackage().getProject().toModel();
    }

    public void sendAnswers(Long userId, List<Answer> answers) throws UserNotFoundException {
        UserEntity userEntity = identifyUser(userId);
        answerDAO.sendAnswers(userEntity, answers);
    }

    public BuildingQesCheckResult checkValid(Long userId) throws UserNotFoundException, QesAlreadyExistsException {
        UserEntity userEntity = identifyUser(userId);
        User user = userEntity.toModel();
        Project project = identifyProject(userEntity);

        return buildQesWrapper(
            userEntity,
            user.getAnswers(),
            project.getQuasiExpertConfig(),
            project.getCriteriaList(),
            user.getThreshold()
        );
    }

    private BuildingQesCheckResult buildQesWrapper(UserEntity userEntity, List<Answer> answers, QuasiExpertConfig config, List<Criteria> criteriaList, Double threshold) throws QesAlreadyExistsException {
        BuildingQesCheckResult buildingQesCheckResult = vdaZaprosServiceMap.get(userEntity.getMethodType()).buildQes(
            answers, config, criteriaList, threshold
        );

        if (buildingQesCheckResult.isOver()) {
            if (quasiExpertDAO.qesAlreadyExists(userEntity.getId())) {
                throw new QesAlreadyExistsException("there are already exists qes for userId " + userEntity.getId());
            }
            quasiExpertDAO.saveQes(userEntity, buildingQesCheckResult.getQes());
        }

        return buildingQesCheckResult;
    }

    public BuildingQesCheckResult replaceAnswer(Long userId, BuildingQesCheckResult buildingQesCheckResult, Answer.AnswerType answerType) throws UserNotFoundException, QesAlreadyExistsException {
        UserEntity userEntity = identifyUser(userId);
        User user = userEntity.toModel();
        Project project = identifyProject(userEntity);
        MethodType methodType = user.getMethodType();

        ReplacedAnswer replacedAnswer = vdaZaprosServiceMap.get(methodType).replaceAnswer(buildingQesCheckResult, answerType);
        answerDAO.replaceAnswer(replacedAnswer.getReplacedAnswer().getId(), replacedAnswer.getNewAnswer());

        return buildQesWrapper(
            userEntity,
            replacedAnswer.getNewAnswers(),
            project.getQuasiExpertConfig(),
            project.getCriteriaList(),
            user.getThreshold()
        );
    }

    public FullAlternativeResult rankAlternatives(Long userId) throws UserNotFoundException {
        UserEntity userEntity = identifyUser(userId);
        Project project = identifyProject(userEntity);
        AlternativePackage alternativePackage = identifyAlternativePackage(userEntity);
        User user = userEntity.toModel();
        MethodType methodType = user.getMethodType();

        List<QuasiExpert> quasiExperts = userEntity.getQuasiExpertSet().stream()
            .map(QuasiExpertEntity::toModel)
            .collect(Collectors.toList());

        if (user.getMethodType() == MethodType.ZAPROS_II || user.getMethodType() == MethodType.ZAPROS_III) {
            List<? extends AlternativeResult> resultsZaprosII = vdaZaprosServiceMap.get(MethodType.ZAPROS_II).rankAlternatives(
                quasiExperts, alternativePackage.getAlternatives(),
                project.getCriteriaList(), project.getQuasiExpertConfig()
            );

            List<? extends AlternativeResult> resultsZaprosIII = vdaZaprosServiceMap.get(MethodType.ZAPROS_III).rankAlternatives(
                quasiExperts, alternativePackage.getAlternatives(),
                project.getCriteriaList(), project.getQuasiExpertConfig()
            );

            return new FullAlternativeResult(resultsZaprosII, resultsZaprosIII);
        }

        // ARACE and ARACE_QV
        List<? extends AlternativeResult> resultsArace = vdaZaprosServiceMap.get(MethodType.ARACE).rankAlternatives(
            quasiExperts, alternativePackage.getAlternatives(),
            project.getCriteriaList(), project.getQuasiExpertConfig()
        );

        List<? extends AlternativeResult> resultsAraceQV = vdaZaprosServiceMap.get(MethodType.ARACE_QV).rankAlternatives(
            quasiExperts, alternativePackage.getAlternatives(),
            project.getCriteriaList(), project.getQuasiExpertConfig()
        );

        return new FullAlternativeResult(resultsArace, resultsAraceQV);
    }

    public QuasiExpert getQes(Long quasiExpertId) {
        return quasiExpertDAO.getQuasiExpertById(quasiExpertId);
    }
}
