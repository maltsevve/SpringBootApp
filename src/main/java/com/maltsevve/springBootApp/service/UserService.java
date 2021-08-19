package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User getById(Long id);

    List<User> getAll();

    User deleteById(Long id);

    User register(User user);

    User findByUsername(String username);
}
