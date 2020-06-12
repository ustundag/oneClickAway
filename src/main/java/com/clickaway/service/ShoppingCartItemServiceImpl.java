package com.clickaway.service;

import com.clickaway.entity.ShoppingCartItem;
import com.clickaway.repository.ShoppingCartItemRepository;
import com.clickaway.service.dto.ShoppingCartItemDTO;
import com.clickaway.transformer.ShoppingCartItemTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ShoppingCartItemServiceImpl implements ShoppingCartItemService {
    private final ShoppingCartItemRepository cartItemRepository;
    private final ShoppingCartItemTransformer cartItemTransformer;

    @Override
    public Optional<ShoppingCartItemDTO> getCartItem(Long id) {
        Optional<ShoppingCartItem> cartItem = cartItemRepository.findById(id);
        return cartItem.map(obj -> Optional.of(cartItemTransformer.transformToCartItemDTO(obj)))
                .orElse(null);
    }

    @Override
    public List<ShoppingCartItemDTO> getAllCartItems() {
        List<ShoppingCartItem> cartItem = cartItemRepository.findAll();
        List<ShoppingCartItemDTO> cartItemDTOs = new ArrayList<>();
        cartItem.forEach(item -> {
            ShoppingCartItemDTO cartItemDTO = cartItemTransformer.transformToCartItemDTO(item);
            cartItemDTOs.add(cartItemDTO);
        });
        return cartItemDTOs;
    }

    @Override
    public ShoppingCartItemDTO addCartItem(ShoppingCartItem cartItem) {
        // TODO check if shoppingCartItem.cartId exists ?
        // TODO check if shoppingCartItem.quantity less than product.quantity ?
        ShoppingCartItem cartItem_created = cartItemRepository.save(cartItem);
        ShoppingCartItemDTO cartItemDTO = cartItemTransformer.transformToCartItemDTO(cartItem_created);
        return cartItemDTO;
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

}