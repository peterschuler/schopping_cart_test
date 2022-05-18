package org.demo.microservice.schoppingcart.schoppingcartapp.core;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.Product;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ShoppingCartData;
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
        return ShoppingCartOverview.builder()
                .products(mapTo(input.getProducts()))
                .totalAmount(calculateTotal(input.getProducts()))
                .build();
    }

    private BigDecimal calculateTotal(List<Product> products) {
        return products.stream()
                .map(p -> p.getSellPrice().amount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<ProductInformation> mapTo(List<Product> products) {
        return products.stream()
                .map(productInformationMapper::map)
                .collect(Collectors.toList());
    }

}
