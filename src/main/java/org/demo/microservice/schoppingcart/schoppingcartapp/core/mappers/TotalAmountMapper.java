package org.demo.microservice.schoppingcart.schoppingcartapp.core.mappers;

import org.demo.microservice.schoppingcart.schoppingcartapp.core.TotalAmountResult;
import org.demo.microservice.schoppingcart.schoppingcartapp.core.output.TotalsInformation;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TotalAmountMapper {

    private final MoneyAmountMapper moneyAmountMapper;

    public TotalsInformation map (TotalAmountResult source) {
        return TotalsInformation.builder()
                .totalAmountToBePaid(moneyAmountMapper.map(source.getTotalAmountToBePaid()))
                .totalListPriceAmount(moneyAmountMapper.map(source.getTotalListPriceAmount()))
                .totalSellPriceAmount(moneyAmountMapper.map(source.getTotalSellPriceAmount()))
                .overallDiscountAmountPercentage(source.getTotalDiscountAmount())
                .build();
    }

}
