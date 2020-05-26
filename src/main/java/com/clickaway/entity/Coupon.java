package com.clickaway.entity;

import com.clickaway.types.DiscountType;
import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Coupon extends AbstractEntity {
    private BigDecimal minAmount;
    private BigDecimal discount;
    private DiscountType discountType;
}
