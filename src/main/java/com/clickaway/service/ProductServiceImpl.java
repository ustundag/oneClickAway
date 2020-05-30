package com.clickaway.service;

import com.clickaway.entity.Product;
import com.clickaway.repository.ProductRepository;
import com.clickaway.service.dto.ProductDTO;
import com.clickaway.transformer.ProductTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductTransformer productTransformer;

    @Transactional
    @Override
    public List<ProductDTO> addProduct(List<Product> products) {
        List<ProductDTO> products_created = new ArrayList<>();
        Product product_saved;
        ProductDTO productDTO;
        for (Product product : products) {
            product_saved = productRepository.save(product);
            productDTO = productTransformer.transformToProductDTO(product_saved);
            products_created.add(productDTO);
        }
        return products_created;
    }

    @Override
    public Optional<ProductDTO> getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(obj -> Optional.of(productTransformer.transformToProductDTO(obj)))
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