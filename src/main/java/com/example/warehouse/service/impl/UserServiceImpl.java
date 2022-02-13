package com.example.warehouse.service.impl;

import com.example.warehouse.dto.UserCreateDto;
import com.example.warehouse.entity.Role;
import com.example.warehouse.entity.User;
import com.example.warehouse.exception.NotFoundException;
import com.example.warehouse.repository.RoleRepository;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.repository.WarehouseRepository;
import com.example.warehouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final WarehouseRepository warehouseRepository;




    @Override
    public User save(UserCreateDto dto) {


        Set<Role> roleSet = new HashSet<>();
        Set<Long> roleIds = dto.getRoleIds();
        for (Long roleId : roleIds) {
            Optional<Role> roleOptional = roleRepository.findById(roleId);
            if (roleOptional.isEmpty()) {
                throw new NotFoundException("Not Found Role");
            }
            Role role = roleOptional.get();
            roleSet.add(role);
        }

        User user = new User(
                dto.getFirstname(),
                dto.getLastname(),
                dto.getPhone(),
                dto.getEmail(),
                warehouseRepository.findById(dto.getWarehouseId()).orElseThrow(() -> new NotFoundException("Not Found Warehouse")),
                dto.getUnique_number(),
                dto.getPassword(),
                roleSet
        );

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long userId) throws ClassNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty())
            throw new ClassNotFoundException("User Not Found");
        User user = userOptional.get();

        return user;
    }

    @Override
    public String findByIdAndRemove(Long userId) {
        userRepository.deleteById(userId);
        return "Deleted User With ID: {" + userId + "}";
    }

    @Override
    public User updateUser(Long userId, UserCreateDto dto) {
        Set<Long> roleIds = dto.getRoleIds();
        Set<Role> roleSet = new HashSet<>();
        for (Long roleId : roleIds) {
            Optional<Role> roleOptional = roleRepository.findById(roleId);
            if (roleOptional.isPresent()) {
                Role role = roleOptional.get();
                roleSet.add(role);
            }
        }
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isEmpty())
            throw new RuntimeException("User Not Found");
        User user = userById.get();

        Set<Role> userRoles = user.getRoles();
        userRoles.addAll(roleSet);
        user.setRoles(userRoles);

        if (!user.getFirstname().equals(dto.getFirstname())) {
            user.setFirstname(dto.getFirstname());
        }

        if (!user.getLastname().equals(dto.getLastname())) {
            user.setLastname(dto.getLastname());
        }

        if (!user.getPhone().equals(dto.getPhone())) {
            user.setPhone(dto.getPhone());
        }

        if (!user.getPassword().equals(dto.getPassword())) {
            user.setPassword(dto.getPassword());
        }

        if (!user.getUniqueNumber().equals(dto.getUnique_number())) {
            user.setUniqueNumber(dto.getUnique_number());
        }


        User updatedUser = userRepository.save(user);


        return updatedUser;
    }

    @Override
    public List<User> findRoleName(Set<String> name) {
        return userRepository.selectAllUserRoleName(name);

    }
}
