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
@ApiModel(value = "Campaign Entity", description = "Database Table Design", parent = AbstractEntity.class)
public class Campaign extends AbstractEntity {
    @ApiModelProperty(value = "Item Limit Threshold for Campaign")
    private Integer itemLimit;
    @ApiModelProperty(value = "Category ID of Campaign")
    private Long categoryId;
    @ApiModelProperty(value = "Discount Value of Campaign")
    private BigDecimal discount;
    @ApiModelProperty(value = "Discount Type of Campaign")
    private DiscountType discountType;
}
