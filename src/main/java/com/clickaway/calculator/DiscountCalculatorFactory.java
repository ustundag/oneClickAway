package com.clickaway.calculator;

import com.clickaway.types.CalculatorType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscountCalculatorFactory {
    private final ApplicationContext context;

    public DiscountCalculator getDiscountCalculator(CalculatorType calculatorType)
            throws IllegalAccessException, InstantiationException {

        switch(calculatorType){
            case CAMPAIGN:
                return context.getBean(CampaignCalculatorImpl.class);
            case COUPON:
                return context.getBean(CouponCalculatorImpl.class);
        }
        return null;
    }
}
