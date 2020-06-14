package com.clickaway.service.dto;

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
@ApiModel(value = "ShoppingCartItem DTO", description = "Data Transfer Object", parent = AbstractDTO.class)
public class ShoppingCartItemDTO extends AbstractDTO {
    @ApiModelProperty(value = "ShoppingCart Id of ShoppingCartItemDTO")
    private Long cartID;
    @ApiModelProperty(value = "Product Title of ShoppingCartItemDTO")
    private String productTitle;
    @ApiModelProperty(value = "Product Price of ShoppingCartItemDTO")
    private BigDecimal productPrice;
    @ApiModelProperty(value = "Product Quantity of ShoppingCartItemDTO")
    private int productQuantity;
    @ApiModelProperty(value = "Product's Individual URI in ShoppingCartItemDTO")
    private URI productUri;
}
