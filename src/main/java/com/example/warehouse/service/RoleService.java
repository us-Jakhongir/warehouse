package com.example.warehouse.service;

import com.example.warehouse.dto.RoleCreateDto;
import com.example.warehouse.entity.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role saveRole(RoleCreateDto dto);

    List<Role> findAll();

    Role findById(Long roleId) throws ClassNotFoundException;

    String findRoleById(Long roleId) throws ClassNotFoundException;

    Role editRoleById(Long roleId, RoleCreateDto dto) throws ClassNotFoundException;
}
