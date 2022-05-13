package org.demo.microservice.schoppingcart.schoppingcartapp.core.input;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.MoneyAmount;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {

    private final String name;

    private final String id;

    private final MoneyAmount listPrice;

    private final MoneyAmount sellPrice;

    private final ProductCategory category;
}
