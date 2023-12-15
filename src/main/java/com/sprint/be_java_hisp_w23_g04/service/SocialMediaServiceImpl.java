package com.sprint.be_java_hisp_w23_g04.service;

import org.springframework.stereotype.Service;
import com.sprint.be_java_hisp_w23_g04.entity.User;
import com.sprint.be_java_hisp_w23_g04.repository.ISocialMediaRepository;
import com.sprint.be_java_hisp_w23_g04.repository.SocialMediaRepositoryImpl;

@Service
public class SocialMediaServiceImpl implements ISocialMediaService {

    private final ISocialMediaRepository socialMediaRepository;

    public SocialMediaServiceImpl(SocialMediaRepositoryImpl socialMediaRepository){
        this.socialMediaRepository = socialMediaRepository;
    }

    private boolean isSeller(User user){
        return !user.getPosts().isEmpty();
    }
}
