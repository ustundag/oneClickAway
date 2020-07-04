package com.clickaway.service;

import com.clickaway.entity.Category;
import com.clickaway.service.dto.CategoryDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDTO> addCategory(List<Category> categories);

    Optional<CategoryDTO> getCategory(Long id);

    List<CategoryDTO> getAllCategories();

    void deleteCategory(Long id);
}
