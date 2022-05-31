package org.polytech.zaprosweb.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.polytech.zaprosweb.bean.Criteria;
import org.polytech.zaprosweb.dao.repository.CriteriaRepository;
import org.polytech.zaprosweb.entity.*;
import org.polytech.zaprosweb.util.IterableListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CriteriaDAO {

    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired private CriteriaRepository criteriaRepository;
    @Autowired private AssessmentDAO assessmentDAO;

    public void addCriteriaList(ProjectEntity projectEntity, List<Criteria> criteriaList) {
        criteriaList.forEach(criteria -> {
            CriteriaEntity entity = new CriteriaEntity();
            entity.setName(criteria.getName());
            entity.setProject(projectEntity);

            CriteriaEntity criteriaEntity = criteriaRepository.save(entity);
            assessmentDAO.addAssessments(criteriaEntity, criteria.getAssessmentList());
        });
    }
}
