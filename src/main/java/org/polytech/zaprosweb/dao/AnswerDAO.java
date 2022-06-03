package org.polytech.zaprosweb.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.polytech.zapros.bean.Answer;
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
}
