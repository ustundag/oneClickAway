package com.clickaway.transformer;

import com.clickaway.entity.Product;
import com.clickaway.service.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class ProductTransformer extends AbstractTransformer {

    public ProductDTO transformToProductDTO(Product product) {
        URI uri = createUri(product.getId(), "product");
        ProductDTO productDTO = ProductDTO.builder()
                .categoryName(product.getCategory().getTitle())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .uri(uri).build();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        return productDTO;
    }

}
