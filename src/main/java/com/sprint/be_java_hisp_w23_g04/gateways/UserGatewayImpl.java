package com.sprint.be_java_hisp_w23_g04.gateways;

import com.sprint.be_java_hisp_w23_g04.entityNew.User;
import com.sprint.be_java_hisp_w23_g04.repository.IUserMediaRepository;
import com.sprint.be_java_hisp_w23_g04.repository.UserMediaRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class UserGatewayImpl implements IUserGateway{

    private final IUserMediaRepository userRepository;

    public UserGatewayImpl(UserMediaRepositoryImpl userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User getById(Integer id) {
        return this.userRepository.findUser(id);
    }
}
