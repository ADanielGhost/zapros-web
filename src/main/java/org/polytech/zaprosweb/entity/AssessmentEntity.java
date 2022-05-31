package org.polytech.zaprosweb.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.polytech.zaprosweb.bean.Assessment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "Assessments")
public class AssessmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int rank;

    @ManyToOne
    @JoinColumn(name = "criteria_id", nullable = false)
    private CriteriaEntity criteria;

    @ManyToMany(mappedBy = "assessmentSet")
    @ToString.Exclude
    private Set<AlternativeEntity> alternative;
}
