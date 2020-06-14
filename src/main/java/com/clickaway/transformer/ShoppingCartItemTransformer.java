package com.clickaway.transformer;

import com.clickaway.entity.ShoppingCartItem;
import com.clickaway.service.dto.ShoppingCartItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class ShoppingCartItemTransformer extends AbstractTransformer {

    public ShoppingCartItemDTO transformToCartItemDTO(ShoppingCartItem cartItem) {
        URI uri = createUri(cartItem.getProduct().getId(), "product");
        ShoppingCartItemDTO cartItemDTO = ShoppingCartItemDTO.builder()
                .cartID(cartItem.getCart().getId())
                .productTitle(cartItem.getProduct().getTitle())
                .productPrice(cartItem.getProduct().getPrice())
                .productQuantity(cartItem.getQuantity())
                .productUri(uri).build();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setTitle(cartItem.getTitle());
        return cartItemDTO;
    }

}
