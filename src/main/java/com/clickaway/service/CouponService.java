package com.clickaway.service;

import com.clickaway.entity.Coupon;
import com.clickaway.service.dto.CouponDTO;

import java.util.List;
import java.util.Optional;

public interface CouponService {
    List<CouponDTO> addCoupon(List<Coupon> coupons);

    Optional<CouponDTO> getCoupon(Long id);

    List<CouponDTO> getAllCoupons();

    void deleteCoupon(Long id);
}
