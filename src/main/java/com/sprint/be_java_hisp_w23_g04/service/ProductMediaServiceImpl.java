package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.request.PostDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.*;
import com.sprint.be_java_hisp_w23_g04.entityNew.User;
import com.sprint.be_java_hisp_w23_g04.gateways.IUserGateway;
import com.sprint.be_java_hisp_w23_g04.gateways.UserGatewayImpl;
import com.sprint.be_java_hisp_w23_g04.utils.Verifications;
import org.springframework.stereotype.Service;
import com.sprint.be_java_hisp_w23_g04.repository.IProductMediaRepository;
import com.sprint.be_java_hisp_w23_g04.repository.ProductMediaRepositoryImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.sprint.be_java_hisp_w23_g04.utils.Verifications.verifyUserExistOld;

@Service
public class ProductMediaServiceImpl implements IProductMediaService {

    private final IProductMediaRepository productMediaRepository;
    private final IUserGateway userGateway;

    public ProductMediaServiceImpl(ProductMediaRepositoryImpl productMediaRepository,
                                   UserGatewayImpl userGateway) {
        this.productMediaRepository = productMediaRepository;
        this.userGateway = userGateway;
    }

    @Override
    public SimpleMessageDTO savePost(PostDTO post) {
//        List<Post> posts = new ArrayList<>();
//        User user = productMediaRepository.findUser(post.getUserId());
//
//        verifyUserExist(user);
//        int postId = productMediaRepository.getNextPostId(user);
//
//        posts.add(UserMapper.mapPost(post, postId));
//        posts.addAll(user.getPosts());
//        user.setPosts(posts);
//
//        productMediaRepository.savePost(user);
//        return new SimpleMessageDTO("El post para el user: " + user.getId() + " se guardó exitosamente");
        return new SimpleMessageDTO("A quemarse las pestañas");
    }

    @Override
    public FilteredPostsDTO getFilteredPosts(int userId, String order) {
        User user = userGateway.getById(userId);
        Verifications.verifyUserExist(user);
        Verifications.verifyUserHasFollowedSellers(user);
//
//        LocalDate filterDate = LocalDate.now().minusWeeks(2);
//
//        List<PostResponseDTO> filteredPosts = user.getFollowed().stream()
//                .flatMap(seller -> productMediaRepository.findUser(seller.getId()).getPosts().stream()
//                        .filter(post -> post.getDate().isAfter(filterDate))
//                        .map(post -> PostMapper.PostRequestDTOMapper(seller.getId(), post)))
//                .collect(Collectors.toList());
//
//        Verifications.validateEmptyResponseList(filteredPosts);
//
//        switch (order) {
//            case "date_asc" -> filteredPosts = orderAsc(filteredPosts);
//            case "date_desc" -> filteredPosts = orderDesc(filteredPosts);
//        }
//
//        return new FilteredPostsDTO(userId, filteredPosts);
        return new FilteredPostsDTO(1, List.of());
    }

    private List<PostResponseDTO> orderAsc(List<PostResponseDTO> list) {
        return list.stream()
                .sorted(Comparator.comparing(PostDTO::getDate))
                .collect(Collectors.toList());
    }

    private List<PostResponseDTO> orderDesc(List<PostResponseDTO> list) {
        return list.stream()
                .sorted((p1, p2) -> p2.getDate().compareTo(p1.getDate()))
                .collect(Collectors.toList());
    }

}
