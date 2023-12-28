package com.sprint.be_java_hisp_w23_g04.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SimpleMessageDTO {
    private String description;
    private List<String> messages;

    public SimpleMessageDTO(String description) {
        this.description = description;
    }
}
