package com.dennkk.aiod.service.contracts;

import com.dennkk.aiod.domain.entity.UserEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAllUsers();

    Optional<UserEntity> findUserById(Long userId);

    void saveUser(UserEntity user, String username, Map<String, String> form);

    boolean addUser(UserEntity user);
}
