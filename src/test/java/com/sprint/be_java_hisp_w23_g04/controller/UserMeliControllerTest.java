package com.sprint.be_java_hisp_w23_g04.controller;

import com.sprint.be_java_hisp_w23_g04.dto.response.SimpleMessageDTO;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.exception.BadRequestException;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;
import com.sprint.be_java_hisp_w23_g04.gateway.UserGatewayImpl;
import com.sprint.be_java_hisp_w23_g04.service.UserMediaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMeliControllerTest {

    @Mock
    UserMediaServiceImpl userService;

    @InjectMocks
    UserMeliController controller;

    @Test
    public void unfollowSuccessfulTest(){
        int userId = 4;
        int unfollowedId = 1;

        SimpleMessageDTO expectedServiceResponse = new SimpleMessageDTO("El usuario Juan Perez Id: 1 ya no es seguido por el usuario Sofia Gomez Id: 4");
        when(userService.unfollowUser(anyInt(), anyInt()))
                .thenReturn(expectedServiceResponse);
        ResponseEntity<?> actualResponse = controller.unfollowUser(userId, unfollowedId);

        assertThat(new ResponseEntity<>(expectedServiceResponse,HttpStatus.ACCEPTED)).isEqualTo(actualResponse);
    }

    @Test
    public void unfollowUserNotFoundTest(){
        int userId = 99;
        int unfollowedId = 2;
        when(userService.unfollowUser(anyInt(), anyInt()))
                .thenThrow(new NotFoundException("No se encontró usuario c   on el id " + userId + "."));
        assertThrows(NotFoundException.class ,()-> controller.unfollowUser(userId, unfollowedId));
    }

    @Test
    public void unfollowUserNotFollowedTest(){
        int userId = 99;
        int unfollowedId = 2;
        when(userService.unfollowUser(anyInt(), anyInt()))
                .thenThrow(new BadRequestException("El vendedor con id:" + userId + " no es seguido por el usuario con id:" + unfollowedId));
        assertThrows(BadRequestException.class ,()-> controller.unfollowUser(userId, unfollowedId));
    }
}
