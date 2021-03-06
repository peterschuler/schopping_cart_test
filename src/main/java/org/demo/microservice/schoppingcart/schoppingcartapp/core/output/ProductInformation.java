package org.demo.microservice.schoppingcart.schoppingcartapp.core.output;

import java.math.BigDecimal;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory;

public class ProductInformation {

    private final String name;

    private final String id;

    private final MoneyAmount listPrice;

    private final MoneyAmount sellPrice;

    private final ProductCategory productCategory;

    private final BigDecimal discountPercentage;

    public ProductInformation(String name, String id, MoneyAmount listPrice, MoneyAmount sellPrice, ProductCategory productCategory, BigDecimal discountPercentage) {
        this.name = name;
        this.id = id;
        this.listPrice = listPrice;
        this.sellPrice = sellPrice;
        this.productCategory = productCategory;
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getListPrice() {
        return listPrice.amount();
    }

    public BigDecimal getSellPrice() {
        return sellPrice.amount();
    }

    public String getName() {
        return name;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }
}
