package com.clickaway.service.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class ShoppingCartIndividualDTO extends AbstractDTO {
    private BigDecimal finalAmount;
    private BigDecimal deliveryCost;
    private Map<String, List<ShoppingCartItemDTO>> categories;
    private int quantity;
    private BigDecimal total;
    private BigDecimal couponDiscount;
    private BigDecimal campaignDiscount;
}
