package com.sprint.be_java_hisp_w23_g04.dto;


import com.sprint.be_java_hisp_w23_g04.dto.response.PostResponseDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBUserDTO {
    private int user_id;
    private String name;
    private List<PostResponseDTO> posts;
}
