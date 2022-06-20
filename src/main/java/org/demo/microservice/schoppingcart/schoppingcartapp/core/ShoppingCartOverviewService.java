package org.demo.microservice.schoppingcart.schoppingcartapp.core;

import java.util.List;
import java.util.stream.Collectors;

import org.demo.microservice.schoppingcart.schoppingcartapp.clientdataservice.CustomerServiceImpl;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.clientdiscountdata.CategoryDiscount;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.clientdiscountdata.CustomerData;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.clientdiscountdata.CustomerService;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ShoppingCartData;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers.ProductInformationMapper;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers.TotalAmountMapper;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ProductInformation;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ShoppingCartOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShoppingCartOverviewService {

    private final ProductInformationMapper productsInformationMapper;

    private final CustomerService customerService = new CustomerServiceImpl();

    private final TotalAmountMapper totalAmountMapper;

    public ShoppingCartOverview generateShoppingCartOverview(ShoppingCartData input) {

        List<ProductResult> products = toInteralProducts(input);

        CustomerData d;

        if (input.customerId() != null) {
            d = customerService.getCustomerData();

            for (CategoryDiscount categoryDiscount : d.getCategoryDiscountList()) {
                products.stream()
                        .filter(p -> p.getInputProduct().getCategory().equals(categoryDiscount.getCategory()))
                        .forEach(p -> p.setCategoryDiscount(categoryDiscount.getDiscountPercentage()));
            }
        }

        TotalAmountResult
                totalAmountResult = new TotalAmountResult();

        products.forEach(totalAmountResult::addProductPriceToTotals);

        return ShoppingCartOverview.builder()
                .products(toProducts(products))
                .totals(totalAmountMapper.map(totalAmountResult))
                .build();
    }

    private List<ProductInformation> toProducts(List<ProductResult> products) {
        return products.stream()
                .map(productsInformationMapper::map)
                .collect(Collectors.toList());
    }

    private List<ProductResult> toInteralProducts(ShoppingCartData input) {
        return input.getProducts()
                .stream()
                .map(ProductResult::new)
                .collect(Collectors.toList());
    }
}
