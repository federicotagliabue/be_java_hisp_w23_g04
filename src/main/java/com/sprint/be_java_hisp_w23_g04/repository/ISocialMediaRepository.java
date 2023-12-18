package com.sprint.be_java_hisp_w23_g04.repository;

import com.sprint.be_java_hisp_w23_g04.entity.User;

import java.util.List;

public interface ISocialMediaRepository {
    List<User> findAllUsers();

    User findUserById(int id);
}
