package org.demo.microservice.schoppingcart.schoppingcartapp.api;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductJson {

    @NotEmpty
    private final String name;

    @NotEmpty
    private final String id;

    @PositiveOrZero
    @NotNull
    private final BigDecimal listPrice;

    @PositiveOrZero
    @NotNull
    private final BigDecimal sellPrice;

    @NotNull
    private final ProductCategory category;
}