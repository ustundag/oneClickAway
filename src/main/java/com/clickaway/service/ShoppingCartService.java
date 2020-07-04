package com.clickaway.service;

import com.clickaway.entity.ShoppingCart;
import com.clickaway.service.dto.ShoppingCartDTO;
import com.clickaway.service.dto.ShoppingCartIndividualDTO;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {
    ShoppingCartDTO createShoppingCart(ShoppingCart cart);

    Optional<ShoppingCartIndividualDTO> getShoppingCart(Long id);

    List<ShoppingCartDTO> getAllShoppingCarts();

    void deleteShoppingCart(Long id);
}
