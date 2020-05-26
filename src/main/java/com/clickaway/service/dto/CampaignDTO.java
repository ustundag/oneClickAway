package com.clickaway.service.dto;

import com.clickaway.types.DiscountType;
import lombok.*;

import java.math.BigDecimal;
import java.net.URI;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class CampaignDTO extends AbstractDTO {
    private Integer itemLimit;
    private Long categoryId;
    private BigDecimal discount;
    private DiscountType discountType;
    private URI uri;
}
