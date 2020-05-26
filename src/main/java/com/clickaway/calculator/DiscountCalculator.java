package com.clickaway.calculator;

import java.math.BigDecimal;

public interface DiscountCalculator {
    BigDecimal calculateDiscount(BigDecimal current);
}
