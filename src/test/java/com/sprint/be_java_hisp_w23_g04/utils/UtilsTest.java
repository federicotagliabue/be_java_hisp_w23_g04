package com.sprint.be_java_hisp_w23_g04.utils;

import com.sprint.be_java_hisp_w23_g04.dto.response.BuyerDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserDTO;
import com.sprint.be_java_hisp_w23_g04.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest {

    public static BuyerDTO getBuyerDTO() {
        return new BuyerDTO(1, "Juan Perez", List.of(new UserDTO(6, "Diego Lopez"), new UserDTO(4, "Sofia Gomez")));
    }

    public static User getUser() {
        return new User(1, "Juan Perez", List.of(1, 2, 7), List.of(), List.of(4, 6));
    }

    public static List<User> getUsers() {
        return List.of(new User(4, "Sofia Gomez", List.of(), List.of(1, 3), List.of()), new User(6, "Diego Lopez", List.of(), List.of(7), List.of()));
    }
}
