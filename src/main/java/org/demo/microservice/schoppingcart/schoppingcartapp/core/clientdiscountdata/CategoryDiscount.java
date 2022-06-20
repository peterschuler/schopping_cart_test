package org.demo.microservice.schoppingcart.schoppingcartapp.core.clientdiscountdata;

import java.math.BigDecimal;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CategoryDiscount {

    private final ProductCategory category;
    private final BigDecimal discountPercentage;
}
