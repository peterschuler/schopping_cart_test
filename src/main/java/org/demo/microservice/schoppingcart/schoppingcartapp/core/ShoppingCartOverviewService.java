package org.demo.microservice.schoppingcart.schoppingcartapp.core;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.Product;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ShoppingCartData;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers.ProductInformationMapper;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ProductInformation;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ShoppingCartOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShoppingCartOverviewService {

    @Autowired
    private final ProductInformationMapper productInformationMapper;

    public ShoppingCartOverview generateShoppingCartOverview(ShoppingCartData input) {

        List<ProductResult> products = toInteralProducts(input);

        return ShoppingCartOverview.builder()
                .products(mapTo(products))
                .totalAmount(calculateTotal(input.getProducts()))
                .build();
    }

    private List<ProductResult> toInteralProducts(ShoppingCartData input) {
        return input.getProducts()
                .stream()
                .map(ProductResult::new)
                .collect(Collectors.toList());
    }

    private BigDecimal calculateTotal(List<Product> products) {
        return products.stream()
                .map(p -> p.getSellPrice().amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<ProductInformation> mapTo(List<ProductResult> products) {
        return products.stream()
                .map(productInformationMapper::map)
                .collect(Collectors.toList());
    }

}
