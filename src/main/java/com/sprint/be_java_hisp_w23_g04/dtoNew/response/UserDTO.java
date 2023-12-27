package com.sprint.be_java_hisp_w23_g04.dtoNew.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @JsonProperty("user_id")
    private Integer id;
    private String name;
}
