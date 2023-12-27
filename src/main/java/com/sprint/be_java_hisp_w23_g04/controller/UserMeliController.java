package com.sprint.be_java_hisp_w23_g04.controller;

import com.sprint.be_java_hisp_w23_g04.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserMeliController {

    // Dejamos la injeccion del camino bueno. Cambiar esto a medida que se desarrolle el nuevo camino
    private final ISocialMediaService socialMediaService;
    private final IUserMediaService userMediaService;

    public UserMeliController(
            SocialMediaServiceImpl socialMediaService,
            UserMediaServiceImpl userMediaService
    ) {
        this.socialMediaService = socialMediaService;
        this.userMediaService = userMediaService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(socialMediaService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followSellerUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return new ResponseEntity<>(socialMediaService.followSellerUser(userId, userIdToFollow), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> getFollowers(@PathVariable int userId) {
        return new ResponseEntity<>(socialMediaService.followersCount(userId), HttpStatus.OK);
    }

    // US-0003 -> Should be return followers of user id
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<?> getAllFollowersByUserId(@PathVariable Integer userId,
                                                     @RequestParam(defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(userMediaService.getFollowersByUserId(userId, order), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> getFollowedByUserId(@PathVariable Integer userId,
                                                 @RequestParam(defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(socialMediaService.getFollowedByUserId(userId, order), HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollowUser(@PathVariable int userId, @PathVariable int userIdToUnfollow) {
        return new ResponseEntity<>(socialMediaService.unfollowUser(userId, userIdToUnfollow), HttpStatus.ACCEPTED);
    }
}
