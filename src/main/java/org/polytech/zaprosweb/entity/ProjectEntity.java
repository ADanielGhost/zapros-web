package org.polytech.zaprosweb.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "Projects")
public class ProjectEntity {
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
}
