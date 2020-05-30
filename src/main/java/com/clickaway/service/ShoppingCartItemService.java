package com.clickaway.service;

import com.clickaway.entity.ShoppingCartItem;
import com.clickaway.service.dto.ShoppingCartItemDTO;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartItemService {
    Optional<ShoppingCartItemDTO> getCartItem(Long id);

    List<ShoppingCartItemDTO> getAllCartItems();

    ShoppingCartItemDTO addCartItem(ShoppingCartItem shoppingCartItem);

    void deleteCartItem(Long id);
}
