package org.demo.microservice.schoppingcart.schoppingcartapp.core.clientdiscountdata;

import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    CustomerData getCustomerData();
}
