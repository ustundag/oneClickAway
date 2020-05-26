package com.clickaway.service;

import com.clickaway.entity.Coupon;
import com.clickaway.repository.CouponRepository;
import com.clickaway.service.dto.CouponDTO;
import com.clickaway.transformer.CouponTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponTransformer couponTransformer;

    @Override
    public CouponDTO addCoupon(Coupon coupon) {
        Coupon coupon_saved = couponRepository.save(coupon);
        return couponTransformer.transformToCouponDTO(coupon_saved);
    }

    @Override
    public CouponDTO getCoupon(Long id) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        return coupon.map(obj -> couponTransformer.transformToCouponDTO(obj))
                .orElse(null);
    }

    @Override
    public List<CouponDTO> getAllCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        List<CouponDTO> couponDTOs = new ArrayList<>();
        coupons.forEach(item -> {
            CouponDTO couponDTO = couponTransformer.transformToCouponDTO(item);
            couponDTOs.add(couponDTO);
        });
        return couponDTOs;
    }

    @Override
    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }
}