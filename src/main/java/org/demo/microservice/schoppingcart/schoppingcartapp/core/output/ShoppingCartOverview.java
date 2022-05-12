package org.demo.microservice.schoppingcart.schoppingcartapp.core.output;

import java.util.List;

public class ShoppingCartOverview {

    List<ProductInformation> productInformation;

    public ShoppingCartOverview(List<ProductInformation> productInformation) {
        this.productInformation = productInformation;
    }

    public List<ProductInformation> getProducts() {
        return productInformation;
    }
}
