package com.sprint.be_java_hisp_w23_g04.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserFollowDTO {
    private int user_id;
    private String user_name;
}
