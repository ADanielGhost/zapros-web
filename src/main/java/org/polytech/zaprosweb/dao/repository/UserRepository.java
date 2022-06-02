package org.polytech.zaprosweb.dao.repository;

import org.polytech.zaprosweb.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
