package com.sprint.be_java_hisp_w23_g04.integrationIndividual;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sprint.be_java_hisp_w23_g04.dto.request.PostDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.SimpleMessageDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static com.sprint.be_java_hisp_w23_g04.utils.UtilsTest.requestPostDto;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserMeliControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("INDIVIDUAL: Integration test save post OK")
    void testSavePostEndpoint() throws Exception {

        PostDTO request = requestPostDto();
        SimpleMessageDTO response = new SimpleMessageDTO("El post para el user: " + request.getUserId() + " se guardó exitosamente");

        String payloadJson = objectMapper.writeValueAsString(request);
        String responseJson = objectMapper.writeValueAsString(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/products/post").contentType(MediaType.APPLICATION_JSON).content(payloadJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(responseJson))
                .andReturn();
    }
}
