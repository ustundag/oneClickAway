package com.clickaway.controller;

import com.clickaway.model.entity.Coupon;
import com.clickaway.service.CouponServiceImpl;
import com.clickaway.service.dto.CouponDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupon")
public class CouponController {

    //@Autowired
    private final CouponServiceImpl couponService;

    @GetMapping
    public ResponseEntity<List<CouponDTO>> getAll() {
        return ResponseEntity.ok().body(couponService.getAllCoupons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CouponDTO> getOne(@PathVariable long id) {
        CouponDTO couponDTO = couponService.getCoupon(id);
        return (couponDTO != null) ? ResponseEntity.ok().body(couponDTO)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<CouponDTO> addOne(@RequestBody Coupon coupon) {
        CouponDTO couponDTOCreated = couponService.addCoupon(coupon);
        return ResponseEntity.ok(couponDTOCreated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable long id) {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok().build();
    }

}
