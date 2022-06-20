package org.demo.microservice.schoppingcart.schoppingcartapp.core.clientdiscountdata;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CustomerData {

    List<CategoryDiscount> categoryDiscountList;

    public CustomerData(List<CategoryDiscount> categoryDiscountList) {
        this.categoryDiscountList = categoryDiscountList;
    }
}
