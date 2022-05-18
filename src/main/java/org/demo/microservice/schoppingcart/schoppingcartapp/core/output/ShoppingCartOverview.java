package org.demo.microservice.schoppingcart.schoppingcartapp.core.output;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ShoppingCartOverview {

    private final List<ProductInformation> products;

    private final TotalsInformation totals;

    private final BigDecimal totalAmount;
}
