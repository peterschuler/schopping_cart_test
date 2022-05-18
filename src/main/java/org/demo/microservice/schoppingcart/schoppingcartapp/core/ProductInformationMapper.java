package org.demo.microservice.schoppingcart.schoppingcartapp.core;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.Product;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ProductInformation;
import org.springframework.stereotype.Component;

@Component
public class ProductInformationMapper {

    public ProductInformation map(Product source) {
        return new ProductInformation(source.getName(), source.getId(), source.getListPrice(), source.getSellPrice(), source.getCategory());
    }

}
