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

    public BigDecimal getAmount() {
        return amount();
    }

    public MoneyAmount withDiscount(BigDecimal categoryDiscount) {
        if (categoryDiscount == null) {
            return this;
        }
        return new MoneyAmount(amount.multiply(categoryDiscount));
    }
}
