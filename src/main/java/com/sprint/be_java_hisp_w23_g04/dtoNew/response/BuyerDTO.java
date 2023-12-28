package com.sprint.be_java_hisp_w23_g04.dtoNew.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "name", "followed" })
public class BuyerDTO extends UserDTO{
    private List<UserDTO> followed;

    public BuyerDTO(Integer id, String name, List<UserDTO> followed) {
        super(id, name);
        this.followed = followed;
    }

    public BuyerDTO(Integer id, String name) {
        super(id, name);
    }
}
