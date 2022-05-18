package org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.ProductResult;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.Product;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ProductInformation;
import org.springframework.stereotype.Component;

@Component
public class ProductsInformationMapper {

    public List<ProductInformation> map(List<ProductResult> source) {
            return source.stream()
                    .map(this::mapToProduct)
                    .collect(Collectors.toList());
     }

    ProductInformation mapToProduct(ProductResult source) {
        Product original = source.getInputProduct();
        return new ProductInformation(original.getName(), original.getId(), original.getListPrice(), original.getSellPrice(), original.getCategory(), source.calculateDiscountPercentage());
    }

}
