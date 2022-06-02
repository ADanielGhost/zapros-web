package org.polytech.zaprosweb.dao;

import java.util.List;

import org.polytech.zapros.QuasiExpertConfigFactory;
import org.polytech.zapros.bean.Criteria;
import org.polytech.zapros.bean.QuasiExpertConfig;
import org.polytech.zaprosweb.dao.entity.ProjectEntity;
import org.polytech.zaprosweb.dao.entity.QuasiExpertConfigEntity;
import org.polytech.zaprosweb.dao.repository.QuasiExpertConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuasiExpertConfigDAO {
    @Autowired private QuasiExpertConfigRepository quasiExpertConfigRepository;

    public void addConfig(ProjectEntity projectEntity, List<Criteria> criteriaList) {
        QuasiExpertConfig config = QuasiExpertConfigFactory.getConfig(criteriaList);
        QuasiExpertConfigEntity entity = new QuasiExpertConfigEntity();

        entity.setLen(config.getLen());
        entity.setIndexes(config.getIndexes());
        entity.setInitData(config.getInitData());
        entity.setProject(projectEntity);

        quasiExpertConfigRepository.save(entity);
    }
}
