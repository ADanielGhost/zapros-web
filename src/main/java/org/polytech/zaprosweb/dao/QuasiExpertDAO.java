package org.polytech.zaprosweb.dao;

import java.util.List;

import org.polytech.zapros.bean.QuasiExpert;
import org.polytech.zaprosweb.dao.entity.QuasiExpertEntity;
import org.polytech.zaprosweb.dao.entity.UserEntity;
import org.polytech.zaprosweb.dao.repository.QuasiExpertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuasiExpertDAO {

    @Autowired private QuasiExpertRepository quasiExpertRepository;
    @Autowired private RankDAO rankDAO;

    public void saveQes(UserEntity user, List<QuasiExpert> qes) {
        qes.forEach(quasiExpert -> {
            QuasiExpertEntity entity = new QuasiExpertEntity();
            entity.setMatrix(quasiExpert.getMatrix());
            entity.setUser(user);

            QuasiExpertEntity save = quasiExpertRepository.save(entity);
            rankDAO.saveRank(save, quasiExpert.getRanks());
        });
    }

    public QuasiExpert getQuasiExpertById(Long quasiExpertId) {
        return quasiExpertRepository.findById(quasiExpertId).get().toModel();
    }

    public boolean qesAlreadyExists(long userId) {
        return quasiExpertRepository.findByUserId(userId).size() != 0;
    }
}
