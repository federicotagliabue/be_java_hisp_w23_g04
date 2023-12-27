package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dtoNew.response.*;
import com.sprint.be_java_hisp_w23_g04.entityNew.User;
import com.sprint.be_java_hisp_w23_g04.gateways.IUserGateway;
import com.sprint.be_java_hisp_w23_g04.gateways.UserGatewayImp;
import com.sprint.be_java_hisp_w23_g04.utilsNew.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.sprint.be_java_hisp_w23_g04.utils.Verifications.*;

@Service
public class UserMediaServiceImpl implements IUserMediaService {

    private final IUserGateway userGateway;

    public UserMediaServiceImpl(UserGatewayImp userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userGateway.findAll()
                .stream()
                .map(UserMapper::mapUser)
                .toList();
    }

    @Override
    public SimpleMessageDTO followSellerUser(Integer userId, Integer userIdToFollow) {
        return new SimpleMessageDTO("El usuario con id:" + userId + " ahora sigue a vendedor con id:" + userIdToFollow);
    }

    public Integer followersCount(Integer userId) {
        return userGateway.findUser(userId).getFollowersId().size();
    }

    @Override
    public UserDTO getFollowedByUserId(Integer id, String order) {
        return new UserDTO();
    }

    private List<UserDTO> sortedFollow(List<User> follows, String order) {
        return null;
    }

    @Override
    public UserDTO getFollowersByUserId(int userId, String order) {
        return null;
    }

    @Override
    public SimpleMessageDTO unfollowUser(int userId, int unfollowId) {
        User user = userGateway.findUser(userId);
        User unfollowedUser = userGateway.findUser(unfollowId);

        verifyUserExist(user, userId);
        verifyUserExist(unfollowedUser, unfollowId);
        verifyUserIsFollowed(user, unfollowedUser);
        verifyUserIsFollower(unfollowedUser, user);

        userGateway.unfollowUser(userId, unfollowId);

        return new SimpleMessageDTO("El usuario " + unfollowedUser.getName() + " Id: " + unfollowedUser.getId() + " ya no es seguido por el usuario " + user.getName() + " Id: " + user.getId());
    }
}
