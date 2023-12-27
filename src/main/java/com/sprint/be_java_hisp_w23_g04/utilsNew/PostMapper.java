package com.sprint.be_java_hisp_w23_g04.utilsNew;

import com.sprint.be_java_hisp_w23_g04.dtoNew.request.PostDTO;
import com.sprint.be_java_hisp_w23_g04.dtoNew.response.PostResponseDTO;
import com.sprint.be_java_hisp_w23_g04.entityNew.Post;

public class PostMapper {

    public static PostResponseDTO PostRequestDTOMapper() {
        return new PostResponseDTO();
    }

    /**
     * Maps a received PostDTO to a Post entity
     *
     * @param dto The PostDTO to be mapped
     * @param id  The id of the user who is saving the post
     * @return A Post entity resulting from the mapping process
     */
    public static Post map(PostDTO dto, int id) {
        return new Post(id, dto.getUserId(), dto.getProduct().getId(), dto.getDate(), dto.getCategory(), dto.getPrice());
    }
}
