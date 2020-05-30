package com.clickaway.entity;

import com.clickaway.types.DiscountType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

// https://stackoverflow.com/a/37727206/2966857
//@Data
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Campaign extends AbstractEntity {
    private Integer itemLimit;
    private Long categoryId;
    private BigDecimal discount;
    private DiscountType discountType;
}
