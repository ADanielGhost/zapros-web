package org.polytech.zaprosweb.dao;

import java.util.List;

import org.polytech.zapros.bean.Alternative;
import org.polytech.zaprosweb.bean.AlternativeWebResult;
import org.polytech.zaprosweb.dao.entity.AlternativeWebRankingResultEntity;
import org.polytech.zaprosweb.dao.entity.AlternativeWebResultEntity;
import org.polytech.zaprosweb.dao.repository.AlternativeWebResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlternativeWebResultDAO {

    @Autowired private AlternativeWebResultRepository alternativeWebResultRepository;
    @Autowired private AlternativeDAO alternativeDAO;

    public void saveAlternativesWebResults(AlternativeWebRankingResultEntity save, List<AlternativeWebResult> alternativeResults) {
        alternativeResults.forEach(alternativeResult -> {
            AlternativeWebResultEntity entity = new AlternativeWebResultEntity();
            entity.setAlternative(alternativeDAO.getAlternativeById(alternativeResult.getAlternative().getId()));
            entity.setFinalRank(alternativeResult.getFinalRank());
            entity.setAlternativeWebRankingResult(save);

            alternativeWebResultRepository.save(entity);
        });
    }
}
