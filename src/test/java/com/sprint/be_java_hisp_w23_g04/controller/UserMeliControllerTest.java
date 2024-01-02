package com.sprint.be_java_hisp_w23_g04.controller;

import com.sprint.be_java_hisp_w23_g04.dto.response.BuyerDTO;
import com.sprint.be_java_hisp_w23_g04.service.UserMediaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static com.sprint.be_java_hisp_w23_g04.utils.UtilsTest.getBuyerAscendingDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMeliControllerTest {

    @Mock
    UserMediaServiceImpl userService;

    @InjectMocks
    UserMeliController controller;

    @Test
    @DisplayName("Verify that the alphabetical sort type exists")
    void test1() {
        // Arrange
        BuyerDTO expectedBuyerDTO = getBuyerAscendingDTO();
        ResponseEntity<?> expectedResponse = new ResponseEntity<>(expectedBuyerDTO, HttpStatus.OK);

        // Act
        when(userService.getFollowersByUserId(any(), any())).thenReturn(expectedBuyerDTO);
        ResponseEntity<?> response = controller.getAllFollowersByUserId(1, "name_asc");

        // Assert
        assertEquals(expectedResponse, response);
    }

    @Test
    @DisplayName("Verify the correct descending order by name")
    void test2() {
        // Arrange
        BuyerDTO expectedBuyerDTO = getBuyerAscendingDTO();
        ResponseEntity<?> expectedResponse = new ResponseEntity<>(expectedBuyerDTO, HttpStatus.OK);

        // Act
        when(userService.getFollowedByUserId(any(), any())).thenReturn(expectedBuyerDTO);
        ResponseEntity<?> response = controller.getFollowedByUserId(1, "name_asc");

        // Assert
        assertEquals(expectedResponse, response);
    }
}
