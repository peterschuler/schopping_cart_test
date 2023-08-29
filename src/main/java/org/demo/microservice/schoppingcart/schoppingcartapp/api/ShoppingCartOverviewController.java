package org.demo.microservice.schoppingcart.schoppingcartapp.api;

import jakarta.validation.Valid;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.ShoppingCartOverviewService;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.Product;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ShoppingCartData;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.MoneyAmount;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.ShoppingCartOverview;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ShoppingCartOverviewController {

    private final ShoppingCartOverviewService service;

    @PostMapping("/shopcart")
    public ShoppingCartOverview shop(@RequestBody @Valid ShoppingCartInfoJSON shoppingCartInfo ) {


        return service.generateShoppingCartOverview(toData(shoppingCartInfo));
    }

    private ShoppingCartData toData(ShoppingCartInfoJSON shoppingCartInfo) {
        ShoppingCartData data = new ShoppingCartData();

        shoppingCartInfo.getProductInCart().forEach( p -> data.addProduct(Product.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .listPrice(new MoneyAmount(p.getListPrice()))
                        .sellPrice(new MoneyAmount(p.getSellPrice()))
                        .category(toCategory(p.getCategory()))
                .build()
        ));

        return data;
    }

    private org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory toCategory(ProductCategory category) {
        return org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory.valueOf(category.name());
    }

}
