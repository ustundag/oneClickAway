package com.clickaway.service.dto;

import com.clickaway.entity.ShoppingCartItem;
import lombok.*;

import java.net.URI;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class ShoppingCartDTO extends AbstractDTO {
    private List<ShoppingCartItem> items;
    private URI uri;
}
