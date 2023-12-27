package com.sprint.be_java_hisp_w23_g04.gateways;

import com.sprint.be_java_hisp_w23_g04.entityNew.User;
import com.sprint.be_java_hisp_w23_g04.repository.IUserMediaRepository;
import com.sprint.be_java_hisp_w23_g04.repository.UserMediaRepositoryImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGatewayImp implements IUserGateway {

    private IUserMediaRepository userMediaRepository;

    public UserGatewayImp(UserMediaRepositoryImpl userMediaRepository) {
        this.userMediaRepository = userMediaRepository;
    }

    @Override
    public User findUser(Integer userId) {
        return userMediaRepository.findUser(userId);
    }

    @Override
    public List<User> getByIds(List<Integer> usersId) {
        return userMediaRepository.getByIds(usersId);
    }

    @Override
    public void unfollowUser(int userId, int unfollowId){
        userMediaRepository.unfollowUser(userId, unfollowId);
    }

    @Override
    public List<User> findAll(){
        return userMediaRepository.findAllUsers();
    }
}