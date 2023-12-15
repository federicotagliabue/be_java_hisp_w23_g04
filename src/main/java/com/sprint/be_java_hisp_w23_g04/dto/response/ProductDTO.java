package com.sprint.be_java_hisp_w23_g04.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ProductDTO {
    
    private Integer product_id;
    private String product_name;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
