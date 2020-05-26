package com.clickaway.service;

import com.clickaway.model.entity.Product;
import com.clickaway.repository.ProductRepository;
import com.clickaway.service.dto.ProductDTO;
import com.clickaway.transformer.ProductTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductTransformer productTransformer;

    @Override
    public ProductDTO addProduct(Product product) {
        Product product_saved = productRepository.save(product);
        return productTransformer.transformToProductDTO(product_saved);
    }

    @Override
    public ProductDTO getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(obj -> productTransformer.transformToProductDTO(obj))
                .orElse(null);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        products.forEach(item -> {
            ProductDTO productDTO = productTransformer.transformToProductDTO(item);
            productDTOS.add(productDTO);
        });
        return productDTOS;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}