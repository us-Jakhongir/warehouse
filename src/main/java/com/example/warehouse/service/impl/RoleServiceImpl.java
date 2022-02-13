package com.example.warehouse.service.impl;

import com.example.warehouse.dto.RoleCreateDto;
import com.example.warehouse.entity.Role;
import com.example.warehouse.repository.RoleRepository;
import com.example.warehouse.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(RoleCreateDto dto) {

        Role role = new Role(
                dto.getName()
        );

        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return  roleRepository.findAll();
    }

    @Override
    public Role findById(Long roleId) throws ClassNotFoundException {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isEmpty())
            throw new ClassNotFoundException("Role Not Found");
        Role role = roleOptional.get();
        return role;
    }


    @Override
    public String findRoleById(Long roleId) throws ClassNotFoundException {
         roleRepository.deleteById(roleId);

        return "Deleted Role";
    }

    @Override
    public Role editRoleById(Long roleId, RoleCreateDto dto) throws ClassNotFoundException {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isEmpty())
            throw new ClassNotFoundException("Role Not Found");
        Role role = roleOptional.get();

        if (!role.getName().equals(dto.getName())) {
            role.setName(dto.getName());
        }

        return role;
    }
}
