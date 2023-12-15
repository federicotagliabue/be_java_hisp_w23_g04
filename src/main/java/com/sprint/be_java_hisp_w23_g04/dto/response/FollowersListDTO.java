package com.sprint.be_java_hisp_w23_g04.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowersListDTO {
    @JsonAlias("user_id")
    private Integer userId;
    @JsonAlias("userName")
    private String user_name;
    @JsonAlias("followers")
    private List<UserDTO> followers;
}
