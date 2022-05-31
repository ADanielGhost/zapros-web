package org.polytech.zaprosweb.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.polytech.zaprosweb.bean.Alternative;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Alternatives")
public class AlternativeEntity {
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
}
