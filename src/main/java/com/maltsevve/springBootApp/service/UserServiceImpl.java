package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.User;
import com.maltsevve.springBootApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        log.info("IN EventServiceImpl save {}", user);
        userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User getById(Long id) {
        log.info("IN EventServiceImpl getById {}", id);
//        return userRepository.findOne(id);
//        return userRepository.getById(id);
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        log.info("IN EventServiceImpl getAll");
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        log.info("IN EventServiceImpl delete {}", id);
        userRepository.deleteById(id);
    }
}
