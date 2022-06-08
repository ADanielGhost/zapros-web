package org.polytech.zaprosweb.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.polytech.zapros.bean.alternative.AlternativeRankingResult;
import org.polytech.zapros.bean.alternative.AlternativeResult;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AlternativeWebRankingResult {
    private long id;
    private List<AlternativeWebResult> alternativeResults;
    private long nanoTime;
    private List<Integer> countCompareBetter;
    private List<Integer> countCompareWorse;
    private List<Integer> countCompareEqual;
    private List<Integer> countCompareNotComparable;

    public static AlternativeWebRankingResult of(AlternativeRankingResult data) {
        AlternativeWebRankingResult result = new AlternativeWebRankingResult();

        result.alternativeResults = data.getAlternativeResults().stream()
            .map(x -> {
                AlternativeWebResult alternativeWebResult = new AlternativeWebResult();
                alternativeWebResult.setId(x.getId());
                alternativeWebResult.setAlternative(x.getAlternative());
                alternativeWebResult.setFinalRank(x.getFinalRank());
                return alternativeWebResult;
            }).collect(Collectors.toList());

        result.nanoTime = data.getNanoTime();
        result.countCompareBetter = new ArrayList<>();
        result.countCompareWorse = new ArrayList<>();
        result.countCompareEqual = new ArrayList<>();
        result.countCompareNotComparable = new ArrayList<>();

        data.getMapCompare().forEach((qe, map) -> {
            final int[] countCompareBetter = {0};
            final int[] countCompareWorse = {0};
            final int[] countCompareEqual = {0};
            final int[] countCompareNotComparable = {0};

            map.forEach((pair, type) -> {
                switch (type) {
                    case BETTER: countCompareBetter[0]++; break;
                    case WORSE: countCompareWorse[0]++; break;
                    case EQUAL: countCompareEqual[0]++; break;
                    case NOT_COMPARABLE: countCompareNotComparable[0]++; break;
                }
            });

            result.countCompareBetter.add(countCompareBetter[0]);
            result.countCompareWorse.add(countCompareWorse[0]);
            result.countCompareEqual.add(countCompareEqual[0]);
            result.countCompareNotComparable.add(countCompareNotComparable[0]);
        });

        return result;
    }
}
