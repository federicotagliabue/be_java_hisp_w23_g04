package com.sprint.be_java_hisp_w23_g04.controller;

import com.sprint.be_java_hisp_w23_g04.service.UserMediaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserMeliControllerTest {

    @Mock
    UserMediaServiceImpl userService;

    @InjectMocks
    UserMeliController controller;

    @Test
    void followSellerUser() {
        // arrange
        int userId = 1;
        int sellerId = 2;

        // act
        controller.followSellerUser(userId, sellerId);

        // assert
        verify(userService, atLeastOnce()).followSellerUser(any(), any());
    }
}
