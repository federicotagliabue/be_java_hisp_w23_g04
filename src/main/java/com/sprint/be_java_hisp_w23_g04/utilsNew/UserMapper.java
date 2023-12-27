package com.sprint.be_java_hisp_w23_g04.utilsNew;

import com.sprint.be_java_hisp_w23_g04.dto.DBUserDTO;
import com.sprint.be_java_hisp_w23_g04.dto.request.PostDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.PostResponseDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.ProductDTO;
import com.sprint.be_java_hisp_w23_g04.dtoNew.response.UserDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserFollowDTO;
import com.sprint.be_java_hisp_w23_g04.entity.Post;
import com.sprint.be_java_hisp_w23_g04.entity.Product;
import com.sprint.be_java_hisp_w23_g04.entityNew.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User mapUser(DBUserDTO userDto) {
        return null;
    }

    public static UserDTO mapUser(User user) {
        return new UserDTO(user.getId(), user.getName());
    }

    public static Product mapProduct(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getType(),
                productDTO.getBrand(), productDTO.getColor(), productDTO.getNotes());
    }

    public static ProductDTO mapProduct(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getType(),
                product.getBrand(), product.getColor(), product.getNotes());
    }

    public static Post mapPost(PostDTO post, int postId) {
        return new Post(postId, post.getDate(), mapProduct(post.getProduct()), post.getCategory(), post.getPrice());
    }

    public static UserFollowDTO mapUserFollow(User user) {
        return new UserFollowDTO(user.getId(), user.getName());
    }

}
