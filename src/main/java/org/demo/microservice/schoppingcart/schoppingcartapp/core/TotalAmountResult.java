package org.demo.microservice.schoppingcart.schoppingcartapp.core;

import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;

@Getter
public class TotalAmountResult {

    private BigDecimal totalAmountToBePaid = BigDecimal.ZERO;

    private BigDecimal totalListPriceAmount = BigDecimal.ZERO;

    private BigDecimal totalSellPriceAmount = BigDecimal.ZERO;

    public void addProductPriceToTotals(ProductResult p) {
        totalAmountToBePaid = totalAmountToBePaid.add(p.getSellPrice().amount());
        totalListPriceAmount = totalListPriceAmount.add(p.getInputProduct().getListPrice().amount());
        totalSellPriceAmount = totalSellPriceAmount.add(p.getSellPrice().amount());
    }

    public BigDecimal getTotalDiscountAmount() {
        return totalSellPriceAmount
                .multiply(valueOf(100))
                .divide(totalListPriceAmount, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

}
