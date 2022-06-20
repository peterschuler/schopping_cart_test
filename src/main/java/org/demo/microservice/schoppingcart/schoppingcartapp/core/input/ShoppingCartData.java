package org.demo.microservice.schoppingcart.schoppingcartapp.core.input;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartData {

    private List<Product> products = new ArrayList<>();

    private String customerId;

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return List.copyOf(products);
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String customerId() {
        return customerId;
    }
}
