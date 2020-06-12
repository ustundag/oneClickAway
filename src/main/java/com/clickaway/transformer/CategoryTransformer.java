package com.clickaway.transformer;

import com.clickaway.entity.Category;
import com.clickaway.entity.Product;
import com.clickaway.service.dto.CategoryDTO;
import com.clickaway.service.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryTransformer extends AbstractTransformer {
    private final ProductTransformer productTransformer;

    public CategoryDTO transformToCategoryDTO(Category category) {
        URI uri = createUri(category.getId(), "category");
        List<Product> products = category.getProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();

        // TODO modify uri in productDTO
        products.forEach(product -> {
            ProductDTO productDTO = productTransformer.transformToProductDTO(product);
            productDTOs.add(productDTO);
        });

        CategoryDTO categoryDTO = CategoryDTO.builder()
                .productDTOs(productDTOs)
                .parentCategory(category.getParentCategory())
                .uri(uri)
                .build();
        categoryDTO.setId(category.getId());
        categoryDTO.setTitle(category.getTitle());
        return categoryDTO;
    }

}
