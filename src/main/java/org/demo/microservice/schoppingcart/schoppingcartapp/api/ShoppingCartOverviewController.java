package org.demo.microservice.schoppingcart.schoppingcartapp.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartOverviewController {

    @RequestMapping("/shop")
    public String shop() {
        return "xx";
    }

}
