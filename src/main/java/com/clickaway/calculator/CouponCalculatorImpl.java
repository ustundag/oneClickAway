package com.clickaway.calculator;

import com.clickaway.constant.Constant;
import com.clickaway.entity.Coupon;
import com.clickaway.repository.CouponRepository;
import com.clickaway.service.dto.ShoppingCartIndividualDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CouponCalculatorImpl implements DiscountCalculator {
    private final CouponRepository couponRepository;

    @Override
    public BigDecimal calculateDiscount(ShoppingCartIndividualDTO individualCart) {
        BigDecimal couponDiscount = new BigDecimal(0);
        BigDecimal total = individualCart.getTotal();
        BigDecimal current = total.subtract(individualCart.getCampaignDiscount());

        List<Coupon> eligibleCoupons = couponRepository.findAllByMinAmountIsLessThanEqual(current);
        if (eligibleCoupons.size() > 0) {
            // TODO selection process is min amount
            Coupon coupon = Collections.max(eligibleCoupons, Comparator.comparing(c -> c.getMinAmount()));
            System.out.println("[ShoppingCartCalculator] calculateCouponDiscount() -> Successfully applied the coupon below...");
            System.out.println(coupon.toString());
            switch (coupon.getDiscountType()) {
                case AMOUNT:
                    couponDiscount = coupon.getDiscount();
                    System.out.println("current:" + current + ", couponDiscount: " + couponDiscount + ", AMOUNT");
                    break;
                case RATE:
                    couponDiscount = current.multiply(coupon.getDiscount()).divide(Constant.HUNDRED);
                    System.out.println("current:" + current + ", couponDiscount: " + couponDiscount + ", RATE");
                    break;
            }
        }
        return couponDiscount;
    }
}
