package com.company.service;

import com.company.entity.User;

import java.util.List;

public interface UserService {
    User register(User user);
    List<User> findAll();
    User findByUsername(String username);
    User findById(Integer id);
    void delete(Integer id);
}
