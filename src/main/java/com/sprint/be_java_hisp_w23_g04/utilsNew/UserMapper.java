package com.sprint.be_java_hisp_w23_g04.utilsNew;

import com.sprint.be_java_hisp_w23_g04.dtoNew.response.*;
import com.sprint.be_java_hisp_w23_g04.entityNew.User;


public class UserMapper {

    public static UserDTO mapUser(User user) {
        if(user.getPostsId().isEmpty()){
            return new BuyerDTO(user.getId(), user.getName(), user.getFollowedId());
        }
        else{
            return new SellerDTO(user.getId(), user.getName(), user.getFollowedId(), user.getFollowersId(), user.getFollowersId().size());
        }
    }

}
