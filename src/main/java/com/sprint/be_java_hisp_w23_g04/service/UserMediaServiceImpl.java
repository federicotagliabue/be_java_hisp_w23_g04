package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.*;
import com.sprint.be_java_hisp_w23_g04.entityNew.User;
import com.sprint.be_java_hisp_w23_g04.repository.IUserMediaRepository;
import com.sprint.be_java_hisp_w23_g04.repository.UserMediaRepositoryImpl;
import com.sprint.be_java_hisp_w23_g04.utils.UserMapper;
import com.sprint.be_java_hisp_w23_g04.utils.Verifications;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

import static com.sprint.be_java_hisp_w23_g04.utils.Verifications.verifyUserExist;

@Service
public class UserMediaServiceImpl implements IUserMediaService {

    private final IUserMediaRepository userMediaRepository;

    public UserMediaServiceImpl(UserMediaRepositoryImpl userMediaRepository) {
        this.userMediaRepository = userMediaRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public SimpleMessageDTO followSellerUser(Integer userId, Integer userIdToFollow) {
        return new SimpleMessageDTO("El usuario con id:" + userId + " ahora sigue a vendedor con id:" + userIdToFollow);
    }

    public FollowersCountDTO followersCount(Integer userId) {
        return new FollowersCountDTO();
    }

    @Override
    public FollowedListDTO getFollowedByUserId(Integer id, String order) {
        return new FollowedListDTO();
    }

    private List<UserFollowDTO> sortedFollow(List<User> follows, String order) {
        return null;
    }

    @Override
    public FollowersListDTO getFollowersByUserId(int userId, String order) {
        return null;
    }

    @Override
    public SimpleMessageDTO unfollowUser(int userId, int unfollowId) {
        return null;
    }
}
