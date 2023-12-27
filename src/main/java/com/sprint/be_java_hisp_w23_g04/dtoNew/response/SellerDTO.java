package com.sprint.be_java_hisp_w23_g04.dtoNew.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerDTO extends BuyerDTO {
    private List<Integer> followers;
    @JsonProperty("followers_count")
    private Integer followersCount;

    public SellerDTO(Integer id, String name, List<Integer> followed, List<Integer> followers, Integer followersCount) {
        super(id, name, followed);
        this.followers = followers;
        this.followersCount = followersCount;
    }
}
