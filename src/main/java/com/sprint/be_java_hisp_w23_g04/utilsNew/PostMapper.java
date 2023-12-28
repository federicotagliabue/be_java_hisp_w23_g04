package com.sprint.be_java_hisp_w23_g04.utilsNew;

import com.sprint.be_java_hisp_w23_g04.dtoNew.response.PostResponseDTO;
import com.sprint.be_java_hisp_w23_g04.dtoNew.response.ProductDTO;
import com.sprint.be_java_hisp_w23_g04.entityNew.Post;
import com.sprint.be_java_hisp_w23_g04.entityNew.Product;
import com.sprint.be_java_hisp_w23_g04.entityNew.User;

public class PostMapper {

    public static PostResponseDTO PostRequestDTOMapper(){
        return new PostResponseDTO();
    }

    public static PostResponseDTO mapperToPostResponseDTO(User user, Post post, Product product) {
        ProductDTO productDTO = ProductMapper.productDTOMapper(product);

        return new PostResponseDTO(
                user.getId(),
                post.getId(),
                post.getDate(),
                productDTO,
                post.getCategory(),
                post.getPrice()
        );
    }
}
