package org.demo.microservice.schoppingcart.schoppingcartapp.core;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory.BOOKS;
import static org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory.TOYS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductTestBuilder;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ShoppingCartData;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers.MoneyAmountMapper;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers.ProductInformationMapper;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers.TotalAmountMapper;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.MoneyAmount;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ProductInformation;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.TotalsInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShoppingCartOverviewServiceMockTest {

    @Mock
    ProductInformationMapper productInformationMapper;

    @Mock
    TotalAmountMapper totalAmountMapper;

    @InjectMocks
    ShoppingCartOverviewService service;

    @Test
    void should_return_a_single_product_that_was_in_the_input() {

        ShoppingCartData input = new ShoppingCartData();
        input.addProduct(new ProductTestBuilder().withName("does not matter")
                .withId("prod-id-1")
                .withListPrice(valueOf(10.99))
                .withSellPrice(valueOf(9.99))
                .build());

        when(productInformationMapper.map(any())).thenReturn(new ProductInformation("does not matter", "prod-id-1", new MoneyAmount(BigDecimal.TEN), new MoneyAmount(BigDecimal.ONE), BOOKS,  valueOf(20)));
        when(totalAmountMapper.map(any())).thenReturn(TotalsInformation.builder()
                .totalAmountToBePaid(new MoneyAmount(valueOf(1)))
                .totalListPriceAmount(new MoneyAmount(valueOf(2)))
                .totalSellPriceAmount(new MoneyAmount(valueOf(3)))
                .overallDiscountAmountPercentage(valueOf(5))
                .build());

        var output = service.generateShoppingCartOverview(input);

        assertThat(output).isNotNull();
        assertThat(output.getProducts()).hasSize(1);
        assertThat(output.getProducts().get(0).getName()).isEqualTo("does not matter");
        assertThat(output.getProducts().get(0).getId()).isEqualTo("prod-id-1");
        assertThat(output.getProducts().get(0).getListPrice()).isEqualTo(valueOf(10));
        assertThat(output.getProducts().get(0).getSellPrice()).isEqualTo(valueOf(1));
        assertThat(output.getProducts().get(0).getProductCategory()).isEqualTo(BOOKS);
        assertThat(output.getProducts().get(0).getDiscountPercentage()).isEqualByComparingTo(valueOf(20));

        assertThat(output.getTotals().getTotalAmountToBePaid().amount()).isEqualTo(valueOf(1));
        assertThat(output.getTotals().getTotalListPriceAmount().amount()).isEqualTo(valueOf(2));
        assertThat(output.getTotals().getTotalSellPriceAmount().amount()).isEqualTo(valueOf(3));
        assertThat(output.getTotals().getOverallDiscountAmountPercentage()).isEqualByComparingTo(valueOf(5));

    }

}