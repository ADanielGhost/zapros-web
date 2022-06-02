package org.polytech.zaprosweb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.polytech.zapros.VdaZaprosFactory;
import org.polytech.zapros.bean.Answer;
import org.polytech.zapros.bean.AnswerCheckResult;
import org.polytech.zapros.bean.MethodType;
import org.polytech.zapros.service.main.VdaZaprosService;
import org.polytech.zaprosweb.bean.AlternativePackage;
import org.polytech.zaprosweb.bean.Project;
import org.polytech.zaprosweb.bean.User;
import org.polytech.zaprosweb.dao.ProjectDAO;
import org.polytech.zaprosweb.dao.UserDAO;
import org.polytech.zaprosweb.dao.entity.UserEntity;
import org.polytech.zaprosweb.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZaprosService {

    @Autowired private UserDAO userDAO;
    @Autowired private ProjectDAO projectDAO;

    private Map<MethodType, VdaZaprosService> vdaZaprosServiceMap;

    @PostConstruct
    private void init() {
        vdaZaprosServiceMap = new HashMap<>();
        vdaZaprosServiceMap.put(MethodType.ZAPROS_II, VdaZaprosFactory.getService(MethodType.ZAPROS_II));
        vdaZaprosServiceMap.put(MethodType.ARACE, VdaZaprosFactory.getService(MethodType.ARACE));
        vdaZaprosServiceMap.put(MethodType.ZAPROS_III, VdaZaprosFactory.getService(MethodType.ZAPROS_III));
        vdaZaprosServiceMap.put(MethodType.ARACE_QV, VdaZaprosFactory.getService(MethodType.ARACE_QV));
    }

    public AnswerCheckResult askFirst(Long userId) throws UserNotFoundException {
        UserEntity userEntity = identifyUser(userId);

        User user = userEntity.toModel();
        Project project = identifyProject(userEntity);

        MethodType methodType = user.getMethodType();
        return vdaZaprosServiceMap.get(methodType).askFirst(project.getCriteriaList());
    }

    public AnswerCheckResult addAnswer(Long userId, AnswerCheckResult answerCheckResult, Answer.AnswerType answerType) throws UserNotFoundException {
        User user = identifyUser(userId).toModel();

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

    public void sendAnswers(Long userId, List<Answer> answers) {
        answers.forEach(System.out::println);
    }
}
