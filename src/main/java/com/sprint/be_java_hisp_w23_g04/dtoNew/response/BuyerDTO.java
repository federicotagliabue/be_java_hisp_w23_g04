package com.sprint.be_java_hisp_w23_g04.dtoNew.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BuyerDTO extends UserDTO{
    private List<UserDTO> followed;

    public BuyerDTO(Integer id, String name, List<UserDTO> followed) {
        super(id, name);
        this.followed = followed;
    }
}
