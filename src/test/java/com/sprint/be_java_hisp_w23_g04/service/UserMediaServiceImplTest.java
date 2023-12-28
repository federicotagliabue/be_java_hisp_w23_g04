package com.sprint.be_java_hisp_w23_g04.service;

import com.sprint.be_java_hisp_w23_g04.gateway.UserGatewayImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserMediaServiceImplTest {
    @Mock
    UserGatewayImpl userGateway;

    @InjectMocks
    UserMediaServiceImpl userService;
}
