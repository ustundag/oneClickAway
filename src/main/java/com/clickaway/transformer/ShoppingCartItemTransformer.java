package com.clickaway.transformer;

import com.clickaway.entity.ShoppingCartItem;
import com.clickaway.service.dto.ShoppingCartItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class ShoppingCartItemTransformer extends AbstractTransformer {

    public ShoppingCartItemDTO transformToCartItemDTO(ShoppingCartItem cartItem) {
        URI uri = createUri(cartItem.getId(), "item");
        ShoppingCartItemDTO cartItemDTO = ShoppingCartItemDTO.builder()
                .cartID(cartItem.getCart().getId())
                .productTitle(cartItem.getProduct().getTitle())
                .productPrice(cartItem.getProduct().getPrice())
                .quantity(cartItem.getProduct().getQuantity())
                .uri(uri).build();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setTitle(cartItem.getTitle());
        return cartItemDTO;
    }

}
