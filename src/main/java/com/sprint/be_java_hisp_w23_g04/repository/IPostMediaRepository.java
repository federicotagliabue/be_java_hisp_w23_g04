package com.sprint.be_java_hisp_w23_g04.repository;


import com.sprint.be_java_hisp_w23_g04.entityNew.Post;

import java.util.List;

public interface IPostMediaRepository {
    List<Post> getByIds(List<Integer> listIds);
}
