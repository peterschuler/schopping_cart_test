package org.demo.microservice.schoppingcart.schoppingcartapp.core.output;

import java.math.BigDecimal;

public class MoneyAmount {

    private final BigDecimal amount;

    public MoneyAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal amount() {
        return amount;
    }
}
