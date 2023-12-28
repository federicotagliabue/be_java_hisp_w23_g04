package com.sprint.be_java_hisp_w23_g04.controller;

import com.sprint.be_java_hisp_w23_g04.service.UserMediaServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserMeliControllerTest {

    @Mock
    UserMediaServiceImpl userService;

    @InjectMocks
    ProductsMeliController controller;
}
