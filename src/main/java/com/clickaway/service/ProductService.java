package com.clickaway.service;

import com.clickaway.entity.Product;
import com.clickaway.service.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> addProduct(List<Product> products);

    Optional<ProductDTO> getProduct(Long id);

    List<ProductDTO> getAllProducts();

    void deleteProduct(Long id);
}
