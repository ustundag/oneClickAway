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
public class Campaign extends AbstractEntity {
    private Integer itemLimit;
    private Long categoryId;
    private BigDecimal discount;
    private DiscountType discountType;
}
