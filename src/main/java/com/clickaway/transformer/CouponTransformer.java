package com.clickaway.transformer;

import com.clickaway.entity.Coupon;
import com.clickaway.service.dto.CouponDTO;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class CouponTransformer extends AbstractTransformer {

    public Coupon transformToCoupon(CouponDTO couponDTO) {
        Coupon coupon = new Coupon();
        coupon.setTitle(couponDTO.getTitle());
        coupon.setDiscount(couponDTO.getDiscount());
        coupon.setDiscountType(couponDTO.getDiscountType());
        coupon.setMinAmount(couponDTO.getMinAmount());
        return coupon;
    }

    public CouponDTO transformToCouponDTO(Coupon coupon) {
        URI uri = createUri(coupon.getId());
        CouponDTO couponDTO = CouponDTO.builder()
                .discount(coupon.getDiscount())
                .discountType(coupon.getDiscountType())
                .minAmount(coupon.getMinAmount())
                .uri(uri).build();
        couponDTO.setId(coupon.getId());
        couponDTO.setTitle(coupon.getTitle());
        return couponDTO;
    }

}
