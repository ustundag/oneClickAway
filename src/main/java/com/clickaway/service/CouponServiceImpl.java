package com.clickaway.service;

import com.clickaway.entity.Coupon;
import com.clickaway.repository.CouponRepository;
import com.clickaway.service.dto.CouponDTO;
import com.clickaway.transformer.CouponTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponTransformer couponTransformer;

    @Transactional
    @Override
    public List<CouponDTO> addCoupon(List<Coupon> coupons) {
        List<CouponDTO> coupons_created = new ArrayList<>();
        Coupon coupon_saved;
        CouponDTO couponDTO;
        for (Coupon coupon : coupons) {
            coupon_saved = couponRepository.save(coupon);
            couponDTO = couponTransformer.transformToCouponDTO(coupon_saved);
            coupons_created.add(couponDTO);
        }
        return coupons_created;
    }

    @Override
    public Optional<CouponDTO> getCoupon(Long id) {
        Optional<Coupon> coupon = couponRepository.findById(id);
        return coupon.map(obj -> Optional.of(couponTransformer.transformToCouponDTO(obj)))
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