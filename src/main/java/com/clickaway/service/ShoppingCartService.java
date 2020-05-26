package com.clickaway.service;

import com.clickaway.service.dto.ProductDTO;
import com.clickaway.service.dto.ShoppingCartDTO;

public interface ShoppingCartService {
    ShoppingCartDTO getShoppingCart();

    ShoppingCartDTO addItem(ProductDTO productDTO);

    ShoppingCartDTO finishShopping() throws InstantiationException, IllegalAccessException;

    //void deleteItem(Long id);
}
