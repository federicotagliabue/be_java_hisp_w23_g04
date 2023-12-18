package com.sprint.be_java_hisp_w23_g04.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sprint.be_java_hisp_w23_g04.dto.request.PostDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.FollowedListDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserFollowDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.*;
import com.sprint.be_java_hisp_w23_g04.entity.Post;
import com.sprint.be_java_hisp_w23_g04.entity.Product;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;
import com.sprint.be_java_hisp_w23_g04.exception.BadRequestException;
import com.sprint.be_java_hisp_w23_g04.utils.PostMapper;
import com.sprint.be_java_hisp_w23_g04.utils.UserMapper;
import com.sprint.be_java_hisp_w23_g04.utils.Verifications;
import org.springframework.stereotype.Service;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.repository.ISocialMediaRepository;
import com.sprint.be_java_hisp_w23_g04.repository.SocialMediaRepositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public FollowedListDTO getFollowedByUserId(Integer id) {
        User user = socialMediaRepository.findUser(id);

        Verifications.verifyUserExist(user);

        List<UserFollowDTO> followed = user.getFollowed().stream().map(UserMapper::mapUserFollow).toList();
        return new FollowedListDTO(user.getId(), user.getName(), followed);
    }

    public FollowersCountDTO followersCount(Integer userId) {
        User user = socialMediaRepository.findUser(userId);

        Verifications.verifyUserExist(user);

        return new FollowersCountDTO(
                user.getId(),
                user.getName(),
                user.getFollowers().size()
        );
    }

    @Override
    public SimpleMessageDTO followSellerUser(Integer userId, Integer userIdToFollow) {
        User user = socialMediaRepository.findUser(userId);
        User seller = socialMediaRepository.findUser(userIdToFollow);
        if (user == null || seller == null) {
            throw new NotFoundException("No se encontró usuario con el id proporcionado.");
        }

        if (!isSeller(seller)) {
            throw new BadRequestException("El id de usuario vendedor proporcionado no es valido.");
        }

        if (userAlreadyFollowsSeller(user, seller)) {
            throw new BadRequestException("El usuario con id:" + userId + " ya sigue al vendedor con id:" + userIdToFollow);
        }

        seller.getFollowers().add(user);
        user.getFollowed().add(seller);

        return new SimpleMessageDTO("Usuario con id:" + userId + " ahora sigue a vendedor con id:" + userIdToFollow);
    }

    private boolean userAlreadyFollowsSeller(User user, User seller) {
        return user.getFollowed().contains(seller);
    }


    @Override
    public FollowersListDTO getAllFollowersByUserId(int userId) {
        User user = this.socialMediaRepository.findUser(userId);

        Verifications.verifyUserExist(user);

        List<UserFollowDTO> followers = user.getFollowers().stream()
                .map(UserMapper::mapUserFollow).toList();
        return new FollowersListDTO(user.getId(), user.getName(), followers);
    }

    @Override
    public void savePost(PostDTO post) {
        List<Post> posts = new ArrayList<>();
        User user = socialMediaRepository.findUser(post.getUserId());

        Verifications.verifyUserExist(user);

        posts.add(UserMapper.mapPost(post));
        posts.addAll(user.getPosts());
        user.setPosts(posts);

        socialMediaRepository.savePost(user);
    }

    private boolean isSeller(User user) {
        return !user.getPosts().isEmpty();
    }

    @Override
    public SimpleMessageDTO unfollowUser(int userId, int unfollowId) {
        User user = socialMediaRepository.findUser(userId);
        Verifications.verifyUserExist(user, userId);
        User unfollowedUser = socialMediaRepository.findUser(unfollowId) ;
        Verifications.verifyUserExist(unfollowedUser, unfollowId);

        if(user.getFollowed().stream().filter(followed -> Objects.equals(followed.getId(), unfollowedUser.getId())).findAny().orElse(null) == null){
            throw new NotFoundException("El usuario que estás intentando dejar de seguir no se encuentra en tu lista de seguidos");
        };

        if(unfollowedUser.getFollowers().stream().filter(follower -> Objects.equals(follower.getId(), user.getId())).findAny().orElse(null) == null){
            throw new NotFoundException("No te encuentras en la lista de seguidos del usuario al que estás intentando dejar de seguir. Por favor, comprueba la consistencia de tus datos");
        };

        socialMediaRepository.unfollowUser(userId, unfollowId);

        return new SimpleMessageDTO("El usuario " + unfollowedUser.getName() + " Id: " + unfollowedUser.getId() + " ya no es seguido por el usuario " + user.getName() + " Id: " + user.getId());
    }

    @Override
    public FilteredPosts getFilteredPosts(int userId) {
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

        return new FilteredPosts(userId, filteredPosts);
    }


}
