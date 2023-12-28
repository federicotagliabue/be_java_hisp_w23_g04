package com.sprint.be_java_hisp_w23_g04.service;


import com.sprint.be_java_hisp_w23_g04.dtoNew.response.PostDTO;
import com.sprint.be_java_hisp_w23_g04.dtoNew.response.PostListDTO;
import com.sprint.be_java_hisp_w23_g04.dtoNew.response.SimpleMessageDTO;

public interface IProductMediaService {

    SimpleMessageDTO savePost(PostDTO post);

    PostListDTO getFilteredPosts(int userId, String order);
}
