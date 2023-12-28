package com.sprint.be_java_hisp_w23_g04.utilsNew;


import com.sprint.be_java_hisp_w23_g04.dtoNew.response.ProductDTO;
import com.sprint.be_java_hisp_w23_g04.entityNew.Product;

public class ProductMapper {

    public static ProductDTO productDTOMapper(Product product){

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getType(),
                product.getBrand(),
                product.getColor(),
                product.getNotes()
        );
    }

}
