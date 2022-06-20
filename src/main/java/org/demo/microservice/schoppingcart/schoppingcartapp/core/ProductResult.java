package org.demo.microservice.schoppingcart.schoppingcartapp.core;

import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.Product;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.MoneyAmount;

public class ProductResult {

    private final Product inputProduct;

    private BigDecimal categoryDiscount;

    public ProductResult(Product originalInput) {
        this.inputProduct = originalInput;
    }

    public Product getInputProduct() {
        return inputProduct;
    }

    public MoneyAmount getSellPrice() {
        return inputProduct.getSellPrice().withDiscount(categoryDiscount);
    }

    public BigDecimal calculateDiscountPercentage() {
        return getSellPrice().amount()
                .multiply(valueOf(100))
                .divide(inputProduct.getListPrice().amount(), RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

    public void setCategoryDiscount(BigDecimal categoryDiscount) {
        this.categoryDiscount = categoryDiscount;
    }

}
