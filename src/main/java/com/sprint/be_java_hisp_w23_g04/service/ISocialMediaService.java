package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.*;

import java.util.List;

public interface ISocialMediaService {
    List<UserDTO> getAllUsers();

    FollowedListDTO getFollowedByUserId(Integer id, String order);

    FollowersListDTO getFollowersByUserId(int userId, String order);

    FollowersCountDTO followersCount(Integer userId);

    SimpleMessageDTO followSellerUser(Integer userId, Integer userIdToFollow);
}
