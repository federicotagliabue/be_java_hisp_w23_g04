package com.sprint.be_java_hisp_w23_g04.utils;

import com.sprint.be_java_hisp_w23_g04.dto.response.BuyerDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.SellerDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserDTO;
import com.sprint.be_java_hisp_w23_g04.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UtilsTest {

    public static BuyerDTO getBuyerDTO() {
        return new BuyerDTO(1, "Juan Perez", List.of(new UserDTO(6, "Diego Lopez"), new UserDTO(4, "Sofia Gomez")));
    }

    public static User getOneUser() {
        List <Integer> followedIds = new ArrayList<>();
        followedIds.add(7);
        return new User(6, "Diego Lopez", new ArrayList<>(), followedIds, new ArrayList<>());
    }
    public static User getOneUserSeller() {
        List <Integer> followerIds = new ArrayList<>();
        followerIds.add(2);
        followerIds.add(4);
        List <Integer> postIds = new ArrayList<>();
        postIds.add(4);
        return new User(3, "Pablo Gonzalez", postIds, new ArrayList<>(), followerIds);
    }
}
