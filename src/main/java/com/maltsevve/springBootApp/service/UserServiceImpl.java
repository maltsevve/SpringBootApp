package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.Role;
import com.maltsevve.springBootApp.model.Status;
import com.maltsevve.springBootApp.model.User;
import com.maltsevve.springBootApp.repository.RoleRepository;
import com.maltsevve.springBootApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.NOT_ACTIVE); // Use save() to activate new user
        user.setCreated(new Date());
        user.setUpdated(new Date());

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", user, username);
        return user;
    }

    @Override
    public void save(User user) {
        if (user.getStatus() == Status.NOT_ACTIVE) {
            user.setStatus(Status.ACTIVE);
        }

        if (user.getCreated() == null) {
            user.setCreated(new Date());
        }

        user.setUpdated(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        log.info("IN EventServiceImpl save {}", user);
    }

    @Override
    @Transactional
    public User getById(Long id) {
        log.info("IN EventServiceImpl getById {}", id);
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        log.info("IN EventServiceImpl getAll");
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        User user = getById(id);
        user.setStatus(Status.DELETED);
        user.setUpdated(new Date());
        userRepository.save(user);

        log.info("IN EventServiceImpl delete {}", id);
//        userRepository.deleteById(id);
    }
}
