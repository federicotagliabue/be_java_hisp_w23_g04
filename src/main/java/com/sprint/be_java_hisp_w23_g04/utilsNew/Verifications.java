package com.sprint.be_java_hisp_w23_g04.utilsNew;

import com.sprint.be_java_hisp_w23_g04.entityNew.User;
import com.sprint.be_java_hisp_w23_g04.exception.NoContentException;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;

import java.util.List;

public class Verifications {
    public static void verifyUserExist(User user, Integer id) {
        if (user == null) {
            throw new NotFoundException("No se encontró usuario con el id " + id + ".");
        }
    }

    public static void validateEmptyResponseList(List<?> list) {
        if (list.isEmpty()) {
            throw new NoContentException();
        }
    }
}
