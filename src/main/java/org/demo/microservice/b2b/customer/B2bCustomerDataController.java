package org.demo.microservice.b2b.customer;

import static java.util.List.of;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class B2bCustomerDataController {

    @GetMapping("/b2b/customer/{customerId}")
    @ResponseBody
    public CustomerInfo shop(@PathVariable  String customerId) {
        return data().get(customerId);
    }

    private Map<String, CustomerInfo> data() {
        Map<String, CustomerInfo> data = new HashMap<>();
        data.put("customer-1", CustomerInfo.builder()
                        .id("customer-1")
                        .reductions(of(new ReductionInfo(ProductCategory.TOYS, 2.0)))
                .build());
        return data;
    }

}
