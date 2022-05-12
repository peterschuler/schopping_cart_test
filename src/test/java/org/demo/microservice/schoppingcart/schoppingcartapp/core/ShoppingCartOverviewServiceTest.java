package org.demo.microservice.schoppingcart.schoppingcartapp.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.Product;
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
    void should_return_a_product_that_was_in_the_input() {

        ShoppingCartData input = new ShoppingCartData();
        input.addProduct(new Product());

        ShoppingCartOverview output = shoppingCartOverviewService.generateShoppingCartOverview(input);

        assertThat(output).isNotNull();
        assertThat(output.getProducts()).isNotEmpty();
    }



}