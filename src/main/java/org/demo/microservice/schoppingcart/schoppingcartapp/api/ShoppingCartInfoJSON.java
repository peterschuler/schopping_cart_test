package org.demo.microservice.schoppingcart.schoppingcartapp.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShoppingCartInfoJSON {

    @NotEmpty
    List<@Valid ProductJson> productInCart;

}
