package com.maltsevve.springBootApp.service;

import com.maltsevve.springBootApp.model.Status;
import com.maltsevve.springBootApp.model.User;
import com.maltsevve.springBootApp.repository.RoleRepository;
import com.maltsevve.springBootApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

//    @InjectMocks
//    private UserServiceImpl userService;

    @Mock
    private UserServiceImpl userService;

//    @Test
//    void register() throws ParseException {
//        userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);
//
//        User user = new User();
//        user.setUsername("TestUser");
//        user.setPassword("Test");
//        user.setEmail("test@test.com");
//
//        verify(userRepository, times(1)).save(user);
//    }

//    @Test
//    void findByUsername() {
//    }

//    @Test
//    void save() {
////        Role role = roleRepository.findByName("ROLE_USER");
//
//        when(userRepository.save(any(User.class))).then(new Answer<User>() {
//            long sequence = 1;
//
//            @Override
//            public User answer(InvocationOnMock invocation) throws Throwable {
//                User user = (User) invocation.getArgument(0);
//                user.setId(sequence++);
//                return user;
//            }
//        });
//    }

//    @Test
//    void getById() {
//    }
//
//    @Test
//    void getAll() {
//    }

    @Test
    void deleteById() {
        User user = new User();
        user.setUsername("TestUser");
        user.setPassword("Test");
        user.setEmail("test@test.com");

        when(userService.getById(1L)).thenReturn(user);

        userService.deleteById(1L);

        Assertions.assertEquals(user.getStatus(), Status.DELETED);

        verify(userService, times(1)).save(user);
    }
}