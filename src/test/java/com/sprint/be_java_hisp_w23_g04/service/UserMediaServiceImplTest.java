package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.BuyerDTO;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.exception.BadRequestException;
import com.sprint.be_java_hisp_w23_g04.exception.NoContentException;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;
import com.sprint.be_java_hisp_w23_g04.gateway.UserGatewayImpl;
import com.sprint.be_java_hisp_w23_g04.utils.Verifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.sprint.be_java_hisp_w23_g04.utils.UtilsTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMediaServiceImplTest {
    @Mock
    UserGatewayImpl userGateway;

    @Mock
    Verifications verifications;

    @InjectMocks
    UserMediaServiceImpl userService;

    @Test
    @DisplayName("Verify that the alphabetical asc sort type exists in getFollowersByUserId")
    void test1() {
        // Arrange
        Integer userIdtoFind = 1;
        String orderCriteria = "name_asc";
        BuyerDTO expectedResponse = getBuyerAscendingDTO();

        // Act
        when(userGateway.findUser(any())).thenReturn(new User());
        when(userGateway.getByIds(anyList())).thenReturn(getUsers());
        BuyerDTO response = userService.getFollowersByUserId(userIdtoFind, orderCriteria);

        // Assert
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Verify that the alphabetical dsc sort type exists in getFollowersByUserId")
    void test7() {
        // Arrange
        Integer userIdtoFind = 1;
        String orderCriteria = "name_dsc";
        BuyerDTO expectedResponse = getBuyerDescendingDTO();

        // Act
        when(userGateway.findUser(any())).thenReturn(new User());
        when(userGateway.getByIds(anyList())).thenReturn(getUsers());
        BuyerDTO response = userService.getFollowersByUserId(userIdtoFind, orderCriteria);

        // Assert
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Excep because criteria order not exists")
    void test8() {
        // Arrange
        Integer userIdtoFind = 1;
        String orderCriteria = "name_false";

        // Assert
        assertThrows(BadRequestException.class, () -> userService.getFollowersByUserId(userIdtoFind, orderCriteria));
    }

    @Test
    @DisplayName("Except because userId not exist in getFollowersByUserId")
    void test2() {
        // Arrange
        Integer userIdtoFind = 99;
        String orderCriteria = "name_asc";

        // Act
        when(userGateway.findUser(any())).thenReturn(null);

        // Assert
        assertThrows(NotFoundException.class, () -> userService.getFollowersByUserId(userIdtoFind, orderCriteria));
    }

    @Test
    @DisplayName("Except because not user with followers")
    void test3() {
        // Arrange
        Integer userIdtoFind = 4;
        String orderCriteria = "name_asc";

        // Act
        when(userGateway.findUser(any())).thenReturn(new User());
        when(userGateway.getByIds(anyList())).thenReturn(List.of());

        // Assert
        assertThrows(NoContentException.class, () -> userService.getFollowersByUserId(userIdtoFind, orderCriteria));
    }

    @Test
    @DisplayName("Verify the correct descending order by name in getFollowedByUserId")
    void test4() {
        // Arrange
        Integer userIdToFind = 1;
        String orderCriteria = "name_dsc";
        BuyerDTO expectedResponse = getBuyerDescendingDTO();

        // Act
        when(userGateway.findUser(any())).thenReturn(new User());
        when(userGateway.getByIds(anyList())).thenReturn(getUsers());
        BuyerDTO response = userService.getFollowedByUserId(userIdToFind, orderCriteria);

        // Assert
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Except because userId not exist in getFollowedByUserId")
    void test5() {
        // Arrange
        Integer userIdToFind = 99;
        String orderCriteria = "name_asc";

        // Act
        when(userGateway.findUser(any())).thenReturn(null);

        // Assert
        assertThrows(NotFoundException.class, () -> userService.getFollowedByUserId(userIdToFind, orderCriteria));
    }

    @Test
    @DisplayName("Except because not user with followed")
    void test6() {
        // Arrange
        Integer userIdToFind = 4;
        String orderCriteria = "name_asc";

        // Act
        when(userGateway.findUser(any())).thenReturn(new User());
        when(userGateway.getByIds(anyList())).thenReturn(List.of());

        // Assert
        assertThrows(NoContentException.class, () -> userService.getFollowersByUserId(userIdToFind, orderCriteria));
    }
}
