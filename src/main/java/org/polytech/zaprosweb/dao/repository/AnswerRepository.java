package org.polytech.zaprosweb.dao.repository;

import java.util.List;

import org.polytech.zaprosweb.dao.entity.AnswerEntity;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<AnswerEntity, Long> {
    List<AnswerEntity> findByUserId(long userId);
}
