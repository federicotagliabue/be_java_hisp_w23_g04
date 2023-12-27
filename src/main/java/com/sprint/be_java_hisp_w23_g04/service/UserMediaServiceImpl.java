package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.*;
import com.sprint.be_java_hisp_w23_g04.dtoNew.response.BuyerDTO;
import com.sprint.be_java_hisp_w23_g04.entityNew.User;
import com.sprint.be_java_hisp_w23_g04.repository.IUserMediaRepository;
import com.sprint.be_java_hisp_w23_g04.repository.UserMediaRepositoryImpl;
import com.sprint.be_java_hisp_w23_g04.utilsNew.UserMapper;
import com.sprint.be_java_hisp_w23_g04.utilsNew.Verifications;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


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
    public BuyerDTO getFollowedByUserId(Integer userId, String order) {
        User user = this.userMediaRepository.findUser(userId);

        Verifications.verifyUserExist(user, userId);

        List<User> userFollowers = userMediaRepository.getByIds(user.getFollowedId());

        Verifications.validateEmptyResponseList(userFollowers);

        List<com.sprint.be_java_hisp_w23_g04.dtoNew.response.UserDTO> followed = sortedFollow(userFollowers, order);

        return new BuyerDTO(user.getId(), user.getName(), followed);
    }


    @Override
    public BuyerDTO getFollowersByUserId(Integer userId, String order) {
        User user = this.userMediaRepository.findUser(userId);

        Verifications.verifyUserExist(user, userId);

        List<User> userFollowers = userMediaRepository.getByIds(user.getFollowersId());

        Verifications.validateEmptyResponseList(userFollowers);

        List<com.sprint.be_java_hisp_w23_g04.dtoNew.response.UserDTO> followed = sortedFollow(userFollowers, order);

        return new BuyerDTO(user.getId(), user.getName(), followed);
    }

    private List<com.sprint.be_java_hisp_w23_g04.dtoNew.response.UserDTO> sortedFollow(List<User> follows, String order) {
        if (order.equals("name_asc")) {
            return follows.stream()
                    .map(UserMapper::mapUser)
                    .sorted(Comparator.comparing(com.sprint.be_java_hisp_w23_g04.dtoNew.response.UserDTO::getName))
                    .toList();
        } else {
            return follows.stream()
                    .map(UserMapper::mapUser)
                    .sorted(Comparator.
                            comparing(com.sprint.be_java_hisp_w23_g04.dtoNew.response.UserDTO::getName)
                            .reversed())
                    .toList();
        }

    }

    @Override
    public SimpleMessageDTO unfollowUser(int userId, int unfollowId) {
        return null;
    }
}
