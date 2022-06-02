package org.polytech.zaprosweb.dao;

import java.util.Comparator;
import java.util.List;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.polytech.zapros.bean.Criteria;
import org.polytech.zaprosweb.dao.repository.CriteriaRepository;
import org.polytech.zaprosweb.dao.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CriteriaDAO {

    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired private CriteriaRepository criteriaRepository;
    @Autowired private AssessmentDAO assessmentDAO;

    public void addCriteriaList(ProjectEntity projectEntity, List<Criteria> criteriaList) {
        int current = 0;

        for (Criteria criteria: criteriaList) {
            CriteriaEntity entity = new CriteriaEntity();
            entity.setName(criteria.getName());
            entity.setOrderId(current++);
            entity.setProject(projectEntity);

            CriteriaEntity criteriaEntity = criteriaRepository.save(entity);
            assessmentDAO.addAssessments(criteriaEntity, criteria.getAssessments());
        }
    }
}
