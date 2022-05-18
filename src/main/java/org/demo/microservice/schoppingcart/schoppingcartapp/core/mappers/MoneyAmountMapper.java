package org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers;

import java.math.BigDecimal;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.MoneyAmount;
import org.springframework.stereotype.Component;

@Component
public class MoneyAmountMapper {

    public MoneyAmount map(BigDecimal amount) {
        return new MoneyAmount(amount);
    }

}
