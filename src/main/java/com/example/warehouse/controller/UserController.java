package com.example.warehouse.controller;


import com.example.warehouse.dto.UserCreateDto;
import com.example.warehouse.entity.User;
import com.example.warehouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    @PostMapping()
    public User save(@RequestBody UserCreateDto dto) {
        return userService.save(dto);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }


    @GetMapping("/{user_id}")
    public User findById(@PathVariable("user_id") Long userId) throws ClassNotFoundException {
        return userService.findById(userId);
    }

    @DeleteMapping("/{user_id}")
    public String remove(@PathVariable("user_id") Long userId) {
        return userService.findByIdAndRemove(userId);
    }


    @PutMapping("/{user_id}")
    public User edit(@PathVariable("user_id") Long userId, @RequestBody UserCreateDto dto) {
        return userService.updateUser(userId, dto);
    }


    @PostMapping("/findRoleName")
    public List<User> findRoleName(@RequestParam Set<String> name) {
        List<User> userList = userService.findRoleName(name);
        return userList;
    }



}
