package org.polytech.zaprosweb.dao;

import org.polytech.zaprosweb.bean.FullAlternativeResult;
import org.polytech.zaprosweb.bean.RankType;
import org.polytech.zaprosweb.dao.entity.FullAlternativeResultEntity;
import org.polytech.zaprosweb.dao.entity.UserEntity;
import org.polytech.zaprosweb.dao.repository.FullAlternativeResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FullAlternativeResultDAO {

    @Autowired private FullAlternativeResultRepository fullAlternativeResultRepository;
    @Autowired private AlternativeWebRankingResultDAO alternativeWebRankingResultDAO;

    public void saveFullAlternativeResults(UserEntity userEntity, FullAlternativeResult fullAlternativeResult) {
        FullAlternativeResultEntity entity = new FullAlternativeResultEntity();
        entity.setUser(userEntity);

        FullAlternativeResultEntity save = fullAlternativeResultRepository.save(entity);
        alternativeWebRankingResultDAO.saveAlternativeWebRankingResult(save, fullAlternativeResult.getRankOrderResult(), RankType.ORDER);
        alternativeWebRankingResultDAO.saveAlternativeWebRankingResult(save, fullAlternativeResult.getRankQVResult(), RankType.QV);
    }
}
