package com.clickaway.service;

import com.clickaway.entity.Category;
import com.clickaway.repository.CategoryRepository;
import com.clickaway.service.dto.CategoryDTO;
import com.clickaway.transformer.CategoryTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryTransformer categoryTransformer;

    @Override
    public CategoryDTO addCategory(Category category) {
        Category category_saved = categoryRepository.save(category);
        return categoryTransformer.transformToCategoryDTO(category_saved);
    }

    @Override
    public CategoryDTO getCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(obj -> categoryTransformer.transformToCategoryDTO(obj))
                .orElse(null);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        categories.forEach(item -> {
            CategoryDTO categoryDTO = categoryTransformer.transformToCategoryDTO(item);
            categoryDTOs.add(categoryDTO);
        });
        return categoryDTOs;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}