package com.clickaway.service.dto;

import com.clickaway.model.types.DiscountType;
import lombok.*;

import java.math.BigDecimal;
import java.net.URI;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class CouponDTO extends AbstractDTO{
    private BigDecimal minAmount;
    private BigDecimal discount;
    private DiscountType discountType;
    private URI uri;
}
