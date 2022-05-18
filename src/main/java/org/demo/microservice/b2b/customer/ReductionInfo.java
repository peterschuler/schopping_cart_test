package org.demo.microservice.b2b.customer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReductionInfo {
    private final ProductCategory category;
    private final Double reduction;
}
