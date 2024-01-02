package com.sprint.be_java_hisp_w23_g04;

import com.sprint.be_java_hisp_w23_g04.dto.response.PostListDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.PostResponseDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.ProductDTO;
import com.sprint.be_java_hisp_w23_g04.entity.Post;
import com.sprint.be_java_hisp_w23_g04.entity.Product;
import com.sprint.be_java_hisp_w23_g04.entity.User;

import java.time.LocalDate;
import java.util.List;

public class UtilsTest {

    public static PostListDTO generateExpectedResponseT0008(int userId,String order){

        ProductDTO productDTO = new ProductDTO(1, "Silla gamer Test", "Type Gamer Test", "Brand Test", "Color Test", "Special Edition Test");

        PostResponseDTO post1 = new PostResponseDTO(3, 1, LocalDate.of(2023,12,26), productDTO, 10, 999.99);

        PostResponseDTO post2 = new PostResponseDTO(3, 2, LocalDate.of(2023,12,27), productDTO, 10, 888.00);

        PostResponseDTO post3 = new PostResponseDTO(2, 3, LocalDate.of(2023,12,28), productDTO, 10, 1000);

        switch (order) {
            case "date_asc" -> {return new PostListDTO(userId, List.of(post1,post2,post3));}
            case "date_desc" -> {return new PostListDTO(userId, List.of(post3,post2,post1));}
        }

        return null;
    }

    public static Product getProductTest(){
        return new Product(1,
                "Silla gamer Test",
                "Type Gamer Test",
                "Brand Test",
                "Color Test",
                "Special Edition Test");
    }

    public static List<User> getSellers(){
        User seller = new User();
        seller.setId(2);
        seller.setName("Seller Test 2");
        seller.setPostsId(List.of(3));

        User seller2 = new User();
        seller2.setId(3);
        seller2.setName("Seller Test 3");
        seller2.setPostsId(List.of(1,2));

        return List.of(seller,seller2);
    }

    public static User getUserTest(int userId) {
        User userTest = new User();
        userTest.setFollowedId(List.of(2,3));
        userTest.setId(userId);
        return userTest;
    }

    public static List<Post> generatePostListBySeller(int sellerId) {
        switch (sellerId) {
            case 3 -> {
                Post p1 = new Post(1, 3, 1, LocalDate.of(2023,12,26), 10, 999.99);

                Post p2 = new Post(2, 3, 1, LocalDate.of(2023,12,27), 10, 888.00);

                return List.of(p1,p2);
            }
            case 2 -> {
                Post p3 = new Post(3, 2, 1, LocalDate.of(2023,12,28), 10, 1000.0);

                return List.of(p3);
            }
        }
        return null;
    }

    public static List<Integer> getPostListBySeller(int sellerId) {
        switch (sellerId) {
            case 2 -> { return List.of(3);}
            case 3 -> { return List.of(1,2); }
        }

        return null;
    }
}