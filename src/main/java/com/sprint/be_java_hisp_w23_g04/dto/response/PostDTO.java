package com.sprint.be_java_hisp_w23_g04.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    @JsonProperty("user_id")
    private int userId;
    @JsonProperty("post_id")
    private int postId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    private ProductDTO product;
    private int category;
    private double price;
}
