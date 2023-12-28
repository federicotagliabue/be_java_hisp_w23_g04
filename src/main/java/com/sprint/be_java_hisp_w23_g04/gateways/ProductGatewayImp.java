package com.sprint.be_java_hisp_w23_g04.gateways;

import com.sprint.be_java_hisp_w23_g04.entityNew.Product;
import com.sprint.be_java_hisp_w23_g04.repository.IProductMediaRepository;
import com.sprint.be_java_hisp_w23_g04.repository.ProductMediaRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class ProductGatewayImp implements  IProductGateway{

    IProductMediaRepository productRepository;

    public ProductGatewayImp(ProductMediaRepositoryImpl productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public Product getById(Integer id) {
        return this.productRepository.getById(id);
    }
}
