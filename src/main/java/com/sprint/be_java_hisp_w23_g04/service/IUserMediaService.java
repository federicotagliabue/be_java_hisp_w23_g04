package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dtoNew.response.*;

import java.util.List;
import java.util.stream.Stream;

public interface IUserMediaService {
    List<UserDTO> getAllUsers();

    UserDTO getFollowedByUserId(Integer id, String order);

    UserDTO getFollowersByUserId(int userId, String order);

    Integer followersCount(Integer userId);

    SimpleMessageDTO followSellerUser(Integer userId, Integer userIdToFollow);

    SimpleMessageDTO unfollowUser(int userId, int unfollowId);

}
