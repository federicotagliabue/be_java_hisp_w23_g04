package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.UtilsTest;
import com.sprint.be_java_hisp_w23_g04.dto.response.PostListDTO;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.gateway.PostGatewayImpl;
import com.sprint.be_java_hisp_w23_g04.gateway.ProductGatewayImpl;
import com.sprint.be_java_hisp_w23_g04.gateway.UserGatewayImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductMediaServiceImplTest {

    @Mock
    ProductGatewayImpl productGateway;

    @Mock
    UserGatewayImpl userGateway;

    @Mock
    PostGatewayImpl postGateway;

    @InjectMocks
    ProductMediaServiceImpl productService;

    @Test
    @DisplayName("T-0006: Should return published posts in the last 2 weeks of the sellers followed by the given user ordered by date ASC.")
    public void getFilteredPostsOrderByDateAsc(){
        int userId = 99;
        String order = "date_asc";
        User userTest = UtilsTest.getUserTest(99);
        List<User> sellers = UtilsTest.getSellers();
        PostListDTO expectedResponse = UtilsTest.generateExpectedResponseT0008(userId,order);

        when(userGateway.findUser(userId)).thenReturn(userTest);
        when(userGateway.getByIds(any())).thenReturn(sellers);
        when(postGateway.getByIds(UtilsTest.getPostListBySeller(2))).thenReturn(UtilsTest.generatePostListBySeller(2));
        when(postGateway.getByIds(List.of(1,2))).thenReturn(UtilsTest.generatePostListBySeller(3));
        when(productGateway.getById(1)).thenReturn(UtilsTest.getProductTest());

        PostListDTO actualResponse = productService.getFilteredPosts(userId,order);

        Assertions.assertEquals(expectedResponse,actualResponse);
    }

    @Test
    @DisplayName("T-0006: Should return published posts in the last 2 weeks of the sellers followed by the given user ordered by date DESC.")
    public void getFilteredPostsOrderByDateDesc(){
        int userId = 99;
        String order = "date_desc";
        User userTest = UtilsTest.getUserTest(99);
        List<User> sellers = UtilsTest.getSellers();
        PostListDTO expectedResponse = UtilsTest.generateExpectedResponseT0008(userId,order);

        when(userGateway.findUser(userId)).thenReturn(userTest);
        when(userGateway.getByIds(any())).thenReturn(sellers);
        when(postGateway.getByIds(UtilsTest.getPostListBySeller(2))).thenReturn(UtilsTest.generatePostListBySeller(2));
        when(postGateway.getByIds(List.of(1,2))).thenReturn(UtilsTest.generatePostListBySeller(3));
        when(productGateway.getById(1)).thenReturn(UtilsTest.getProductTest());

        PostListDTO actualResponse = productService.getFilteredPosts(userId,order);

        Assertions.assertEquals(expectedResponse,actualResponse);
    }
}
