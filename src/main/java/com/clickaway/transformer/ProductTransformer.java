package com.clickaway.transformer;

import com.clickaway.entity.Product;
import com.clickaway.service.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class ProductTransformer extends AbstractTransformer {

    /*
    public Product transformToProduct(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setCategory(category);
        product.setPrice(productDTO.getPrice());
        return product;
    }
    */

    public ProductDTO transformToProductDTO(Product product) {
        URI uri = createUri(product.getId());
        ProductDTO productDTO = ProductDTO.builder()
                //.categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getTitle())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .uri(uri).build();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        return productDTO;
    }

    /*
    public ShoppingCartItem transformToCartItem(ProductDTO productDTO) {
        ShoppingCartItem cartItem = new ShoppingCartItem(
                productDTO.getPrice(),
                productDTO.getQuantity(),
                productDTO.getCategoryId()
        );
        cartItem.setTitle(productDTO.getTitle());
        return cartItem;
    }
    */

}
