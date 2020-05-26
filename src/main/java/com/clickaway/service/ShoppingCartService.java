package com.clickaway.service;

import com.clickaway.model.entity.Product;
import com.clickaway.service.dto.ProductDTO;
import com.clickaway.service.dto.ShoppingCartDTO;

public interface ShoppingCartService {
    ShoppingCartDTO getShoppingCart();

    ShoppingCartDTO addItem(ProductDTO productDTO);

    //void deleteItem(Long id);
}
