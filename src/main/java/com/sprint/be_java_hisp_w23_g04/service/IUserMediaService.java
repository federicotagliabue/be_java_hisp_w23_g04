package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.*;
import com.sprint.be_java_hisp_w23_g04.dtoNew.response.BuyerDTO;
import com.sprint.be_java_hisp_w23_g04.dtoNew.response.SellerDTO;

import java.util.List;

public interface IUserMediaService {
    List<UserDTO> getAllUsers();

    BuyerDTO getFollowedByUserId(Integer id, String order);

    BuyerDTO getFollowersByUserId(Integer userId, String order);

    SellerDTO followersCount(Integer userId);

    SimpleMessageDTO followSellerUser(Integer userId, Integer userIdToFollow);

    SimpleMessageDTO unfollowUser(int userId, int unfollowId);

}
