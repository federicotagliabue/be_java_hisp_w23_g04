package com.sprint.be_java_hisp_w23_g04.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PostDTO extends SellerDTO {
    private List<PostResponseDTO> posts;
}
