package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.dto.request.PostDTO;
import com.sprint.be_java_hisp_w23_g04.dto.response.*;

import java.util.List;

public interface IProductMediaService {

    SimpleMessageDTO savePost(PostDTO post);

    FilteredPostsDTO getFilteredPosts(int userId, String order);
}
