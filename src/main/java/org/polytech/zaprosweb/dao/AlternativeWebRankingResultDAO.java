package org.polytech.zaprosweb.dao;

import org.polytech.zaprosweb.bean.AlternativeWebRankingResult;
import org.polytech.zaprosweb.bean.RankType;
import org.polytech.zaprosweb.dao.entity.AlternativeWebRankingResultEntity;
import org.polytech.zaprosweb.dao.entity.FullAlternativeResultEntity;
import org.polytech.zaprosweb.dao.repository.AlternativeWebRankingResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlternativeWebRankingResultDAO {

    @Autowired private AlternativeWebRankingResultRepository alternativeWebRankingResultRepository;
    @Autowired private AlternativeWebResultDAO alternativeWebResultDAO;

    public void saveAlternativeWebRankingResult(FullAlternativeResultEntity fullAlternativeResultEntity, AlternativeWebRankingResult rankResult, RankType rankType) {
        AlternativeWebRankingResultEntity entity = new AlternativeWebRankingResultEntity();
        entity.setFullAlternativeResult(fullAlternativeResultEntity);

        entity.setNanoTime(rankResult.getNanoTime());
        entity.setRankType(rankType);
        entity.setCountCompareBetter(rankResult.getCountCompareBetter());
        entity.setCountCompareWorse(rankResult.getCountCompareWorse());
        entity.setCountCompareEqual(rankResult.getCountCompareEqual());
        entity.setCountCompareNotComparable(rankResult.getCountCompareNotComparable());

        AlternativeWebRankingResultEntity save = alternativeWebRankingResultRepository.save(entity);
        alternativeWebResultDAO.saveAlternativesWebResults(save, rankResult.getAlternativeResults());
    }
}
