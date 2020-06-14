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
public class ShoppingCartItemDTO extends AbstractDTO {
    private Long cartID;
    private String productTitle;
    private BigDecimal productPrice;
    private int productQuantity;
    private URI productUri;
}
