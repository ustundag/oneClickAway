package com.clickaway.transformer;

import com.clickaway.entity.Coupon;
import com.clickaway.service.dto.CouponDTO;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class CouponTransformer extends AbstractTransformer {

    public CouponDTO transformToCouponDTO(Coupon coupon) {
        URI uri = createUri(coupon.getId(), "coupon");
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
