package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.Role;
import com.maltsevve.springBootApp.model.Status;
import com.maltsevve.springBootApp.model.User;
import com.maltsevve.springBootApp.repository.RoleRepository;
import com.maltsevve.springBootApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    @Mock
    private RoleRepository roleRepository = Mockito.mock(RoleRepository.class);

    @Mock
    private BCryptPasswordEncoder passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);

    @InjectMocks
    private final UserServiceImpl userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);

    @Test
    void register() {
        User user = getUser();
        user.setStatus(Status.NOT_ACTIVE);

        when(userRepository.save(any())).thenReturn(user);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(getRoles().get(0));
        when(passwordEncoder.encode(anyString())).thenReturn(getUser().getPassword());

        Assertions.assertEquals(Status.NOT_ACTIVE, user.getStatus());
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getCreated());
        Assertions.assertNotNull(user.getUpdated());

        userService.register(user);
    }

    @Test
    void findByUsername() {
        User user = getUser();

        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        User currentUser = userService.findByUsername(user.getUsername());

        Assertions.assertEquals(1L, currentUser.getId());

        verify(userRepository, times(1)).findByUsername(anyString());
    }

    @Test
    void save() {
        User user = getUser();

        when(userRepository.save(any())).thenReturn(getUser());
        when(roleRepository.findByName("ROLE_USER")).thenReturn(getRoles().get(0));
        when(passwordEncoder.encode(anyString())).thenReturn(getUser().getPassword());

        userService.save(user);

        Assertions.assertEquals(Status.ACTIVE, user.getStatus());
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(user.getCreated());
        Assertions.assertNotNull(user.getUpdated());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getById() {
        User user = getUser();

        when(userRepository.getById(1L)).thenReturn(user);

        userService.getById(user.getId());

        Assertions.assertEquals(1L, user.getId());

        verify(userRepository, times(1)).getById(1L);
    }

    @Test
    void getAll() {
        User user1 = getUser();
        User user2 = getUser();
        user2.setId(2L);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAll();

        Assertions.assertEquals(users.size(), result.size());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        User user = getUser();

        when(userRepository.getById(1L)).thenReturn(user);

        user = userService.deleteById(user.getId());

        Assertions.assertEquals(Status.DELETED, user.getStatus());

        verify(userRepository, times(1)).save(user);
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Test user");
        user.setPassword("Test");
        user.setEmail("test@test.com");
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setStatus(Status.ACTIVE);
        user.setRoles(getRoles());

        return user;
    }

    private List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setName("ROLE_USER");
        roles.add(role);

        return roles;
    }
}