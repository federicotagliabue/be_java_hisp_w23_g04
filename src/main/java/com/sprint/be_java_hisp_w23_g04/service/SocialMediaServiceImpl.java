package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.FollowedListDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserFollowDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.*;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;
import com.sprint.be_java_hisp_w23_g04.exception.BadRequestException;
import com.sprint.be_java_hisp_w23_g04.utils.UserMapper;
import com.sprint.be_java_hisp_w23_g04.utils.Verifications;
import org.springframework.stereotype.Service;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.repository.ISocialMediaRepository;
import com.sprint.be_java_hisp_w23_g04.repository.SocialMediaRepositoryImpl;

import java.util.List;

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
    public FollowedListDTO getFollowedByUserId(Integer id, String order) {
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
    public FollowersListDTO getFollowersByUserId(int userId, String order) {
        User user = this.socialMediaRepository.findUser(userId);

        Verifications.verifyUserExist(user);

        List<UserFollowDTO> followers = user.getFollowers().stream()
                .map(UserMapper::mapUserFollow).toList();
        return new FollowersListDTO(user.getId(), user.getName(), followers);
    }


    private boolean isSeller(User user) {
        return !user.getPosts().isEmpty();
    }
}
