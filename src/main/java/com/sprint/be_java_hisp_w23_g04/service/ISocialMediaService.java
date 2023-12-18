package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.FollowedListDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserDTO;

import java.util.List;

public interface ISocialMediaService {
    List<UserDTO> getAllUsers();

    FollowedListDTO getFollowedByUserId(int id);
}
