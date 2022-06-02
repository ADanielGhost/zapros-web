package org.polytech.zaprosweb.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.polytech.zapros.bean.Alternative;
import org.polytech.zapros.bean.Assessment;
import org.polytech.zaprosweb.dao.repository.AlternativeRepository;
import org.polytech.zaprosweb.dao.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlternativeDAO {

    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired private AssessmentDAO assessmentDAO;
    @Autowired private AlternativeRepository alternativeRepository;

    public void addAlternatives(AlternativePackageEntity alternativePackageEntity, List<AssessmentEntity> assessmentEntityList, List<Alternative> alternativeList) {

        List<AlternativeEntity> alternativeEntityList = alternativeList.stream()
            .map(alternative -> {
                AlternativeEntity entity = new AlternativeEntity();
                entity.setName(alternative.getName());
                entity.setAlternativePackage(alternativePackageEntity);

                List<String> assessmentsNames = alternative.getAssessments().stream()
                    .map(Assessment::getName)
                    .collect(Collectors.toList());

                List<AssessmentEntity> collect = assessmentEntityList.stream()
                    .filter(x -> assessmentsNames.contains(x.getName()))
                    .collect(Collectors.toList());

                entity.getAssessmentSet().addAll(collect);
                return entity;
            }).collect(Collectors.toList());

        alternativeRepository.saveAll(alternativeEntityList);
    }
}
