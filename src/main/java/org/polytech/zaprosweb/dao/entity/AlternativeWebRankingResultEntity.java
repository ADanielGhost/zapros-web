package org.polytech.zaprosweb.dao.entity;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
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

import org.polytech.zapros.bean.alternative.AlternativeResult;
import org.polytech.zaprosweb.bean.AlternativeWebRankingResult;
import org.polytech.zaprosweb.bean.AlternativeWebResult;
import org.polytech.zaprosweb.bean.RankType;
import org.polytech.zaprosweb.dao.converter.ListInteger2StringConverter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AlternativeWebRankingResultEntity implements IEntity<AlternativeWebRankingResult> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "alternativeWebRankingResult")
    private Set<AlternativeWebResultEntity> alternativeWebResultEntitySet = new HashSet<>();

    @Column(nullable = false)
    private RankType rankType;

    @Column(nullable = false)
    private long nanoTime;

    @Column(nullable = false)
    @Convert(converter = ListInteger2StringConverter.class)
    private List<Integer> countCompareBetter;

    @Column(nullable = false)
    @Convert(converter = ListInteger2StringConverter.class)
    private List<Integer> countCompareWorse;

    @Column(nullable = false)
    @Convert(converter = ListInteger2StringConverter.class)
    private List<Integer> countCompareEqual;

    @Column(nullable = false)
    @Convert(converter = ListInteger2StringConverter.class)
    private List<Integer> countCompareNotComparable;

    @ManyToOne
    @JoinColumn(name = "full_alternative_result_id", nullable = false)
    private FullAlternativeResultEntity fullAlternativeResult;

    @Override
    public AlternativeWebRankingResult toModel() {
        AlternativeWebRankingResult alternativeWebRankingResult = new AlternativeWebRankingResult();
        alternativeWebRankingResult.setId(id);

        List<AlternativeWebResult> collect = alternativeWebResultEntitySet.stream()
            .map(AlternativeWebResultEntity::toModel)
            .sorted(Comparator.comparing(AlternativeResult::getFinalRank))
            .collect(Collectors.toList());
        alternativeWebRankingResult.setAlternativeResults(collect);

        alternativeWebRankingResult.setNanoTime(nanoTime);
        alternativeWebRankingResult.setCountCompareBetter(countCompareBetter);
        alternativeWebRankingResult.setCountCompareWorse(countCompareWorse);
        alternativeWebRankingResult.setCountCompareEqual(countCompareEqual);
        alternativeWebRankingResult.setCountCompareNotComparable(countCompareNotComparable);

        return alternativeWebRankingResult;
    }
}
