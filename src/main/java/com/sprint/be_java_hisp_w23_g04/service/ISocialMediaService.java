package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.request.PostDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.*;

import java.util.List;

public interface ISocialMediaService {

    SimpleMessageDTO savePost(PostDTO post);

    FilteredPostsDTO getFilteredPosts(int userId, String order);

    List<UserDTO> getAllUsers();

    FollowedListDTO getFollowedByUserId(Integer id, String order);

    FollowersListDTO getFollowersByUserId(Integer userId, String order);

    FollowersCountDTO followersCount(Integer userId);

    SimpleMessageDTO followSellerUser(Integer userId, Integer userIdToFollow);

    SimpleMessageDTO unfollowUser(int userId, int unfollowId);

}
