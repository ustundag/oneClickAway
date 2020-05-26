package com.clickaway.transformer;

import com.clickaway.entity.Category;
import com.clickaway.service.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class CategoryTransformer extends AbstractTransformer {

    public Category transformToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setTitle(categoryDTO.getTitle());
        category.setParentCategoryId(categoryDTO.getParentCategoryId());
        return category;
    }

    public CategoryDTO transformToCategoryDTO(Category category) {
        URI uri = createUri(category.getId());
        CategoryDTO categoryDTO = CategoryDTO.builder()
                .parentCategoryId(category.getParentCategoryId())
                .uri(uri)
                .build();
        categoryDTO.setId(category.getId());
        categoryDTO.setTitle(category.getTitle());
        return categoryDTO;
    }

}
