package com.sprint.be_java_hisp_w23_g04.utils;

import com.sprint.be_java_hisp_w23_g04.dto.DBUserDto;
import com.sprint.be_java_hisp_w23_g04.dto.response.PostDTO;
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

    public static User mapUser(DBUserDto userDto){
        List<PostDTO> postsDto = userDto.getPosts();

        List<Post> posts = postsDto.stream().map(p->new Post(p.getPost_id(), p.getDate(), mapProduct(p.getProduct()), p.getCategory(), p.getPrice())).toList();
        return new User(userDto.getUser_id(), userDto.getName(), posts, new ArrayList<>(), new ArrayList<>());
    }

    public static UserDTO mapUser(User user){
        List<PostDTO> postDTOS = user.getPosts().stream().map(p-> new PostDTO(user.getUserId(), p.getId(), p.getDate(),
                mapProduct(p.getProduct()), p.getCategory(), p.getPrice())).toList();
        return new UserDTO(user.getUserId(), user.getName(), postDTOS, new ArrayList<>(), new ArrayList<>());
    }

    public static Product mapProduct(ProductDTO productDTO){
        return new Product(productDTO.getProduct_id(), productDTO.getProduct_name(), productDTO.getType(),
                productDTO.getBrand(), productDTO.getColor(), productDTO.getNotes());
    }
    public static ProductDTO mapProduct(Product product){
        return new ProductDTO(product.getProductId(), product.getProductName(), product.getType(),
                product.getBrand(), product.getColor(), product.getNotes());
    }

    private static LocalDate convertDateFromString(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(stringDate, formatter);
    }
}
