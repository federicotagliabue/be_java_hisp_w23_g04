package com.sprint.be_java_hisp_w23_g04.dto;


import com.sprint.be_java_hisp_w23_g04.dto.response.PostDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DBUserDto {
    private int user_id;
    private String name;
    private List<PostDTO> posts;
}
