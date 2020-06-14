package com.clickaway.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.net.URI;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
@ApiModel(value = "Category DTO", description = "Data Transfer Object", parent = AbstractDTO.class)
public class CategoryDTO extends AbstractDTO {
    @ApiModelProperty(value = "Product List in CategoryDTO")
    private List<ProductDTO> products;
    @ApiModelProperty(value = "Parent Category of CategoryDTO")
    private String parentCategory;
    @ApiModelProperty(value = "Individual URI of CategoryDTO")
    private URI uri;
}
