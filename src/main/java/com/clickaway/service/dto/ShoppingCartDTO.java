package com.clickaway.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.net.URI;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
@ApiModel(value = "ShoppingCart DTO", description = "Data Transfer Object", parent = AbstractDTO.class)
public class ShoppingCartDTO extends AbstractDTO {
    @ApiModelProperty(value = "Individual URI of ShoppingCartDTO")
    private URI uri;
}
