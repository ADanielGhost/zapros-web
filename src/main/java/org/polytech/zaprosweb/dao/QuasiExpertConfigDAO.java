package org.polytech.zaprosweb.dao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.polytech.zapros.VdaZaprosFactory;
import org.polytech.zapros.bean.Assessment;
import org.polytech.zapros.bean.Criteria;
import org.polytech.zapros.bean.QuasiExpertConfig;
import org.polytech.zaprosweb.dao.entity.ProjectEntity;
import org.polytech.zaprosweb.dao.entity.QuasiExpertConfigEntity;
import org.polytech.zaprosweb.dao.repository.QuasiExpertConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuasiExpertConfigDAO {
    @Autowired private QuasiExpertConfigRepository quasiExpertConfigRepository;

    public void addConfig(ProjectEntity projectEntity, List<Criteria> criteriaList) {
        QuasiExpertConfig config = VdaZaprosFactory.getConfig(getSortedCriteriaList(criteriaList));
        QuasiExpertConfigEntity entity = new QuasiExpertConfigEntity();

        entity.setLen(config.getLen());
        entity.setIndexes(config.getIndexes());
        entity.setInitData(config.getInitData());
        entity.setProject(projectEntity);

        quasiExpertConfigRepository.save(entity);
    }
    private List<Criteria> getSortedCriteriaList(List<Criteria> criteriaList) {
        return criteriaList.stream()
            .peek(criteria -> {
                List<Assessment> assessments = criteria.getAssessments().stream()
                    .sorted(Comparator.comparing(Assessment::getOrderId))
                    .collect(Collectors.toList());
                criteria.setAssessments(assessments);
            })
            .sorted(Comparator.comparing(Criteria::getOrderId))
            .collect(Collectors.toList());
    }
}
