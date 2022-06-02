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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.polytech.zapros.bean.Assessment;
import org.polytech.zapros.bean.Criteria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "criteria")
public class CriteriaEntity implements IEntity<Criteria> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int orderId;

    @OneToMany(mappedBy = "criteria")
    @ToString.Exclude
    private Set<AssessmentEntity> assessmentSet = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    @Override
    public Criteria toModel() {
        List<Assessment> assessments = assessmentSet.stream()
            .map(AssessmentEntity::toModel)
            .sorted(Comparator.comparing(Assessment::getOrderId))
            .collect(Collectors.toList());

        return new Criteria(id, name, assessments, orderId);
    }
}
