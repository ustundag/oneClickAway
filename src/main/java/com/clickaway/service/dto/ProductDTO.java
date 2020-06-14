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
@ApiModel(value = "Product DTO", description = "Data Transfer Object", parent = AbstractDTO.class)
public class ProductDTO extends AbstractDTO {
    @ApiModelProperty(value = "Price of ProductDTO")
    private BigDecimal price;
    @ApiModelProperty(value = "Stock Quantity of ProductDTO")
    private int stockQuantity = 1;
    @ApiModelProperty(value = "Category Name of ProductDTO")
    private String categoryName;
    @ApiModelProperty(value = "Individual URI of ProductDTO")
    private URI uri;
}
