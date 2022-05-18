package org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.ProductResult;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.Product;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.MoneyAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductInformationMapperTest {

    private ProductInformationMapper mapper;

    @BeforeEach
    void beforeEach() {
        this.mapper = new ProductInformationMapper();
    }

    @Test
    void test_map() {
        var product = mapper.map(new ProductResult(new Product("id", "id", new MoneyAmount(BigDecimal.ONE), new MoneyAmount(BigDecimal.TEN), ProductCategory.TOYS)));

        assertThat(product.getId()).isEqualTo("id");
    }
}