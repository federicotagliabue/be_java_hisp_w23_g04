package com.sprint.be_java_hisp_w23_g04.controller;

import org.springframework.web.bind.annotation.RestController;
import com.sprint.be_java_hisp_w23_g04.service.ISocialMediaService;
import com.sprint.be_java_hisp_w23_g04.service.SocialMediaServiceImpl;

@RestController
public class SocialMeliController {

    private final ISocialMediaService socialMediaService;

    public SocialMeliController(SocialMediaServiceImpl socialMediaService){
        this.socialMediaService = socialMediaService;
    }
}
