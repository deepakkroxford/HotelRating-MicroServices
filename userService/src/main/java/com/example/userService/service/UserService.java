package com.example.userService.service;

import com.example.userService.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User findById(Long id);
    List<User> findAll();
    User updateUser(User user, Long id);
    String deleteById(Long id);
}
