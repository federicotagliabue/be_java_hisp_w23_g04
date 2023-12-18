package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.FollowersListDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserDTO;
import com.sprint.be_java_hisp_w23_g04.utils.UserMapper;
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
    public FollowersListDTO getAllFollowersByUserId(int userId) {
        /*
        User userFinded = this.socialMediaRepository.findAllUsers();
        List<UserFollowDTO> followers = userFinded.getFollowers().stream()
                .map(user -> new UserFollowDTO(user.getId(), user.getName()))
                .toList();
        return new FollowersListDTO(userFinded.getId(), userFinded.getName(), followers);
         */
        return null;
    }

    private boolean isSeller(User user) {
        return !user.getPosts().isEmpty();
    }
}
