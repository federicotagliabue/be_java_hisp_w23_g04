package com.sprint.be_java_hisp_w23_g04.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter @Getter
public class UserDTO {
    @JsonAlias("user_id")
    private String userId;
    @JsonAlias("user_name")
    private String userName;
}
