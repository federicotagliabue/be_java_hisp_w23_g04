package com.sprint.be_java_hisp_w23_g04.utils;

import com.sprint.be_java_hisp_w23_g04.dto.DBUserDTO;
import com.sprint.be_java_hisp_w23_g04.dto.request.PostDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.PostResponseDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.ProductDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserDTO;
import com.sprint.be_java_hisp_w23_g04.entity.Post;
import com.sprint.be_java_hisp_w23_g04.entity.Product;
import com.sprint.be_java_hisp_w23_g04.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static User mapUser(DBUserDTO userDto) {
        List<PostResponseDTO> postsDto = userDto.getPosts();

        List<Post> posts = postsDto.stream().map(p -> new Post(p.getPostId(), p.getDate(), mapProduct(p.getProduct()), p.getCategory(), p.getPrice())).toList();
        return new User(userDto.getUser_id(), userDto.getName(), posts, new ArrayList<>(), new ArrayList<>());
    }

    public static UserDTO mapUser(User user) {
        List<PostResponseDTO> postResponseDTOS = user.getPosts().stream()
                .map(p -> new PostResponseDTO(user.getId(), p.getId(), p.getDate(), mapProduct(p.getProduct()), p.getCategory(), p.getPrice())).toList();
        return new UserDTO(user.getId(), user.getName(), postResponseDTOS, new ArrayList<>(), new ArrayList<>());
    }

    public static Product mapProduct(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getType(),
                productDTO.getBrand(), productDTO.getColor(), productDTO.getNotes());
    }

    public static ProductDTO mapProduct(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getType(),
                product.getBrand(), product.getColor(), product.getNotes());
    }

    public static Post mapPost(PostDTO post) {
        return new Post(
                post.getDate(),
                mapProduct(post.getProduct()),
                post.getCategory(),
                post.getPrice()
        );
    }

    private static LocalDate convertDateFromString(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(stringDate, formatter);
    }
}
