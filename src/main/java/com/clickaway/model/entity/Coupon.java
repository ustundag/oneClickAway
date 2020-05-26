package com.clickaway.model.entity;

import com.clickaway.model.types.DiscountType;
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
