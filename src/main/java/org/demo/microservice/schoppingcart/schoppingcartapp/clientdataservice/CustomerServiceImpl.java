package org.demo.microservice.schoppingcart.schoppingcartapp.clientdataservice;

import java.math.BigDecimal;
import java.util.List;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.clientdiscountdata.CategoryDiscount;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.clientdiscountdata.CustomerData;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.clientdiscountdata.CustomerService;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.input.ProductCategory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerData getCustomerData() {

        RestTemplate restTemplate = new RestTemplate();
        var x = restTemplate.getForObject("http://localhost:8080/b2b/customer/customer-1", Object.class);

            return new CustomerData(List.of(new CategoryDiscount(ProductCategory.TOYS, BigDecimal.valueOf(0.5))));


    }
}
