package org.demo.microservice.schoppingcart.schoppingcartapp.core.input;

import static java.math.BigDecimal.valueOf;
import static org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory.TOYS;

import java.math.BigDecimal;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.MoneyAmount;

public class ProductTestBuilder {

    private ProductCategory category = TOYS;

    private String name = "test_name";

    private String id = "test_id_1";

    private BigDecimal listPrice = BigDecimal.ONE;

    private BigDecimal sellPrice = valueOf(0.5);

    public Product build() {
        return new Product(name, id, new MoneyAmount(listPrice), new MoneyAmount(sellPrice), category);
    }

    public ProductTestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductTestBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ProductTestBuilder withListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
        return this;
    }

    public ProductTestBuilder withSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
        return this;
    }

    public ProductTestBuilder setCategory(ProductCategory category) {
        this.category = category;
        return this;
    }
}
