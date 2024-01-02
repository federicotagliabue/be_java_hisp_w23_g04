package com.sprint.be_java_hisp_w23_g04.utils;

import com.sprint.be_java_hisp_w23_g04.dto.request.PostDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.*;
import com.sprint.be_java_hisp_w23_g04.entity.Post;
import com.sprint.be_java_hisp_w23_g04.entity.Product;
import com.sprint.be_java_hisp_w23_g04.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UtilsTest {

    public static BuyerDTO getBuyerAscendingDTO() {
        return new BuyerDTO(1, "Juan Perez", List.of(
                new UserDTO(3, "Almendra Cari"),
                new UserDTO(6, "Diego Lopez"),
                new UserDTO(2, "Hugo Sorteajugo"),
                new UserDTO(4, "Sofia Gomez"),
                new UserDTO(5, "Token Fuerte"))
        );
    }

    public static BuyerDTO getBuyerDescendingDTO() {
        return new BuyerDTO(1, "Juan Perez", List.of(
                new UserDTO(5, "Token Fuerte"),
                new UserDTO(4, "Sofia Gomez"),
                new UserDTO(2, "Hugo Sorteajugo"),
                new UserDTO(6, "Diego Lopez"),
                new UserDTO(3, "Almendra Cari")
        )
        );
    }

    public static User getUser() {
        return new User(1, "Juan Perez", List.of(1, 2, 7), List.of(), List.of(4, 6, 3, 2, 5));
    }

    public static List<User> getUsers() {
        return List.of(
                new User(4, "Sofia Gomez", List.of(), List.of(), List.of()),
                new User(6, "Diego Lopez", List.of(), List.of(), List.of()),
                new User(3, "Almendra Cari", List.of(), List.of(), List.of()),
                new User(2, "Hugo Sorteajugo", List.of(), List.of(), List.of()),
                new User(5, "Token Fuerte", List.of(), List.of(), List.of())
        );
    }

    public static User getOneUser() {
        List<Integer> followedIds = new ArrayList<>();
        followedIds.add(7);
        return new User(6, "Diego Lopez", new ArrayList<>(), followedIds, new ArrayList<>());
    }

    public static User getOneUserSeller() {
        List<Integer> followerIds = new ArrayList<>();
        followerIds.add(2);
        followerIds.add(4);
        List<Integer> postIds = new ArrayList<>();
        postIds.add(4);
        return new User(3, "Pablo Gonzalez", postIds, new ArrayList<>(), followerIds);
    }

    public static PostListDTO generateExpectedResponse(int userId, String order) {

        ProductDTO productDTO = new ProductDTO(1, "Silla gamer Test", "Type Gamer Test", "Brand Test", "Color Test", "Special Edition Test");

        PostResponseDTO post1 = new PostResponseDTO(3, 1, LocalDate.of(2023, 12, 26), productDTO, 10, 999.99);

        PostResponseDTO post2 = new PostResponseDTO(3, 2, LocalDate.of(2023, 12, 27), productDTO, 10, 888.00);

        PostResponseDTO post3 = new PostResponseDTO(2, 3, LocalDate.of(2023, 12, 28), productDTO, 10, 1000);

        switch (order) {
            case "date_asc" -> {
                return new PostListDTO(userId, List.of(post1, post2, post3));
            }
            case "date_desc" -> {
                return new PostListDTO(userId, List.of(post3, post2, post1));
            }
        }

        return null;
    }

    public static Product getProductTest() {
        return new Product(1,
                "Silla gamer Test",
                "Type Gamer Test",
                "Brand Test",
                "Color Test",
                "Special Edition Test");
    }

    public static List<User> getSellers() {
        User seller = new User();
        seller.setId(2);
        seller.setName("Seller Test 2");
        seller.setPostsId(List.of(3));

        User seller2 = new User();
        seller2.setId(3);
        seller2.setName("Seller Test 3");
        seller2.setPostsId(List.of(0, 1, 2));

        return List.of(seller, seller2);
    }

    public static User getUserTest(int userId) {
        User userTest = new User();
        userTest.setFollowedId(List.of(2, 3));
        userTest.setId(userId);
        return userTest;
    }

    public static List<Post> generatePostListBySeller(int sellerId) {
        switch (sellerId) {
            case 3 -> {
                Post p0 = new Post(0, 3, 1, LocalDate.of(2020, 6, 18), 10, 999.99);

                Post p1 = new Post(1, 3, 1, LocalDate.of(2023, 12, 26), 10, 999.99);

                Post p2 = new Post(2, 3, 1, LocalDate.of(2023, 12, 27), 10, 888.00);

                return List.of(p0, p1, p2);
            }
            case 2 -> {
                Post p3 = new Post(3, 2, 1, LocalDate.of(2023, 12, 28), 10, 1000.0);

                return List.of(p3);
            }
        }
        return null;
    }

    public static List<Integer> getPostListBySeller(int sellerId) {
        switch (sellerId) {
            case 2 -> {
                return List.of(3);
            }
            case 3 -> {
                return List.of(0, 1, 2);
            }
        }

        return null;
    }

    public static PostResponseDTO getExcludedPost() {
        ProductDTO productDTO = new ProductDTO(1, "Silla gamer Test", "Type Gamer Test", "Brand Test", "Color Test", "Special Edition Test");

        return new PostResponseDTO(3, 0, LocalDate.of(2020, 6, 18), productDTO, 10, 999.99);
    }

    public static PostDTO requestPostDto() {
        ProductDTO productDTO = new ProductDTO(1, "Product", "Type", "Brand", "Color", "Notes");

        PostDTO postDTO = new PostDTO();
        postDTO.setUserId(1);
        postDTO.setDate(LocalDate.now());
        postDTO.setProduct(productDTO);
        postDTO.setCategory(123);
        postDTO.setPrice(99.99);

        return postDTO;
    }

    public static PostDTO requestPostDtoUserIdNotFound() {
        ProductDTO productDTO = new ProductDTO(99, "Product", "Type", "Brand", "Color", "Notes");

        PostDTO postDTO = new PostDTO();
        postDTO.setUserId(99);
        postDTO.setDate(LocalDate.now());
        postDTO.setProduct(productDTO);
        postDTO.setCategory(123);
        postDTO.setPrice(99.99);

        return postDTO;
    }

    public static SimpleMessageDTO getSimpleMessageDTO(PostDTO request) {
        return new SimpleMessageDTO("El post para el user: " + request.getUserId() + " se guardó exitosamente");
    }

    public static SellerDTO getSellerDTO() {
        return new SellerDTO(1, "Juan Perez", 2);
    }

    public static SimpleMessageDTO getSimpleMessageDTOUserNotExist() {
        return new SimpleMessageDTO("No se encontró usuario con el id 99.");
    }
}
