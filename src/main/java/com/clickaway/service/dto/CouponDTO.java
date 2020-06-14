package com.clickaway.service.dto;

import com.clickaway.types.DiscountType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.net.URI;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
@ApiModel(value = "Coupon DTO", description = "Data Transfer Object", parent = AbstractDTO.class)
public class CouponDTO extends AbstractDTO {
    @ApiModelProperty(value = "Min Amount Threshold for CouponDTO")
    private BigDecimal minAmount;
    @ApiModelProperty(value = "Discount Value for CouponDTO")
    private BigDecimal discount;
    @ApiModelProperty(value = "Discount Type for CouponDTO")
    private DiscountType discountType;
    @ApiModelProperty(value = "Individual URI of CouponDTO")
    private URI uri;
}
