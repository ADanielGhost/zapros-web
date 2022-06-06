package org.polytech.zaprosweb.dao;

import java.util.Map;

import org.polytech.zapros.bean.Assessment;
import org.polytech.zaprosweb.dao.entity.QuasiExpertEntity;
import org.polytech.zaprosweb.dao.entity.RankEntity;
import org.polytech.zaprosweb.dao.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RankDAO {

    @Autowired private RankRepository rankRepository;
    @Autowired private AssessmentDAO assessmentDAO;

    public void saveRank(QuasiExpertEntity quasiExpert, Map<Assessment, Integer> ranks) {
        ranks.forEach((key, value) -> {
            RankEntity entity = new RankEntity();
            entity.setAssessment(assessmentDAO.getAssessmentByOrderIdAndCriteriaId(key));
            entity.setValue(value);
            entity.setQuasiExpert(quasiExpert);
            rankRepository.save(entity);
        });
    }
}
