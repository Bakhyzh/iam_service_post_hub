package com.post_hub.iam_service.service;

import com.post_hub.iam_service.mapper.UserMapper;
import com.post_hub.iam_service.model.dto.user.UserDTO;
import com.post_hub.iam_service.model.entity.User;
import com.post_hub.iam_service.model.exception.NotFoundException;
import com.post_hub.iam_service.repositories.UserRepository;
import com.post_hub.iam_service.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;
    private UserDTO testUserDTO;

    @BeforeEach
    void setUp(){
        testUser = new User();
        testUser.setId(1);
        testUser.setUsername("TestUser");
        testUser.setEmail("testUser@email.com");
        testUser.setPassword("TestPassword");

        testUserDTO = new UserDTO();
        testUserDTO.setId(1);
        testUserDTO.setUsername("TestUser");
        testUserDTO.setEmail("testUser@email.com");

    }
    @Test
    void getById_User_Exists_ReturnsUserDTO(){
        when(userRepository.findByIdAndDeletedFalse(1)).thenReturn(Optional.of(testUser));
        when(userMapper.toDto(testUser)).thenReturn(testUserDTO);

        UserDTO result = userService.getById(1).getPayload();

        assertNotNull(result);
        assertEquals(testUserDTO.getId(),result.getId());
        assertEquals(testUserDTO.getUsername(),result.getUsername());

        verify(userRepository,times(1)).findByIdAndDeletedFalse(1);
        verify(userMapper,times(1)).toDto(testUser);
    }
    @Test
    void getById_UserNotFound_ThrowsException(){
        when(userRepository.findByIdAndDeletedFalse(999)).thenReturn(Optional.empty());

        assertThatThrownBy(()->userService.getById(999))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("not found");

        verify(userRepository , times(1)).findByIdAndDeletedFalse(999);
        verify(userMapper,times(0)).toDto(testUser);
    }

}
