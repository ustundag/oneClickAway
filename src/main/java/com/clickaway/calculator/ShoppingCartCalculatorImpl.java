package com.clickaway.calculator;

import com.clickaway.entity.ShoppingCartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShoppingCartCalculatorImpl {

    public BigDecimal calculateTotal(List<ShoppingCartItem> items) {
        BigDecimal total = new BigDecimal(0);
        total = items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal::add)
                .get();
        return total;
    }

}
