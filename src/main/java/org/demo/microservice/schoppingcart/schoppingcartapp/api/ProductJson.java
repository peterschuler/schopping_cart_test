package org.demo.microservice.schoppingcart.schoppingcartapp.api;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

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