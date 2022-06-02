package org.polytech.zaprosweb.dao.entity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.polytech.zapros.bean.Criteria;
import org.polytech.zaprosweb.bean.AlternativePackage;
import org.polytech.zaprosweb.bean.Project;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "projects")
public class ProjectEntity implements IEntity<Project> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "project")
    @ToString.Exclude
    private Set<CriteriaEntity> criteriaSet = new HashSet<>();

    @OneToMany(mappedBy = "project")
    @ToString.Exclude
    private Set<AlternativePackageEntity> alternativePackageSet = new HashSet<>();

    @OneToOne(mappedBy = "project")
    private QuasiExpertConfigEntity quasiExpertConfig;

    @Override
    public Project toModel() {
        List<Criteria> criteriaList = criteriaSet.stream()
            .map(CriteriaEntity::toModel)
            .sorted(Comparator.comparingInt(Criteria::getOrderId))
            .collect(Collectors.toList());

        List<AlternativePackage> alternativePackages = alternativePackageSet.stream()
            .map(AlternativePackageEntity::toModel)
            .collect(Collectors.toList());

        return new Project(id, name, criteriaList, quasiExpertConfig.toModel(), alternativePackages);
    }
}
