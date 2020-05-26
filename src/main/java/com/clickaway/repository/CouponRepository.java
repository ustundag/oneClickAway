package com.clickaway.repository;

import com.clickaway.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findAllByMinAmountIsLessThanEqual(BigDecimal currentAmount);
}
