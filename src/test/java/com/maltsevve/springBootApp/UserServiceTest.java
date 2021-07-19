package com.maltsevve.springBootApp;

import com.maltsevve.springBootApp.repository.UserRepository;
import com.maltsevve.springBootApp.service.UserServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


}
