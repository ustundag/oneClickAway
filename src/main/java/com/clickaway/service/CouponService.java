package com.clickaway.service;

import com.clickaway.entity.Coupon;
import com.clickaway.service.dto.CouponDTO;

import java.util.List;

public interface CouponService {
    CouponDTO addCoupon(Coupon coupon);

    CouponDTO getCoupon(Long id);

    List<CouponDTO> getAllCoupons();

    void deleteCoupon(Long id);
}
