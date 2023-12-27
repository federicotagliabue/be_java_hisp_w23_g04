package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.*;
import com.sprint.be_java_hisp_w23_g04.dtoNew.response.BuyerDTO;

import java.util.List;

public interface IUserMediaService {
    List<UserDTO> getAllUsers();

    FollowedListDTO getFollowedByUserId(Integer id, String order);

    BuyerDTO getFollowersByUserId(Integer userId, String order);

    FollowersCountDTO followersCount(Integer userId);

    SimpleMessageDTO followSellerUser(Integer userId, Integer userIdToFollow);

    SimpleMessageDTO unfollowUser(int userId, int unfollowId);

}
