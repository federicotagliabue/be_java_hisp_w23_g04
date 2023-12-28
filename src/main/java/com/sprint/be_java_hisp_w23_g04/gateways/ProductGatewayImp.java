package com.sprint.be_java_hisp_w23_g04.gateways;

import com.sprint.be_java_hisp_w23_g04.entity.Product;
import com.sprint.be_java_hisp_w23_g04.repository.IProductMediaRepository;
import com.sprint.be_java_hisp_w23_g04.repository.ProductMediaRepositoryImpl;
import org.springframework.stereotype.Component;

@Component
public class ProductGatewayImp implements  IProductGateway{

    IProductMediaRepository productRepository;

    public ProductGatewayImp(ProductMediaRepositoryImpl productRepository){
        this.productRepository = productRepository;
    }

    /**
     * Get posts by list ids
     *
     * @param id id of the product to get.
     * @return the Product
     **/
    @Override
    public Product getById(Integer id) {
        return this.productRepository.getById(id);
    }
}
