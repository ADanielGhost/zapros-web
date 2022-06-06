package org.polytech.zaprosweb.dao.repository;

import java.util.List;

import org.polytech.zaprosweb.dao.entity.AssessmentEntity;
import org.springframework.data.repository.CrudRepository;

public interface AssessmentRepository extends CrudRepository<AssessmentEntity, Long> {
    List<AssessmentEntity> findByOrderIdAndCriteriaId(int orderId, long criteriaId);
}
