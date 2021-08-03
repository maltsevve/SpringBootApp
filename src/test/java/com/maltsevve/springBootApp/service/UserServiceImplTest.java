//package com.maltsevve.springBootApp.service;
//
//import com.maltsevve.springBootApp.model.Status;
//import com.maltsevve.springBootApp.model.User;
//import com.maltsevve.springBootApp.repository.RoleRepository;
//import com.maltsevve.springBootApp.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//class UserServiceImplTest {
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private RoleRepository roleRepository;
//
//    @Mock
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private UserServiceImpl serviceUnderTest;
//
////    @Test
////    void register() throws ParseException {
////        userService = new UserServiceImpl(userRepository, roleRepository, passwordEncoder);
////
////        User user = new User();
////        user.setUsername("TestUser");
////        user.setPassword("Test");
////        user.setEmail("test@test.com");
////
////        verify(userRepository, times(1)).save(user);
////    }
//
////    @Test
////    void findByUsername() {
////    }
//
////    @Test
////    void save() {
//////        Role role = roleRepository.findByName("ROLE_USER");
////
////        when(userRepository.save(any(User.class))).then(new Answer<User>() {
////            long sequence = 1;
////
////            @Override
////            public User answer(InvocationOnMock invocation) throws Throwable {
////                User user = (User) invocation.getArgument(0);
////                user.setId(sequence++);
////                return user;
////            }
////        });
////    }
//
////    @Test
////    void getById() {
////    }
////
////    @Test
////    void getAll() {
////    }
//
//    @Test
//    void deleteById() {
//        User user = new User();
//        user.setId(1L);
//        user.setUsername("TestUser");
//        user.setPassword("Test");
//        user.setEmail("test@test.com");
//        user.setStatus(Status.DELETED);
//        when(userRepository.save(any())).thenReturn(user);
//        serviceUnderTest.deleteById(1L);
//
//        verify(userRepository, times(1)).save(user);
//    }
//}