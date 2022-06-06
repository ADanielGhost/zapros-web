package org.polytech.zaprosweb.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.polytech.zapros.bean.Answer;
import org.polytech.zapros.bean.ReplacedAnswer;
import org.polytech.zaprosweb.dao.entity.AnswerEntity;
import org.polytech.zaprosweb.dao.entity.AssessmentEntity;
import org.polytech.zaprosweb.dao.entity.UserEntity;
import org.polytech.zaprosweb.dao.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerDAO {

    @Autowired private AnswerRepository answerRepository;
    @Autowired private AssessmentDAO assessmentDAO;

    public boolean answersAlreadyExists(Long userId) {
        return answerRepository.findByUserId(userId).size() != 0;
    }

    public void sendAnswers(UserEntity userEntity, List<Answer> answers) {
        List<AnswerEntity> entities = answers.stream()
            .map(answer -> {
                AnswerEntity entity = new AnswerEntity();

                AssessmentEntity i = assessmentDAO.getAssessmentById(answer.getI().getId());
                AssessmentEntity j = assessmentDAO.getAssessmentById(answer.getJ().getId());

                entity.setI(i);
                entity.setJ(j);
                entity.setAnswerType(answer.getAnswerType());
                entity.setAnswerAuthor(answer.getAnswerAuthor());
                entity.setUser(userEntity);

                return entity;
            }).collect(Collectors.toList());

        answerRepository.saveAll(entities);
    }

    public void replaceAnswer(long id, Answer newAnswer) {
        AnswerEntity entity = answerRepository.findById(id).orElseThrow(IllegalStateException::new);

        entity.setAnswerType(newAnswer.getAnswerType());
        entity.setAnswerAuthor(newAnswer.getAnswerAuthor());

        answerRepository.save(entity);
    }
}
