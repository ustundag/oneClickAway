package com.clickaway.service;

import com.clickaway.entity.Product;
import com.clickaway.service.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO addProduct(Product product);

    ProductDTO getProduct(Long id);

    List<ProductDTO> getAllProducts();

    void deleteProduct(Long id);
}
