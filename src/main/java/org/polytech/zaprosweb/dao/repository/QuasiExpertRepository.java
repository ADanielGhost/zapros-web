package org.polytech.zaprosweb.dao.repository;

import java.util.Collection;
import java.util.List;

import org.polytech.zaprosweb.dao.entity.QuasiExpertEntity;
import org.springframework.data.repository.CrudRepository;

public interface QuasiExpertRepository extends CrudRepository<QuasiExpertEntity, Long> {
    List<QuasiExpertEntity> findByUserId(long userId);
}
