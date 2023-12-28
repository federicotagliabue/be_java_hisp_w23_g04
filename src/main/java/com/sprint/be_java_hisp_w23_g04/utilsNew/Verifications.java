package com.sprint.be_java_hisp_w23_g04.utilsNew;

import com.sprint.be_java_hisp_w23_g04.entityNew.User;
import com.sprint.be_java_hisp_w23_g04.exception.NoContentException;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;

import java.util.List;

public class Verifications {

    /**
     * Checks if a user exists and throws NotFoundException if not.
     *
     * @param user The user to check for existence.
     * @param id   The user's ID, used for the error message if the user doesn't exist.
     * @throws NotFoundException if the user is null.
     */
    public static void verifyUserExist(User user, Integer id) {
        if (user == null) {
            throw new NotFoundException("No se encontró usuario con el id " + id + ".");
        }
    }

    /**
     * Validates that a given list is not empty. Throws NoContentException if the list is empty.
     *
     * @param list The list to check for content.
     * @throws NoContentException if the list is empty.
     */
    public static void validateEmptyResponseList(List<?> list) {
        if (list.isEmpty()) {
            throw new NoContentException();
        }
    }

    public static void verifyUserHasFollowedSellers(com.sprint.be_java_hisp_w23_g04.entityNew.User user) {
        if(user.getFollowedId().isEmpty()){
            throw new NotFoundException("El usuario indicado actualmente no sigue a ningún vendedor");
        }
    }
}
