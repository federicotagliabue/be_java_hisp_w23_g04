package com.sprint.be_java_hisp_w23_g04.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDTO {
    @JsonAlias("user_id")
    private int userId;
    @JsonAlias("user_name")
    private String userName;
    private List<PostDTO> posts;
    private List<UserFollowDTO> followed;
    private List<UserFollowDTO> followers;


}
