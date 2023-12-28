package com.sprint.be_java_hisp_w23_g04.gateways;

import com.sprint.be_java_hisp_w23_g04.entityNew.Post;
import com.sprint.be_java_hisp_w23_g04.entityNew.User;

import java.util.List;

public interface IPostGateway {
    List<Post> getByIds(List<Integer> usersId);
}
