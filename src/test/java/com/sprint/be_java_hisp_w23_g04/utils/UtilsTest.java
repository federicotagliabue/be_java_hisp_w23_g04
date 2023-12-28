package com.sprint.be_java_hisp_w23_g04.utils;

import com.sprint.be_java_hisp_w23_g04.dto.response.BuyerDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserDTO;
import com.sprint.be_java_hisp_w23_g04.entity.User;

import java.util.List;

public class UtilsTest {

    public static BuyerDTO getBuyerDTO() {
        return new BuyerDTO(1, "Juan Perez", List.of(
                new UserDTO(3, "Almendra Cari"),
                new UserDTO(6, "Diego Lopez"),
                new UserDTO(2, "Hugo Sorteajugo"),
                new UserDTO(4, "Sofia Gomez"),
                new UserDTO(5, "Token Fuerte"))
        );
    }

    public static List<User> getUsers() {
        return List.of(
                new User(4, "Sofia Gomez", List.of(), List.of(), List.of()),
                new User(6, "Diego Lopez", List.of(), List.of(), List.of()),
                new User(3, "Almendra Cari", List.of(), List.of(), List.of()),
                new User(2, "Hugo Sorteajugo", List.of(), List.of(), List.of()),
                new User(5, "Token Fuerte", List.of(), List.of(), List.of())
        );
    }
}