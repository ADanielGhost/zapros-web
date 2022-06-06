package org.polytech.zaprosweb.dao.entity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.polytech.zapros.bean.Alternative;
import org.polytech.zapros.bean.Assessment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "alternatives")
public class AlternativeEntity implements IEntity<Alternative> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(
        name = "alternativesAssessments",
        joinColumns = @JoinColumn(name = "alternative_id"),
        inverseJoinColumns = @JoinColumn(name = "assessmentId")
    )
    @ToString.Exclude
    private Set<AssessmentEntity> assessmentSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "alternative_package_id", nullable = false)
    private AlternativePackageEntity alternativePackage;

    @Override
    public Alternative toModel() {
        List<Assessment> assessments = assessmentSet.stream()
            .map(AssessmentEntity::toModel)
            .sorted(Comparator.comparing(Assessment::getOrderId))
            .collect(Collectors.toList());

        return new Alternative(id, name, assessments);
    }
}
