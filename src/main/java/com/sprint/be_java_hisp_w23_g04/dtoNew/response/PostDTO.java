package com.sprint.be_java_hisp_w23_g04.dtoNew.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO extends SellerDTO {
    private List<PostResponseDTO> posts;
}
