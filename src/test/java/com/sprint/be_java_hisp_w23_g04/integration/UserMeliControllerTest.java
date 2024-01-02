package com.sprint.be_java_hisp_w23_g04.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.be_java_hisp_w23_g04.dto.response.SimpleMessageDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserDTO;
import com.sprint.be_java_hisp_w23_g04.repository.UserMediaRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserMeliControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserMediaRepositoryImpl userMediaRepository;

    @BeforeEach
    public void beforeEach() {
    }

    @Test
    @DisplayName("Get All Users successful")
    void getAllUsersOKTest() throws Exception {
        //Act y Assert
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = result.getResponse().getContentAsString();
        List<UserDTO> studentsDTOs = mapper.readValue(jsonResponse, new TypeReference<List<UserDTO>>(){});
        assertEquals(8, studentsDTOs.size());
    }

    @Test
    @DisplayName("Follow User Seller Successful")
    void followSellerUserOKTest() throws Exception {
        //Act y Assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/{userId}/follow/{userIdToFollow}", 2, 1))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("El usuario con id:2 ahora sigue a vendedor con id:1"));
    }

    @Test
    @DisplayName("Follow User Seller: Fails with Not Found when User Id does not exist")
    void followSellerUserException1Test() throws Exception {
        String expectedMsg= "No se encontró usuario con el id 99.";

        //Act y Assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/{userId}/follow/{userIdToFollow}", 99, 1))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.description").value(expectedMsg));
    }

    @Test
    @DisplayName("Follow User Seller: Fails with Not Found when Seller Id does not exist")
    void followSellerUserException2Test() throws Exception {
        String expectedMsg= "No se encontró usuario con el id 99.";

        //Act y Assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/{userId}/follow/{userIdToFollow}", 1, 99))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.description").value(expectedMsg));
    }

    @Test
    @DisplayName("Follow User Seller: Fails with Bad Request when User and Seller Id are the same")
    void followSellerUserException3Test() throws Exception {
        String expectedMsg= "El usuario y vendedor con id:1 no pueden ser el mismo.";

        //Act y Assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/{userId}/follow/{userIdToFollow}", 1, 1))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.description").value(expectedMsg));
    }

    @Test
    @DisplayName("Follow User Seller: Fails with Bad Request when Seller Id provided is not a Seller")
    void followSellerUserException4Test() throws Exception {
        String expectedMsg= "El id de usuario vendedor proporcionado no es valido.";

        //Act y Assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/{userId}/follow/{userIdToFollow}", 1, 4))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.description").value(expectedMsg));
    }

    @Test
    @DisplayName("Follow User Seller: Fails with Bad Request when User already follows Seller")
    void followSellerUserException5Test() throws Exception {
        String expectedMsg= "El usuario con id:4 ya sigue al vendedor con id:1";

        //Act y Assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/{userId}/follow/{userIdToFollow}", 4, 1))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.description").value(expectedMsg));
    }

    @Test
    @DisplayName("Follow User Seller Fails with Bad Request when ids are negative")
    void followSellerUserValidationExceptionTest() throws Exception {
        String expectedMsg= "Se encontraron los siguientes errores en las validaciones:";
        String expectedMsgError = "El id debe ser mayor a cero";

        //Act y Assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("/users/{userId}/follow/{userIdToFollow}", -2, 1))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.description").value(expectedMsg))
                .andExpect(jsonPath("$.messages", contains(expectedMsgError)));
    }
}
