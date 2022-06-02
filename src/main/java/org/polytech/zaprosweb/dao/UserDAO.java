package org.polytech.zaprosweb.dao;

import org.polytech.zapros.bean.MethodType;
import org.polytech.zaprosweb.bean.User;
import org.polytech.zaprosweb.dao.entity.AlternativePackageEntity;
import org.polytech.zaprosweb.dao.entity.IEntity;
import org.polytech.zaprosweb.dao.entity.ProjectEntity;
import org.polytech.zaprosweb.dao.entity.UserEntity;
import org.polytech.zaprosweb.dao.repository.UserRepository;
import org.polytech.zaprosweb.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {

    @Autowired private UserRepository userRepository;

    public void addUser(ProjectEntity projectEntity, User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());

        UserEntity userEntity = userRepository.save(entity);
    }

    public UserEntity registerUser(AlternativePackageEntity alternativePackageEntity, User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setMethodType(user.getMethodType());
        if (user.getMethodType() == MethodType.ARACE || user.getMethodType() == MethodType.ARACE_QV) {
            entity.setThreshold(user.getThreshold());
        }
        entity.setAlternativePackage(alternativePackageEntity);

        return userRepository.save(entity);
    }

    public UserEntity getUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);
    }
}
