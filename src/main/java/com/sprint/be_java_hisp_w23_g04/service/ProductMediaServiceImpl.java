package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dtoNew.response.*;
import com.sprint.be_java_hisp_w23_g04.entityNew.*;
import com.sprint.be_java_hisp_w23_g04.gateways.*;
import com.sprint.be_java_hisp_w23_g04.utilsNew.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductMediaServiceImpl implements IProductMediaService {

    private final IUserGateway userGateway;
    private final IPostGateway postGateway;

    private final IProductGateway productGateway;

    public ProductMediaServiceImpl(UserGatewayImp userGateway,
                                   PostGatewayImp postGateway,
                                   ProductGatewayImp productGateway) {
        this.userGateway = userGateway;
        this.postGateway = postGateway;
        this.productGateway = productGateway;
    }

    @Override
    public SimpleMessageDTO savePost(PostDTO post) {
        return null;
    }

    @Override
    public PostListDTO getFilteredPosts(int userId, String order) {
        User user = userGateway.findUser(userId);
        Verifications.verifyUserExist(user,userId);
        Verifications.verifyUserHasFollowedSellers(user);
        LocalDate filterDate = LocalDate.now().minusWeeks(2);

        List<Integer>followedIds = user.getFollowedId();
        List<User> sellers = userGateway.getByIds(followedIds);

        List<PostResponseDTO> listToReturn = new ArrayList<>();

        for(User seller: sellers) {
            List<Post> sellerPosts = postGateway.getByIds(seller.getPostsId());

            for (Post post: sellerPosts) {
                if(!post.getDate().isBefore(filterDate)){
                    Product product = productGateway.getById(post.getProductId());
                    listToReturn.add(PostMapper.mapperToPostResponseDTO(seller,post,product));
                }
            }
        }

        Verifications.validateEmptyResponseList(listToReturn);

        switch (order) {
            case "date_asc" -> listToReturn = orderAsc(listToReturn);
            case "date_desc" -> listToReturn = orderDesc(listToReturn);
        }

        return new PostListDTO(userId,listToReturn);
    }

    private List<PostResponseDTO> orderAsc(List<PostResponseDTO> list) {
        return list.stream()
                .sorted(Comparator.comparing(PostResponseDTO::getDate))
                .collect(Collectors.toList());
    }

    private List<PostResponseDTO> orderDesc(List<PostResponseDTO> list) {
        return list.stream()
                .sorted((p1, p2) -> p2.getDate().compareTo(p1.getDate()))
                .collect(Collectors.toList());
    }

}
