package com.clickaway.calculator;

import com.clickaway.constant.Constant;
import com.clickaway.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DeliveryCostCalculatorImpl {
    private final ShoppingCartRepository shoppingCartRepository;

    public BigDecimal calculateDeliveryCost() {
        int numOfDeliveries = shoppingCartRepository.countDistinctByCategoryID();
        int numOfProducts = shoppingCartRepository.numberOfProductQuantity();

        BigDecimal cargoPrice = Constant.COST_PER_DELIVERY
                .multiply(BigDecimal.valueOf(numOfDeliveries));

        BigDecimal packagePrice = Constant.COST_PER_PRODUCT
                .multiply(BigDecimal.valueOf(numOfProducts));

        return cargoPrice.add(packagePrice).add(Constant.FIXED_COST);
    }


}
