package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.response.*;
import com.sprint.be_java_hisp_w23_g04.dtoNew.response.BuyerDTO;
import com.sprint.be_java_hisp_w23_g04.entityNew.User;
import com.sprint.be_java_hisp_w23_g04.exception.NoContentException;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;
import com.sprint.be_java_hisp_w23_g04.gateways.IUserGateway;
import com.sprint.be_java_hisp_w23_g04.gateways.UserGatewayImp;
import com.sprint.be_java_hisp_w23_g04.utilsNew.UserMapper;
import com.sprint.be_java_hisp_w23_g04.utilsNew.Verifications;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserMediaServiceImpl implements IUserMediaService {

    private final IUserGateway userGateway;

    public UserMediaServiceImpl(UserGatewayImp userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }

    @Override
    public SimpleMessageDTO followSellerUser(Integer userId, Integer userIdToFollow) {
        User user = userGateway.findUser(userId);
        User seller = userGateway.findUser(userIdToFollow);
        Verifications.verifyUserExist(user, userId);
        Verifications.verifyUserExist(seller, userIdToFollow);
        Verifications.verifyDistinctsUser(user, seller);
        Verifications.verifyUserIsSeller(seller);
        Verifications.verifyUserFollowsSeller(user, seller);

        seller.getFollowersId().add(userId);
        user.getFollowedId().add(userIdToFollow);
        return new SimpleMessageDTO("El usuario con id:" + userId + " ahora sigue a vendedor con id:" + userIdToFollow);
    }

    public FollowersCountDTO followersCount(Integer userId) {
        return new FollowersCountDTO();
    }

    /**
     * Retrieves and sorts a user's followed sellers based on the specified order.
     *
     * @param userId ID of the user to retrieve followed sellers for.
     * @param order  Sorting order for the list, defaulting to 'name_asc'.
     * @return BuyerDTO with user details and sorted list of followed UserDTOs, or 204 No Content if none are followed.
     * @throws NotFoundException  If the user with the given userId doesn't exist.
     * @throws NoContentException If the user exists but follows no sellers.
     */

    @Override
    public BuyerDTO getFollowedByUserId(Integer userId, String order) {
        User user = this.userGateway.findUser(userId);

        Verifications.verifyUserExist(user, userId);

        List<User> userFollowers = userGateway.getByIds(user.getFollowedId());

        Verifications.validateEmptyResponseList(userFollowers);

        List<com.sprint.be_java_hisp_w23_g04.dtoNew.response.UserDTO> followed = sortedFollow(userFollowers, order);

        return new BuyerDTO(user.getId(), user.getName(), followed);
    }


    /**
     * US-0003 Generate a response object
     *
     * @param userId The ID of the user whose followers are to be retried.
     * @param order  The shorting criteria for the returned list.
     * @return BuyerDTO With information
     */
    @Override
    public BuyerDTO getFollowersByUserId(Integer userId, String order) {
        User user = this.userGateway.findUser(userId);

        Verifications.verifyUserExist(user, userId);

        List<User> userFollowers = userGateway.getByIds(user.getFollowersId());

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
