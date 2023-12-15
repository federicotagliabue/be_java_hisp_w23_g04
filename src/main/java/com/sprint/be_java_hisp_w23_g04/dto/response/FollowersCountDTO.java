package com.sprint.be_java_hisp_w23_g04.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public class FollowersCountDTO {

    @JsonAlias("user_id")
    private Integer userId;
    @JsonAlias("user_name")
    private String userName;
    @JsonAlias("followers_count")
    private Integer followersCount;
}
