package com.sprint.be_java_hisp_w23_g04.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class PostDTO {
    private int user_id;
    private int post_id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    private ProductDTO product;
    private int category;
    private double price;
}
