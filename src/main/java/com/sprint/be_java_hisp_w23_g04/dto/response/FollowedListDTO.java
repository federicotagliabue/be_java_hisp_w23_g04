package com.sprint.be_java_hisp_w23_g04.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class FollowedListDTO {
    @JsonAlias("user_id")
    private Integer userId;
    @JsonAlias("user_name")
    private String userName;
    @JsonAlias("followed")
    private List<UserDTO> followed;
}
