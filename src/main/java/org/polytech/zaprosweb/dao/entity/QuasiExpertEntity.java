package org.polytech.zaprosweb.dao.entity;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.polytech.zapros.bean.Assessment;
import org.polytech.zapros.bean.QuasiExpert;
import org.polytech.zaprosweb.dao.converter.BinaryMatrix2StringConverter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "quasiExperts")
public class QuasiExpertEntity implements IEntity<QuasiExpert> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @Convert(converter = BinaryMatrix2StringConverter.class)
    private int[][] matrix;

    @OneToOne
    @JoinColumn(name = "first_answer_id")
    private AnswerEntity firstAnswer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "quasiExpert")
    @ToString.Exclude
    private Set<RankEntity> ranks;

    @Override
    public QuasiExpert toModel() {
        QuasiExpert quasiExpert = new QuasiExpert();
        quasiExpert.setId(id);
        quasiExpert.setMatrix(matrix);
        if (firstAnswer != null) {
            quasiExpert.setFirstAnswer(firstAnswer.toModel());
        }

        Map<Assessment, Integer> collect = ranks.stream()
            .collect(Collectors.toMap(
                x -> x.getAssessment().toModel(),
                RankEntity::getValue)
            );

        quasiExpert.setRanks(collect);
        return quasiExpert;
    }
}
