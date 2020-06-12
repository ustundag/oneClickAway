package com.clickaway.entity;

import com.clickaway.types.DiscountType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Coupon extends AbstractEntity {
    private BigDecimal minAmount;
    private BigDecimal discount;
    private DiscountType discountType;
}
