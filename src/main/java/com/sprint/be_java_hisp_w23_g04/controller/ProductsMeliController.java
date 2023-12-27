package com.sprint.be_java_hisp_w23_g04.controller;

import com.sprint.be_java_hisp_w23_g04.dto.request.PostDTO;
import com.sprint.be_java_hisp_w23_g04.gateways.ProductGatewayImp;
import com.sprint.be_java_hisp_w23_g04.service.ISocialMediaService;
import com.sprint.be_java_hisp_w23_g04.service.SocialMediaServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sprint.be_java_hisp_w23_g04.service.IProductMediaService;
import com.sprint.be_java_hisp_w23_g04.service.ProductMediaServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductsMeliController {
    // Dejamos la injeccion del camino bueno. Cambiar esto a medida que se desarrolle el nuevo camino
    private final ISocialMediaService productMediaService;
    private final IProductMediaService service;

    public ProductsMeliController(SocialMediaServiceImpl productMediaService,
                                 ProductMediaServiceImpl service) {
        this.productMediaService = productMediaService;
        this.service = service;
    }


    @PostMapping("/post")
    public ResponseEntity<?> savePost(@RequestBody PostDTO post) {
        return new ResponseEntity<>(productMediaService.savePost(post), HttpStatus.OK);
    }


    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<?> getFilteredPost(@PathVariable Integer userId, @RequestParam(defaultValue = "date_asc") String order) {
        //return new ResponseEntity<>(productMediaService.getFilteredPosts(userId, order), HttpStatus.OK);
        return new ResponseEntity<>(service.getFilteredPosts(userId, order), HttpStatus.OK);
    }
}
