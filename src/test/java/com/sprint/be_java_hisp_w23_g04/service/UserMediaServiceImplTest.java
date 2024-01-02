package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.SimpleMessageDTO;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.exception.BadRequestException;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;
import com.sprint.be_java_hisp_w23_g04.gateway.UserGatewayImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;

import static com.sprint.be_java_hisp_w23_g04.utils.Verifications.*;
import static org.assertj.core.api.Assertions.assertThat;
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
    public void unfollowUserTest(){
        int userId = 4;
        int unfollowId = 1;

        when(userGateway.findUser(userId)).thenReturn(new User(4,"Sofia Gomez", List.of(), List.of(1,3),List.of()));
        when(userGateway.findUser(unfollowId)).thenReturn(new User(1, "Juan Perez", List.of(1,2,7), List.of(), List.of(4,6)));

        SimpleMessageDTO expectedResponse = new SimpleMessageDTO("El usuario Juan Perez Id: 1 ya no es seguido por el usuario Sofia Gomez Id: 4");

        SimpleMessageDTO actualResponse = userService.unfollowUser(userId, unfollowId);

        assertThat(expectedResponse).isEqualTo(actualResponse);
    }

    @Test
    public void unfollowUserWithUserNotFoundTest(){
        int userId = 400;
        int unfollowId = 1;

        when(userGateway.findUser(userId)).thenReturn(null);
        when(userGateway.findUser(unfollowId)).thenReturn(new User(1, "Juan Perez", List.of(1,2,7), List.of(), List.of(4,6)));

        assertThrows(NotFoundException.class, ()->userService.unfollowUser(userId, unfollowId), "No se encontró usuario con el id 400.");
    }

    @Test
    public void unfollowUserBuyerDontFollowSellerTest(){
        int userId = 6;
        int unfollowId = 1;

        when(userGateway.findUser(userId)).thenReturn(new User(6, "Diego Lopez", List.of(), List.of(7), List.of()));
        when(userGateway.findUser(unfollowId)).thenReturn(new User(1, "Juan Perez", List.of(1,2,7), List.of(), List.of(4,6)));

        assertThrows(BadRequestException.class, ()->userService.unfollowUser(userId, unfollowId), "El usuario con id:6 no sigue al vendedor con id:1");
    }

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
