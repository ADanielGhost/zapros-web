package org.polytech.zaprosweb.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.polytech.zaprosweb.bean.AlternativePackage;
import org.polytech.zaprosweb.dao.repository.AlternativePackageRepository;
import org.polytech.zaprosweb.dao.entity.*;
import org.polytech.zaprosweb.exception.AlternativePackageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlternativePackageDAO {

    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired private AlternativePackageRepository alternativePackageRepository;
    @Autowired private AlternativeDAO alternativeDAO;

    public void addAlternativePackage(ProjectEntity projectEntity, AlternativePackage alternativePackage) {
        AlternativePackageEntity entity = new AlternativePackageEntity();
        entity.setName(alternativePackage.getName());
        entity.setProject(projectEntity);
        AlternativePackageEntity alternativePackageEntity = alternativePackageRepository.save(entity);

        List<AssessmentEntity> projectsAssessments = projectEntity.getCriteriaSet().stream()
            .map(CriteriaEntity::getAssessmentSet)
            .flatMap(Set::stream)
            .collect(Collectors.toList());

        alternativeDAO.addAlternatives(alternativePackageEntity, projectsAssessments, alternativePackage.getAlternatives());
    }

    public AlternativePackageEntity getAlternativePackage(Long alternativePackageId) throws AlternativePackageNotFoundException {
        return alternativePackageRepository
            .findById(alternativePackageId)
            .orElseThrow(AlternativePackageNotFoundException::new);
    }
}
