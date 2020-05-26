package com.clickaway.transformer;

import com.clickaway.calculator.ShoppingCartCalculatorImpl;
import com.clickaway.entity.ShoppingCart;
import com.clickaway.entity.ShoppingCartItem;
import com.clickaway.service.dto.ShoppingCartDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartTransformer {
    private final ShoppingCartCalculatorImpl shoppingCartCalculator;
    private final BigDecimal ZERO = new BigDecimal(0);

    public ShoppingCartDTO transformToShoppingCartDTO(ShoppingCart shoppingCart) {
        List<ShoppingCartItem> cartItems = shoppingCart.getCartItems();
        BigDecimal total = new BigDecimal(0);
        int quantity = 0;

        if (cartItems.size() > 0) {
            total = shoppingCartCalculator.calculateTotal(cartItems);
            quantity = cartItems.stream()
                    .mapToInt(ShoppingCartItem::getQuantity)
                    .sum();
        }

        ShoppingCartDTO shoppingCartDTO = ShoppingCartDTO.builder()
                .categories(new HashMap<>())
                .quantity(quantity)
                .total(total)
                .couponDiscount(ZERO)
                .campaignDiscount(ZERO)
                .deliveryCost(ZERO)
                .finalAmount(ZERO).build();

        shoppingCartDTO.setId(shoppingCart.getId());
        shoppingCartDTO.setTitle(shoppingCart.getTitle());
        return shoppingCartDTO;
    }

}
