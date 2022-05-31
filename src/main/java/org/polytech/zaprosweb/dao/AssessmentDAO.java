package org.polytech.zaprosweb.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.polytech.zaprosweb.bean.Assessment;
import org.polytech.zaprosweb.dao.repository.AssessmentRepository;
import org.polytech.zaprosweb.entity.AssessmentEntity;
import org.polytech.zaprosweb.entity.CriteriaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AssessmentDAO {

    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired private AssessmentRepository assessmentRepository;

    public void addAssessments(CriteriaEntity criteriaEntity, List<Assessment> assessmentList) {
        List<AssessmentEntity> entities = assessmentList.stream()
            .map(assessment -> {
                AssessmentEntity assessmentEntity = new AssessmentEntity();
                assessmentEntity.setName(assessment.getName());
                assessmentEntity.setRank(assessment.getRank());
                assessmentEntity.setCriteria(criteriaEntity);
                return assessmentEntity;
            }).collect(Collectors.toList());

        assessmentRepository.saveAll(entities);
    }
}
