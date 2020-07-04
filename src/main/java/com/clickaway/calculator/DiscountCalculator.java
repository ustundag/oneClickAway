package com.clickaway.calculator;

import com.clickaway.service.dto.ShoppingCartIndividualDTO;

import java.math.BigDecimal;

public interface DiscountCalculator {
    BigDecimal calculateDiscount(ShoppingCartIndividualDTO individualCart);
}
