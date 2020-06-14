package com.clickaway.controller;

import com.clickaway.entity.Coupon;
import com.clickaway.service.CouponService;
import com.clickaway.service.dto.CouponDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupon")
@Api(value = "Rest Controller for Coupon")
public class CouponController {
    private final CouponService couponService;

    @GetMapping
    @ApiOperation(value = "Get All Coupons")
    public ResponseEntity<List<CouponDTO>> getAll() {
        return ResponseEntity.ok().body(couponService.getAllCoupons());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get One Coupon")
    public ResponseEntity<CouponDTO> getOne(@PathVariable long id) {
        Optional<CouponDTO> couponDTO = couponService.getCoupon(id);
        return couponDTO.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Create New Coupons", notes = "Notice that Transactional Method")
    public ResponseEntity<List<CouponDTO>> addCoupon(@RequestBody List<Coupon> coupons) {
        List<CouponDTO> coupons_created = couponService.addCoupon(coupons);
        return ResponseEntity.ok(coupons_created);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete One Coupon")
    public ResponseEntity<Void> deleteOne(@PathVariable long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok().build();
    }

}
