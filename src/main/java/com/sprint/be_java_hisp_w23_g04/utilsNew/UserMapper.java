package com.sprint.be_java_hisp_w23_g04.utilsNew;

import com.sprint.be_java_hisp_w23_g04.dtoNew.response.UserDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserFollowDTO;
import com.sprint.be_java_hisp_w23_g04.entityNew.User;


public class UserMapper {

    public static UserDTO mapUser(User user) {
        return new UserDTO(user.getId(), user.getName());
    }

    public static UserFollowDTO mapUserFollow(User user) {
        return new UserFollowDTO(user.getId(), user.getName());
    }

}
