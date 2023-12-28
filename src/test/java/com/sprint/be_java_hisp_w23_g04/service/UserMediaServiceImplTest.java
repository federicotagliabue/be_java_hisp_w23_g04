package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.SimpleMessageDTO;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;
import com.sprint.be_java_hisp_w23_g04.gateway.UserGatewayImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sprint.be_java_hisp_w23_g04.utils.UtilsTest.getOneUser;
import static com.sprint.be_java_hisp_w23_g04.utils.UtilsTest.getOneUserSeller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMediaServiceImplTest {
    @Mock
    UserGatewayImpl userGateway;

    @InjectMocks
    UserMediaServiceImpl userService;

    @Test
    @DisplayName("User Follows Seller User successfully.")
    void followSellerUserWhenUserExistTestOK(){
        //Arrange
        Integer userId = 3;
        Integer sellerId = 2;
        SimpleMessageDTO expectedMsg = new SimpleMessageDTO("El usuario con id:3 ahora sigue a vendedor con id:2");

        //Act
        when(userGateway.findUser(userId)).thenReturn(getOneUser());
        when(userGateway.findUser(sellerId)).thenReturn(getOneUserSeller());
        SimpleMessageDTO msg = userService.followSellerUser(userId, sellerId);

        //Assert
        assertEquals(expectedMsg, msg);
    }

    @Test
    @DisplayName("User Follows Seller User fails when User does not exist.")
    void followSellerUserWhenUserDoesNotExistTestNotFound(){
        //Arrange
        Integer userId = 99;
        Integer sellerId = 2;

        //Act
        when(userGateway.findUser(userId)).thenReturn(null);
        when(userGateway.findUser(sellerId)).thenReturn(getOneUserSeller());

        //Assert
        assertThrows(NotFoundException.class, () -> {userService.followSellerUser(userId, sellerId);});
    }

    @Test
    @DisplayName("User Follows Seller User fails when Seller does not exist.")
    void followSellerUserWhenSellerDoesNotExistTestNotFound(){
        //Arrange
        Integer userId = 3;
        Integer sellerId = 99;

        //Act
        when(userGateway.findUser(userId)).thenReturn(getOneUser());
        when(userGateway.findUser(sellerId)).thenReturn(null);

        //Assert
        assertThrows(NotFoundException.class, () -> {userService.followSellerUser(userId, sellerId);});
    }
}
