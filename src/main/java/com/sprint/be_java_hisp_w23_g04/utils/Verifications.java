package com.sprint.be_java_hisp_w23_g04.utils;

import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;

public class Verifications {

    public static void verifyUserExist(User user) {
        if (user == null) {
            throw new NotFoundException("No se encontró usuario con el id proporcionado.");
        }
    }
}
