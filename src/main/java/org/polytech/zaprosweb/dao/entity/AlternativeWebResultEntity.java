package org.polytech.zaprosweb.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.polytech.zapros.bean.Alternative;
import org.polytech.zaprosweb.bean.AlternativeWebResult;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AlternativeWebResultEntity implements IEntity<AlternativeWebResult> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "alternative_id", nullable = false)
    private AlternativeEntity alternative;

    @Column(nullable = false)
    private int finalRank;

    @ManyToOne
    @JoinColumn(name = "alternative_web_ranking_result_id")
    private AlternativeWebRankingResultEntity alternativeWebRankingResult;

    @Override
    public AlternativeWebResult toModel() {
        AlternativeWebResult alternativeWebResult = new AlternativeWebResult();
        alternativeWebResult.setId(id);
        alternativeWebResult.setAlternative(alternative.toModel());
        alternativeWebResult.setFinalRank(finalRank);
        return alternativeWebResult;
    }
}
