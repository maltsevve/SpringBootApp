package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.User;

import java.util.List;

public interface UserService extends GenericService<User, Long>{
    @Override
    User save(User user);

    @Override
    User getById(Long id);

    @Override
    List<User> getAll();

    @Override
    User deleteById(Long id);

    User register(User user);

    User findByUsername(String username);
}
