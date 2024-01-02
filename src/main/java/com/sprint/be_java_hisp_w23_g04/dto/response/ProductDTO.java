package com.sprint.be_java_hisp_w23_g04.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Data
@Generated
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotNull(message = "El id del producto no puede estar vacío")
    @Positive(message = "El id del producto debe ser mayor a cero")
    @JsonProperty("product_id")
    private Integer id;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    @Max(value = 40, message = "La longitud del nombre del producto no puede superar los 40 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$\n", message = "El campo no puede poseer caracteres especiales")
    @JsonProperty("product_name")
    private String name;

    @NotBlank(message = "El tipo del producto no puede estar vacío")
    @Max(value = 15, message = "La longitud del tipo del producto no puede superar los 15 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$\n", message = "El tipo del producto no puede poseer caracteres especiales")
    private String type;

    @NotBlank(message = "La marca del producto no puede estar vacía")
    @Max(value = 25, message = "La longitud de la marca del producto no puede superar los 25 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$\n", message = "La marca del producto no puede poseer caracteres especiales")
    private String brand;

    @NotBlank(message = "El color del producto no puede estar vacío")
    @Max(value = 14, message = "La longitud del color del producto no puede superar los 15 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$\n", message = "El color del producto no puede poseer caracteres especiales")
    private String color;

    @Max(value = 80, message = "La longitud de las notas no puede superar los 80 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]*$\n", message = "Las notas no pueden poseer caracteres especiales")
    private String notes;
}
