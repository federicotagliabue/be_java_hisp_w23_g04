package com.sprint.be_java_hisp_w23_g04.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private User user;
    private LocalDate date;
    private Product product;
    private Integer category;
    private Double price;
}
