package org.demo.microservice.schoppingcart.schoppingcartapp.core;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory.TOYS;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductTestBuilder;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ShoppingCartData;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ShoppingCartOverview;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ShoppingCartOverviewServiceTest.TestConfig.class)
class ShoppingCartOverviewServiceTest {

    @Autowired
    private ShoppingCartOverviewService shoppingCartOverviewService;

    @Test
    void should_return_a_single_product_that_was_in_the_input() {

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
        assertThat(output.getProducts().get(0).getDiscountPercentage()).isEqualByComparingTo(valueOf(90.90));

        assertThat(output.getTotals().getTotalAmountToBePaid().amount()).isEqualTo(valueOf(9.99));
        assertThat(output.getTotals().getTotalListPriceAmount().amount()).isEqualTo(valueOf(10.99));
        assertThat(output.getTotals().getTotalSellPriceAmount().amount()).isEqualTo(valueOf(9.99));
        assertThat(output.getTotals().getOverallDiscountAmountPercentage()).isEqualByComparingTo(valueOf(90.90));
    }

    @Test
    void should_return_a_three_products_that_was_in_the_input() {

        ShoppingCartData input = new ShoppingCartData();
        input.addProduct(new ProductTestBuilder()
                .withName("My first book")
                .withListPrice(valueOf(5.99))
                .build());
        input.addProduct(new ProductTestBuilder()
                .withName("My first phone")
                .build());
        input.addProduct(new ProductTestBuilder()
                .withName("My first toy")
                .build());

        ShoppingCartOverview output = shoppingCartOverviewService.generateShoppingCartOverview(input);

        assertThat(output).isNotNull();
        assertThat(output.getProducts()).hasSize(3);
        assertThat(output.getProducts().get(0).getName()).isEqualTo("My first book");
        assertThat(output.getProducts().get(1).getName()).isEqualTo("My first phone");
        assertThat(output.getProducts().get(2).getName()).isEqualTo("My first toy");

        assertThat(output.getTotals().getTotalAmountToBePaid().amount()).isEqualTo(valueOf(1.5));
        assertThat(output.getTotals().getTotalListPriceAmount().amount()).isEqualTo(valueOf(7.99));
        assertThat(output.getTotals().getTotalSellPriceAmount().amount()).isEqualTo(valueOf(1.5));
        assertThat(output.getTotals().getOverallDiscountAmountPercentage()).isEqualByComparingTo(valueOf(18.80));
    }

    @Test
    void test_that_when_a_customer_has_category_reduction_for_toys_these_are_calculated() {
        ShoppingCartData input = new ShoppingCartData();
        input.setCustomerId("customer-1");
        input.addProduct(new ProductTestBuilder()
                .withName("My first book")
                .withListPrice(valueOf(5.99))
                .withCategory(ProductCategory.BOOKS)
                .build());
        input.addProduct(new ProductTestBuilder()
                .withName("My first phone")
                .withCategory(TOYS)
                .withSellPrice(valueOf(100))
                .withListPrice(valueOf(100))
                .build());
        input.addProduct(new ProductTestBuilder()
                .withName("My first toy")
                .withCategory(TOYS)
                .withSellPrice(valueOf(100))
                .withListPrice(valueOf(100))
                .build());

        ShoppingCartOverview output = shoppingCartOverviewService.generateShoppingCartOverview(input);

        assertThat(output).isNotNull();

        assertThat(output.getTotals().getTotalAmountToBePaid().amount()).isEqualTo(valueOf(100.5));
        assertThat(output.getTotals().getTotalListPriceAmount().amount()).isEqualTo(valueOf(205.99));
        assertThat(output.getTotals().getTotalSellPriceAmount().amount()).isEqualTo(valueOf(100.5));
//        assertThat(output.getTotals().getOverallDiscountAmountPercentage()).isEqualByComparingTo(valueOf(97.30));
    }

    @ComponentScan(value = {
            "org.demo.microservice.schoppingcart.schoppingcartapp.core" })
    public static class TestConfig {

    }
}