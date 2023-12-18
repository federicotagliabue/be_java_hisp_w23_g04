package com.sprint.be_java_hisp_w23_g04.utils;

import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.exception.BadRequestException;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;

import java.util.Objects;

public class Verifications {

    public static void verifyUserExist(User user, int id) {
        if (user == null) {
            throw new NotFoundException("No se encontró usuario con el id " + id+".");
        }
    }

    public static void verifyUserExist(User user) {
        if (user == null) {
            throw new NotFoundException("No se encontró usuario con el id proporcionado.");
        }
    }

    public static void verifyUserIsSeller(User seller){
        if (!isSeller(seller)) {
            throw new BadRequestException("El id de usuario vendedor proporcionado no es valido.");
        }
    }
    private static boolean isSeller(User user) {
        return !user.getPosts().isEmpty();
    }

    public static void verifyUserFollowsSeller(User user, User seller){
        if (userAlreadyFollowsSeller(user, seller)) {
            throw new BadRequestException("El usuario con id:" + user.getId() + " ya sigue al vendedor con id:" + seller.getId());
        }
    }
    private static boolean userAlreadyFollowsSeller(User user, User seller) {
        return user.getFollowed().contains(seller);
    }

    public static void verifyUserIsFollowed(User user, User unfollowedUser) {
        if(userHasFollower(user, unfollowedUser) == null){
            throw new NotFoundException("El usuario que estás intentando dejar de seguir no se encuentra en tu lista de seguidos");
        }
    }

    private static User userHasFollower(User user, User unfollowedUser){
        return user.getFollowed().stream().filter(followed -> Objects.equals(followed.getId(), unfollowedUser.getId())).findAny().orElse(null);
    }

    public static void verifyUserIsFollower(User unfollowedUser, User user) {
        if( userHasFollowed(unfollowedUser, user) == null){
            throw new NotFoundException("No te encuentras en la lista de seguidos del usuario al que estás intentando dejar de seguir. Por favor, comprueba la consistencia de tus datos");
        }
    }
    private static User userHasFollowed(User unfollowedUser, User user){
        return unfollowedUser.getFollowers().stream().filter(follower -> Objects.equals(follower.getId(), user.getId())).findAny().orElse(null);
    }


}
