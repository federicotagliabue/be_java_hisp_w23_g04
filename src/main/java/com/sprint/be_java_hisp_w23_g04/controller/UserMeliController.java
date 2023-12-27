package com.sprint.be_java_hisp_w23_g04.controller;

import com.sprint.be_java_hisp_w23_g04.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserMeliController {

    // Dejamos la injeccion del camino bueno. Cambiar esto a medida que se desarrolle el nuevo camino
    private final IUserMediaService userMediaService;

    public UserMeliController(IUserMediaService userMediaService) {
        this.userMediaService = userMediaService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userMediaService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followSellerUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return new ResponseEntity<>(userMediaService.followSellerUser(userId, userIdToFollow), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> getFollowers(@PathVariable int userId) {
        return new ResponseEntity<>(userMediaService.followersCount(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<?> getAllFollowersByUserId(@PathVariable int userId,
                                                     @RequestParam(defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(userMediaService.getFollowersByUserId(userId, order), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> getFollowedByUserId(@PathVariable Integer userId,
                                                 @RequestParam(defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(userMediaService.getFollowedByUserId(userId, order), HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollowUser(@PathVariable int userId, @PathVariable int userIdToUnfollow) {
        return new ResponseEntity<>(userMediaService.unfollowUser(userId, userIdToUnfollow), HttpStatus.ACCEPTED);
    }
}
