package com.clickaway.controller;

import com.clickaway.entity.Coupon;
import com.clickaway.service.CouponService;
import com.clickaway.service.dto.CouponDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupon")
public class CouponController {
    private final CouponService couponService;

    @GetMapping
    public ResponseEntity<List<CouponDTO>> getAll() {
        return ResponseEntity.ok().body(couponService.getAllCoupons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponDTO> getOne(@PathVariable long id) {
        Optional<CouponDTO> couponDTO = couponService.getCoupon(id);
        return couponDTO.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<List<CouponDTO>> addCoupon(@RequestBody List<Coupon> coupons) {
        List<CouponDTO> coupons_created = couponService.addCoupon(coupons);
        return ResponseEntity.ok(coupons_created);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok().build();
    }

}
