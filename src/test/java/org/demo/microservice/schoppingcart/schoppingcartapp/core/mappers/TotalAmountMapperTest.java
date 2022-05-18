package org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers;

import static java.math.BigDecimal.valueOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.ProductResult;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.TotalAmountResult;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.Product;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.MoneyAmount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TotalAmountMapperTest {

    @Mock()
    private MoneyAmountMapper moneyAmountMapperMock;

    @InjectMocks
    private TotalAmountMapper mapper;

    @Test
    void test_map_with_two_product_in_total() {
        TotalAmountResult totalAmountResult = new TotalAmountResult();

        totalAmountResult.addProductPriceToTotals(new ProductResult(new Product("id", "id", new MoneyAmount(BigDecimal.ONE), new MoneyAmount(BigDecimal.TEN), ProductCategory.TOYS)));
        totalAmountResult.addProductPriceToTotals(new ProductResult(new Product("id", "id", new MoneyAmount(BigDecimal.ONE), new MoneyAmount(BigDecimal.TEN), ProductCategory.TOYS)));

        when(moneyAmountMapperMock.map(any())).thenReturn(new MoneyAmount(valueOf(20)));

        var result = mapper.map(totalAmountResult);

        Assertions.assertThat(result.getTotalSellPriceAmount().amount()).isEqualByComparingTo(valueOf(20));
        Assertions.assertThat(result.getTotalListPriceAmount().amount()).isEqualByComparingTo(valueOf(20));
        Assertions.assertThat(result.getTotalSellPriceAmount().amount()).isEqualByComparingTo(valueOf(20));
        Assertions.assertThat(result.getOverallDiscountAmountPercentage()).isEqualByComparingTo(valueOf(1000));
    }

    @Test
    void test_map_with_two_product_in_totals(@Mock TotalAmountResult result) {

        when(result.getTotalListPriceAmount()).thenReturn(valueOf(1));
        when(result.getTotalSellPriceAmount()).thenReturn(valueOf(2));
        when(result.getTotalAmountToBePaid()).thenReturn(valueOf(3));
        when(result.getTotalDiscountAmount()).thenReturn(valueOf(4));

        lenient().when(moneyAmountMapperMock.map(eq(valueOf(1)))).thenReturn(new MoneyAmount(valueOf(1)));
        lenient().when(moneyAmountMapperMock.map(eq(valueOf(2)))).thenReturn(new MoneyAmount(valueOf(2)));
        lenient().when(moneyAmountMapperMock.map(eq(valueOf(3)))).thenReturn(new MoneyAmount(valueOf(3)));
        lenient().when(moneyAmountMapperMock.map(eq(valueOf(4)))).thenReturn(new MoneyAmount(valueOf(4)));

        var info = mapper.map(result);

        Assertions.assertThat(info.getTotalSellPriceAmount().amount()).isEqualByComparingTo(valueOf(2));
        Assertions.assertThat(info.getTotalListPriceAmount().amount()).isEqualByComparingTo(valueOf(1));
        Assertions.assertThat(info.getTotalAmountToBePaid().amount()).isEqualByComparingTo(valueOf(3));
        Assertions.assertThat(info.getOverallDiscountAmountPercentage()).isEqualByComparingTo(valueOf(4));
    }

}