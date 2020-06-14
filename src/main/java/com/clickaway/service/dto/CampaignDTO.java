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
@ApiModel(value = "Campaign DTO", description = "Data Transfer Object", parent = AbstractDTO.class)
public class CampaignDTO extends AbstractDTO {
    @ApiModelProperty(value = "Item Limit of Campaign")
    private Integer itemLimit;
    @ApiModelProperty(value = "Category Id of Campaign")
    private Long categoryId;
    @ApiModelProperty(value = "Discount of Campaign")
    private BigDecimal discount;
    @ApiModelProperty(value = "Discount Type of Campaign")
    private DiscountType discountType;
    @ApiModelProperty(value = "Individual URI of Campaign")
    private URI uri;
}
