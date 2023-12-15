package com.sprint.be_java_hisp_w23_g04.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    
    private int product_id;
    private String product_name;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
