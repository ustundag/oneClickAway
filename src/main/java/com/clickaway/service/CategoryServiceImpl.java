package com.clickaway.service;

import com.clickaway.entity.Category;
import com.clickaway.repository.CategoryRepository;
import com.clickaway.service.dto.CategoryDTO;
import com.clickaway.transformer.CategoryTransformer;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryTransformer categoryTransformer;

    @Transactional
    @Override
    public List<CategoryDTO> addCategory(List<Category> categories) {
        List<CategoryDTO> categories_created = new ArrayList<>();
        Category category_saved;
        CategoryDTO categoryDTO;
        for (Category category : categories) {
            category_saved = categoryRepository.save(category);
            categoryDTO = categoryTransformer.transformToCategoryDTO(category_saved);
            categories_created.add(categoryDTO);
        }
        return categories_created;
    }

    @Override
    public Optional<CategoryDTO> getCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(obj -> Optional.of(categoryTransformer.transformToCategoryDTO(obj)))
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