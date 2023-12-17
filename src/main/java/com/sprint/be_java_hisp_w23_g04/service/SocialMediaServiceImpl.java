package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.FollowersCountDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.UserDTO;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;
import com.sprint.be_java_hisp_w23_g04.utils.UserMapper;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.repository.ISocialMediaRepository;
import com.sprint.be_java_hisp_w23_g04.repository.SocialMediaRepositoryImpl;

import java.util.List;

@Service
public class SocialMediaServiceImpl implements ISocialMediaService {

    private final ISocialMediaRepository socialMediaRepository;

    public SocialMediaServiceImpl(SocialMediaRepositoryImpl socialMediaRepository){
        this.socialMediaRepository = socialMediaRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
       List<User> users = socialMediaRepository.findAllUsers();
       return users.stream().map(UserMapper::mapUser).toList();
    }

    @Override
    public FollowersCountDTO followersCount(Integer userId) {
        User user = socialMediaRepository.findUser(userId);
        if (user == null) {
            throw new NotFoundException("Usuario no encontrado.");
        }

        Integer followersCount = socialMediaRepository.followersCount(userId);

        return new FollowersCountDTO(
                user.getId(),
                user.getName(),
                followersCount
        );
    }

    private boolean isSeller(User user){
        return !user.getPosts().isEmpty();
    }
}
