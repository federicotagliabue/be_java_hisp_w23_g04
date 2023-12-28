package com.sprint.be_java_hisp_w23_g04.repository;

import com.sprint.be_java_hisp_w23_g04.entityNew.Product;
import com.sprint.be_java_hisp_w23_g04.entityNew.User;

import java.util.List;

public interface IProductMediaRepository {

    int getNextPostId(User user);

    void savePost(User user);

    List<Product> getByIds(List<Integer> listIds);

    Product getById(Integer id);
}
