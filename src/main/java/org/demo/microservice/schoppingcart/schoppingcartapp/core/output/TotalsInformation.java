package org.demo.microservice.schoppingcart.schoppingcartapp.core.output;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TotalsInformation {

    private final MoneyAmount totalAmountToBePaid;

    private final MoneyAmount totalListPriceAmount;

    private final MoneyAmount totalSellPriceAmount;

    private final BigDecimal overallDiscountAmountPercentage;

}
