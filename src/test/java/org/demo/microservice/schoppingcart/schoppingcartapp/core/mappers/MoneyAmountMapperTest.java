package org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MoneyAmountMapperTest {

    @Test
    void test_map() {
        var money = new MoneyAmountMapper().map(ONE);
        Assertions.assertThat(money.amount()).isEqualByComparingTo(valueOf(1.0));
    }
}