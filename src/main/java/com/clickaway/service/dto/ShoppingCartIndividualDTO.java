package com.clickaway.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "ShoppingCartIndividual DTO", description = "Data Transfer Object", parent = AbstractDTO.class)
public class ShoppingCartIndividualDTO extends AbstractDTO {
    @ApiModelProperty(value = "Final Amount of ShoppingCartIndividual")
    private BigDecimal finalAmount;
    @ApiModelProperty(value = "Delivery Cost of ShoppingCartIndividual")
    private BigDecimal deliveryCost;
    @ApiModelProperty(value = "Product List grouped by Category Name in ShoppingCartIndividual")
    private Map<String, List<ShoppingCartItemDTO>> categories;
    @ApiModelProperty(value = "Quantity of Items in ShoppingCartIndividual")
    private int quantity;
    @ApiModelProperty(value = "Total Cost of ShoppingCartIndividual")
    private BigDecimal total;
    @ApiModelProperty(value = "Coupon Discount of ShoppingCartIndividual")
    private BigDecimal couponDiscount;
    @ApiModelProperty(value = "Campaign Discount of ShoppingCartIndividual")
    private BigDecimal campaignDiscount;
}
