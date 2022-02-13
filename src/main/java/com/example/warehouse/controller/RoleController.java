package com.example.warehouse.controller;


import com.example.warehouse.dto.RoleCreateDto;
import com.example.warehouse.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.warehouse.entity.Role;

import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final RoleService roleService;


    @PostMapping
    public Role save(@RequestBody RoleCreateDto dto) {
        return roleService.saveRole(dto);
    }


    @GetMapping
    public List<Role> findAll() {
        return roleService.findAll();
    }


    @GetMapping("/{role_id}")
    public Role findById(@PathVariable("role_id") Long roleId) throws ClassNotFoundException {
        return roleService.findById(roleId);
    }

    @DeleteMapping("/{role_id}")
    public String remove(@PathVariable("role_id") Long roleId) throws ClassNotFoundException {
        return roleService.findRoleById(roleId);
    }

    @PutMapping("/{role_id}")
    public Role edit(@PathVariable("role_id") Long roleId, @RequestBody RoleCreateDto dto) throws ClassNotFoundException {
        return roleService.editRoleById(roleId, dto);
    }

}
