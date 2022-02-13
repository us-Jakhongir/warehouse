package com.example.warehouse.service;

import com.example.warehouse.dto.UserCreateDto;
import com.example.warehouse.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User save(UserCreateDto dto);

    List<User> findAll();

    User findById(Long userId) throws ClassNotFoundException;

    String findByIdAndRemove(Long userId);

    User updateUser(Long userId, UserCreateDto dto);

    List<User> findRoleName(Set<String> name);
}
