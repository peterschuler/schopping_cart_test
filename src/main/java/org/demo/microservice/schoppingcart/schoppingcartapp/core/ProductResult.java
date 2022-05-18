package org.demo.microservice.schoppingcart.schoppingcartapp.core;

import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.Product;

public class ProductResult {

    private final Product inputProduct;

    public ProductResult(Product originalInput) {
        this.inputProduct = originalInput;
    }

    public Product getInputProduct() {
        return inputProduct;
    }

    public BigDecimal calculateDiscountPercentage() {
        return inputProduct.getSellPrice().amount()
                .multiply(valueOf(100))
                .divide(inputProduct.getListPrice().amount(), RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}
