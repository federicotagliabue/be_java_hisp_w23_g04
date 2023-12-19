package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.request.PostDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.FollowedListDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserFollowDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.*;
import com.sprint.be_java_hisp_w23_g04.entity.Post;
import com.sprint.be_java_hisp_w23_g04.utils.PostMapper;
import com.sprint.be_java_hisp_w23_g04.utils.UserMapper;
import com.sprint.be_java_hisp_w23_g04.utils.Verifications;
import org.springframework.stereotype.Service;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.repository.ISocialMediaRepository;
import com.sprint.be_java_hisp_w23_g04.repository.SocialMediaRepositoryImpl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sprint.be_java_hisp_w23_g04.utils.Verifications.verifyUserExist;

@Service
public class SocialMediaServiceImpl implements ISocialMediaService {

    private final ISocialMediaRepository socialMediaRepository;

    public SocialMediaServiceImpl(SocialMediaRepositoryImpl socialMediaRepository) {
        this.socialMediaRepository = socialMediaRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = socialMediaRepository.findAllUsers();
        return users.stream().map(UserMapper::mapUser).toList();
    }

    @Override
    public SimpleMessageDTO followSellerUser(Integer userId, Integer userIdToFollow) {
        User user = socialMediaRepository.findUser(userId);
        User seller = socialMediaRepository.findUser(userIdToFollow);
        Verifications.verifyUserExist(user, userId);
        Verifications.verifyUserExist(seller, userIdToFollow);

        Verifications.verifyUserIsSeller(seller);
        Verifications.verifyUserFollowsSeller(user, seller);

        seller.getFollowers().add(user);
        user.getFollowed().add(seller);

        return new SimpleMessageDTO("El usuario con id:" + userId + " ahora sigue a vendedor con id:" + userIdToFollow);
    }

    public FollowersCountDTO followersCount(Integer userId) {
        User user = socialMediaRepository.findUser(userId);

        verifyUserExist(user);

        return new FollowersCountDTO(
                user.getId(),
                user.getName(),
                user.getFollowers().size()
        );
    }

    @Override
    public FollowedListDTO getFollowedByUserId(Integer id, String order) {
        User user = socialMediaRepository.findUser(id);

        verifyUserExist(user);

        List<UserFollowDTO> followed = sortedFollow(user, order);
        return new FollowedListDTO(user.getId(), user.getName(), followed);
    }

    private List<UserFollowDTO> sortedFollow(User user, String order) {

        if (order.equals("name_asc")) {
            return user.getFollowers().stream()
                    .map(UserMapper::mapUserFollow)
                    .sorted(Comparator.comparing(UserFollowDTO::getUserName))
                    .toList();
        } else if (order.equals("name_desc")) {
            return user.getFollowers().stream()
                    .map(UserMapper::mapUserFollow)
                    .sorted(Comparator.
                            comparing(UserFollowDTO::getUserName)
                            .reversed())
                    .toList();
        }

        return user.getFollowers().stream()
                .map(UserMapper::mapUserFollow)
                .toList();
    }


    @Override
    public FollowersListDTO getFollowersByUserId(int userId, String order) {
        User user = this.socialMediaRepository.findUser(userId);

        verifyUserExist(user);

        List<UserFollowDTO> followers = sortedFollow(user, order);

        return new FollowersListDTO(user.getId(), user.getName(), followers);
    }

    @Override
    public SimpleMessageDTO savePost(PostDTO post) {
        List<Post> posts = new ArrayList<>();
        User user = socialMediaRepository.findUser(post.getUserId());

        verifyUserExist(user);

        posts.add(UserMapper.mapPost(post));
        posts.addAll(user.getPosts());
        user.setPosts(posts);

        socialMediaRepository.savePost(user);
        return new SimpleMessageDTO("El post para el user: " + user.getId() + " se guardó exitosamente");
    }

    @Override
    public SimpleMessageDTO unfollowUser(int userId, int unfollowId) {
        User user = socialMediaRepository.findUser(userId);
        User unfollowedUser = socialMediaRepository.findUser(unfollowId) ;
        Verifications.verifyUserExist(user, userId);
        Verifications.verifyUserExist(unfollowedUser, unfollowId);
        Verifications.verifyUserIsFollowed(user, unfollowedUser);
        Verifications.verifyUserIsFollower(unfollowedUser, user);

        socialMediaRepository.unfollowUser(userId, unfollowId);

        return new SimpleMessageDTO("El usuario " + unfollowedUser.getName() + " Id: " + unfollowedUser.getId() + " ya no es seguido por el usuario " + user.getName() + " Id: " + user.getId());
    }

    @Override
    public FilteredPosts getFilteredPosts(int userId, String order) {
        User user = socialMediaRepository.findUser(userId);
        LocalDate filterDate = LocalDate.now().minusWeeks(2);
        List<PostResponseDTO> filteredPosts = new ArrayList<>();

        for(User seller : user.getFollowed()){
            for(Post post : seller.getPosts()){
                if(post.getDate().isAfter(filterDate)){
                    PostResponseDTO postDTO = PostMapper.PostRequestDTOMapper(userId,post);
                    filteredPosts.add(postDTO);
                }
            }
        }

        switch (order){
            case "date_asc" -> filteredPosts = orderAsc(filteredPosts);
            case "date_desc" -> filteredPosts = orderDesc(filteredPosts);
        }

        return new FilteredPosts(userId, filteredPosts);
    }

    private List<PostResponseDTO> orderAsc(List<PostResponseDTO> list){
       return list.stream()
                .sorted(Comparator.comparing(PostDTO::getDate))
                .collect(Collectors.toList());
    }

    private List<PostResponseDTO> orderDesc(List<PostResponseDTO> list){
        return list.stream()
                .sorted((p1, p2) -> p2.getDate().compareTo(p1.getDate()))
                .collect(Collectors.toList());
    }

}
