package org.demo.microservice.schoppingcart.schoppingcartapp.core;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory.TOYS;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductTestBuilder;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ShoppingCartData;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ShoppingCartOverview;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoppingCartOverviewServiceTest {

    private ShoppingCartOverviewService shoppingCartOverviewService;

    @BeforeEach
    void beforeEach() {
        shoppingCartOverviewService = new ShoppingCartOverviewService();
    }

    @Test
    void should_return_a_sinlge_product_that_was_in_the_input() {

        ShoppingCartData input = new ShoppingCartData();
        input.addProduct(new ProductTestBuilder()
                        .withName("does not matter")
                        .withId("prod-id-1")
                        .withListPrice(valueOf(10.99))
                        .withSellPrice(valueOf(9.99))
                .build());

        ShoppingCartOverview output = shoppingCartOverviewService.generateShoppingCartOverview(input);

        assertThat(output).isNotNull();
        assertThat(output.getProducts()).hasSize(1);
        assertThat(output.getProducts().get(0).getName()).isEqualTo("does not matter");
        assertThat(output.getProducts().get(0).getId()).isEqualTo("prod-id-1");
        assertThat(output.getProducts().get(0).getListPrice()).isEqualTo(valueOf(10.99));
        assertThat(output.getProducts().get(0).getSellPrice()).isEqualTo(valueOf(9.99));
        assertThat(output.getProducts().get(0).getProductCategory()).isEqualTo(TOYS);

        assertThat(output.getTotalAmount()).isEqualTo(valueOf(9.99));
    }

    @Test
    void should_return_a_three_products_that_was_in_the_input() {

        ShoppingCartData input = new ShoppingCartData();
        input.addProduct(new ProductTestBuilder()
                .withName("My first book")
                .withSellPrice(valueOf(5.99))
                .build());
        input.addProduct(new ProductTestBuilder()
                .withName("My first phone").build());
        input.addProduct(new ProductTestBuilder()
                .withName("My first toy").build());

        ShoppingCartOverview output = shoppingCartOverviewService.generateShoppingCartOverview(input);

        assertThat(output).isNotNull();
        assertThat(output.getProducts()).hasSize(3);
        assertThat(output.getProducts().get(0).getName()).isEqualTo("My first book");
        assertThat(output.getProducts().get(1).getName()).isEqualTo("My first phone");
        assertThat(output.getProducts().get(2).getName()).isEqualTo("My first toy");
        assertThat(output.getTotalAmount()).isEqualTo(valueOf(6.99))      ;
    }
}