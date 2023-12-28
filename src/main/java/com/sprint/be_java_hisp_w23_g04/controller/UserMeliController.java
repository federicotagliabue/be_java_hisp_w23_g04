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

    /**
     * User follows Seller, Seller adds new Follower and User add new Followed.
     *
     * @param userId The ID of the User who adds new Followed.
     * @param userIdToFollow  The ID of the Seller who adds new Follower.
     * @return A ResponseEntity containing the success message.
     */
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followSellerUser(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        return new ResponseEntity<>(userMediaService.followSellerUser(userId, userIdToFollow), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> getFollowers(@PathVariable int userId) {
        return new ResponseEntity<>(userMediaService.followersCount(userId), HttpStatus.OK);
    }

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
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<?> getAllFollowersByUserId(@PathVariable Integer userId,
                                                     @RequestParam(defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(userMediaService.getFollowersByUserId(userId, order), HttpStatus.OK);
    }

    /**
     * Retrieves a list of sellers followed by a given user, sorted based on the specified order.
     *
     * @param userId The ID of the user whose followed sellers are to be retrieved.
     * @param order  The sorting criteria for the returned list (e.g., 'name_asc'). Defaults to 'name_asc'.
     * @return A ResponseEntity containing the sorted list of followed sellers or 204 No Content if none are followed.
     * @throws NotFoundException  If the user with the given userId does not exist.
     * @throws NoContentException If the user exists but follows no sellers.
     */
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> getFollowedByUserId(@PathVariable Integer userId,
                                                 @RequestParam(defaultValue = "name_asc") String order) {
        return new ResponseEntity<>(userMediaService.getFollowedByUserId(userId, order), HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollowUser(@PathVariable int userId, @PathVariable int userIdToUnfollow) {
        return new ResponseEntity<>(socialMediaService.unfollowUser(userId, userIdToUnfollow), HttpStatus.ACCEPTED);
    }
}
