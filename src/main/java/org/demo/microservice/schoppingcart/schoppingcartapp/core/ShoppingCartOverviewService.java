package org.demo.microservice.schoppingcart.schoppingcartapp.core;

import java.util.List;
import java.util.stream.Collectors;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.Product;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ShoppingCartData;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ProductInformation;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ShoppingCartOverview;

public class ShoppingCartOverviewService {

    public ShoppingCartOverview generateShoppingCartOverview(ShoppingCartData input) {
        ShoppingCartOverview shoppingCartOverview = new ShoppingCartOverview(mapTo(input.getProducts()));

        return shoppingCartOverview;
    }

    private List<ProductInformation> mapTo(List<Product> products) {
        return products.stream()
                .map(p -> {
                    return new ProductInformation();
                })
                .collect(Collectors.toList());
    }

}
