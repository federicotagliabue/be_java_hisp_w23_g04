package com.sprint.be_java_hisp_w23_g04.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerDTO extends BuyerDTO {
    private List<UserDTO> followers;
    @JsonProperty("followers_count")
    private Integer followersCount;

    public SellerDTO(Integer id, String name, Integer followersCount) {
        super(id, name);
        this.followersCount = followersCount;
    }
}

