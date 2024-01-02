package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.SellerDTO;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;
import com.sprint.be_java_hisp_w23_g04.gateway.IUserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMediaServiceImplTest {
    @Mock
    IUserGateway userGateway;

    @InjectMocks
    UserMediaServiceImpl userService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("followersCount() should return a SellerDTO")
    void testFollowersCountOk() {
        // Arrange
        Integer userId = 1;
        User mockUser = new User();
        mockUser.setId(1);
        mockUser.setName("test");
        mockUser.setFollowersId(Collections.emptyList());

        SellerDTO expected = new SellerDTO();
        expected.setFollowersCount(0);
        expected.setId(1);
        expected.setName("test");

        // Act
        when(userGateway.findUser(any())).thenReturn(mockUser);

        SellerDTO result = userService.followersCount(userId);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("followersCount() should throw NotFoundException")
    void testFollowersCountThrowsNotFoundException() {
        // Arrange
        Integer userId = 1;

        // Act
        when(userGateway.findUser(any())).thenReturn(null);

        // Assert
        assertThrows(NotFoundException.class, () -> userService.followersCount(userId));
    }

}
