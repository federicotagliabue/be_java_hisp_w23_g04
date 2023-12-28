package com.sprint.be_java_hisp_w23_g04.utils;

import com.sprint.be_java_hisp_w23_g04.dto.response.BuyerDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserDTO;

import java.util.List;

public class UtilsTest {

    public static BuyerDTO getBuyerDTO() {
        return new BuyerDTO(1, "Juan Perez", List.of(new UserDTO(6, "Diego Lopez"), new UserDTO(4, "Sofia Gomez")));
    }
}
