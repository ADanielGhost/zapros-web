package org.polytech.zaprosweb.bean;

import java.util.List;
import java.util.stream.Collectors;

import org.polytech.zaprosweb.entity.ProjectEntity;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Project {
    private long id;
    private String name;
    private List<Criteria> criteriaList;
    private List<AlternativePackage> alternativePackageList;

    private Project() { }

    public static Project of(ProjectEntity projectEntity) {
        Project project = new Project();
        project.id = projectEntity.getId();
        project.name = projectEntity.getName();

        project.criteriaList = projectEntity.getCriteriaSet()
            .stream()
            .map(Criteria::of)
            .collect(Collectors.toList());

        project.alternativePackageList = projectEntity.getAlternativePackageSet()
            .stream()
            .map(AlternativePackage::of)
            .collect(Collectors.toList());

        return project;
    }
}
