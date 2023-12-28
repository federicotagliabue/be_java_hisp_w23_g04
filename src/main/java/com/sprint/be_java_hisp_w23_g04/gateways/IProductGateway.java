package com.sprint.be_java_hisp_w23_g04.gateways;

import com.sprint.be_java_hisp_w23_g04.entityNew.Product;

public interface IProductGateway {

    Product getById(Integer id);
}
