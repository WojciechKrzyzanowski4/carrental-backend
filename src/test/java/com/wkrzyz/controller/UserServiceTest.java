package com.wkrzyz.controller;

import com.wkrzyz.entity.RegistrationSource;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.entity.UserRole;
import com.wkrzyz.mapper.UserMapper;
import com.wkrzyz.repository.UserEntityRepository;
import com.wkrzyz.service.UserService;
import com.wkrzyz.service.impl.UserServiceImpl;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.BDDMockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.awaitility.Awaitility.given;



@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserEntityRepository userEntityRepository;

    private UserEntity mockUser;
    @BeforeEach
    void setUp() {
        mockUser.setName("WojciechKrzyzanowski4");
        mockUser.setEmail("WojciechKrzyzanowski4");
        mockUser.setRole(UserRole.ROLE_ADMIN);
        mockUser.setSource(RegistrationSource.GITHUB);
    }
    @DisplayName("JUnit test for saveEmployee method")
    @Test
    void testSaveUser() {

       //to do this test


    }

}
