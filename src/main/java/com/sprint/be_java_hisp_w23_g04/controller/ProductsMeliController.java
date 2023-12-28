package com.sprint.be_java_hisp_w23_g04.controller;

import com.sprint.be_java_hisp_w23_g04.dtoNew.request.PostDTO;
import com.sprint.be_java_hisp_w23_g04.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsMeliController {
    // Dejamos la injeccion del camino bueno. Cambiar esto a medida que se desarrolle el nuevo camino
    private final IProductMediaService productMediaService;
    private final IPostMediaService postMediaService;

    public ProductsMeliController(ProductMediaServiceImpl productMediaService,
                                  PostMediaServiceImpl postMediaService) {
        this.productMediaService = productMediaService;
        this.postMediaService = postMediaService;
    }

    /**
     * US-0005 Saves a new post
     *
     * @param post The post to be saved
     * @return A ResponseEntity containing successfully saving message
     * @throws NotFoundException If the user with de given userId does not exist
     */
    @PostMapping("/post")
    public ResponseEntity<?> savePost(@RequestBody PostDTO post) {
        return new ResponseEntity<>(postMediaService.savePost(post), HttpStatus.OK);
    }


    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<?> getFilteredPost(@PathVariable int userId, @RequestParam(defaultValue = "date_asc") String order) {
        return new ResponseEntity<>(productMediaService.getFilteredPosts(userId, order), HttpStatus.OK);
    }
}
