package com.clickaway.service.dto;

import lombok.*;

import java.math.BigDecimal;
import java.net.URI;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class ProductDTO extends AbstractDTO {
    private BigDecimal price;
    private Long categoryId;
    private int quantity;
    private URI uri;
}
