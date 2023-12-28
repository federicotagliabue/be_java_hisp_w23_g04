package com.sprint.be_java_hisp_w23_g04.utils;

import com.sprint.be_java_hisp_w23_g04.entityNew.User;
import com.sprint.be_java_hisp_w23_g04.exception.BadRequestException;
import com.sprint.be_java_hisp_w23_g04.exception.NoContentException;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Verifications {

    public static void verifyUserExist(User user, int id) {
        if (user == null) {
            throw new NotFoundException("No se encontró usuario con el id " + id+".");
        }
    }

    //TODO: Eliminar
    /*public static void verifyUserExist(User user) {
        if (user == null) {
            throw new NotFoundException("No se encontró usuario con el id proporcionado.");
        }
    }

    public static void verifyUserExist(com.sprint.be_java_hisp_w23_g04.entityNew.User user) {
        if (user == null) {
            throw new NotFoundException("No se encontró usuario con el id proporcionado.");
        }
    }*/

    public static void verifyUserIsSeller(User seller){
        if (!isSeller(seller)) {
            throw new BadRequestException("El id de usuario vendedor proporcionado no es valido.");
        }
    }
    private static boolean isSeller(User user) {
        return !user.getPostsId().isEmpty();
    }

    public static void verifyUserFollowsSeller(User user, User seller){
        if (userAlreadyFollowsSeller(user, seller)) {
            throw new BadRequestException("El usuario con id:" + user.getId() + " ya sigue al vendedor con id:" + seller.getId());
        }
    }
    private static boolean userAlreadyFollowsSeller(User user, User seller) {
        return user
                .getFollowedId()
                .stream()
                .anyMatch(u ->
                        Objects.equals(u, seller.getId()));
    }

    public static void verifyUserIsFollowed(User user, User unfollowedUser) {
        if(!userHasFollower(user, unfollowedUser)){
            throw new NotFoundException("El usuario que estás intentando dejar de seguir no se encuentra en tu lista de seguidos");
        }
    }

    private static boolean userHasFollower(User user, User unfollowedUser){
        return user.getFollowedId()
                .stream()
                .anyMatch(followed ->
                        Objects.equals(followed, unfollowedUser.getId()));
    }

    public static void verifyUserIsFollower(User unfollowedUser, User user) {
        if( !userHasFollowed(unfollowedUser, user)){
            throw new NotFoundException("No te encuentras en la lista de seguidos del usuario al que estás intentando dejar de seguir. Por favor, comprueba la consistencia de tus datos");
        }
    }
    private static boolean userHasFollowed(User unfollowedUser, User user){
        return unfollowedUser.
                getFollowersId()
                .stream()
                .anyMatch(follower ->
                        Objects.equals(follower, user.getId()));
    }


    public static void verifyUserHasFollowedSellers(User user) {
        if(user.getFollowedId().isEmpty()){
            throw new NotFoundException("El usuario indicado actualmente no sigue a ningún vendedor");
        }
    }

    public static void validateEmptyResponseList(List<?> list) {
        if(list.isEmpty()){
            throw new NoContentException();
        }
    }
}
