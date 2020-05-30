package com.clickaway.transformer;

import com.clickaway.entity.Category;
import com.clickaway.entity.Product;
import com.clickaway.service.dto.CategoryDTO;
import com.clickaway.service.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryTransformer extends AbstractTransformer {
    private final ProductTransformer productTransformer;
    /*
    public Category transformToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setTitle(categoryDTO.getTitle());
        category.setProducts(categoryDTO.getProducts());
        category.setParentCategoryId(categoryDTO.getParentCategoryId());
        return category;
    }
    */

    public CategoryDTO transformToCategoryDTO(Category category) {
        URI uri = createUri(category.getId());
        List<Product> products = category.getProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();
        // TODO convert products to productDTO list, take care for URI attribute.
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .products(products)
                .parentCategory(category.getParentCategory())
                .uri(uri)
                .build();
        categoryDTO.setId(category.getId());
        categoryDTO.setTitle(category.getTitle());
        return categoryDTO;
    }

}
