package com.clickaway.entity;

import com.clickaway.types.DiscountType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Coupon Entity", description = "Database Table Design", parent = AbstractEntity.class)
public class Coupon extends AbstractEntity {
    @ApiModelProperty(value = "Minimum Amount threshold for Coupon")
    private BigDecimal minAmount;
    @ApiModelProperty(value = "Discount Value for Coupon")
    private BigDecimal discount;
    @ApiModelProperty(value = "Discount Type for Coupon")
    private DiscountType discountType;
}
