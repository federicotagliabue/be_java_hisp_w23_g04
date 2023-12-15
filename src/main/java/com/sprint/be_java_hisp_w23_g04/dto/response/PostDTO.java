package com.sprint.be_java_hisp_w23_g04.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
public class PostDTO {
    private Integer user_id;
    private Integer post_id;
    private LocalDate date;
    private ProductDTO product;
    private Integer category;
    private Double price;
}
