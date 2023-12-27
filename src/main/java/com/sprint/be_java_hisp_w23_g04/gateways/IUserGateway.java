package com.sprint.be_java_hisp_w23_g04.gateways;

import com.sprint.be_java_hisp_w23_g04.entityNew.User;

public interface IUserGateway {
    User getById(Integer id);
}
