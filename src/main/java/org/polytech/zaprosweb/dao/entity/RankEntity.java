package org.polytech.zaprosweb.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "ranks")
public class RankEntity implements IEntity<RankEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "assessment_id", nullable = false)
    private AssessmentEntity assessment;

    @ManyToOne
    @JoinColumn(name = "quasi_expert_id", nullable = false)
    private QuasiExpertEntity quasiExpert;

    @Column(nullable = false)
    private int value;

    @Override
    public RankEntity toModel() {
        throw new IllegalStateException();
    }
}
