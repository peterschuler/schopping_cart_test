package org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.ProductResult;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.Product;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ProductInformation;
import org.springframework.stereotype.Component;

@Component
public class ProductInformationMapper {

    public ProductInformation map(ProductResult source) {
            return mapToProduct(source);
     }

    ProductInformation mapToProduct(ProductResult source) {
        Product original = source.getInputProduct();
        return new ProductInformation(original.getName(), original.getId(), original.getListPrice(), original.getSellPrice(), original.getCategory(), source.calculateDiscountPercentage());
    }

}
