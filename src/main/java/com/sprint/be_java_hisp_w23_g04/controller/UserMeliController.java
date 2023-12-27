package com.sprint.be_java_hisp_w23_g04.controller;

import com.sprint.be_java_hisp_w23_g04.exception.NoContentException;
import com.sprint.be_java_hisp_w23_g04.exception.NotFoundException;
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

<<<<<<< HEAD
    /**
     * US-0003 Return all followers of a user.
     * <p>
     * With the order parameter we can sort them descendingly and ascendingly.
     *
     * @param userId The ID of the user whose followers are to be retried.
     * @param order  The shorting criteria for the returned list. Defaults to 'name_asc'.
     * @return A ResponseEntity containing the sorted list of followers
     * @throws NotFoundException If the user with the given userId does not exist.
     * @throws NoContentException If the user exists but no have followers.
     */
=======
    // US-0003 -> Should be return followers of user id
>>>>>>> ab241c945a9f05ae3f27e58cd1347a9778825b6a
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
