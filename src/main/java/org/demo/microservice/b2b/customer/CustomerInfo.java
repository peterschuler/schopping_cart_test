package org.demo.microservice.b2b.customer;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerInfo {

    private final String id;
    private final List<ReductionInfo> reductions;

}
