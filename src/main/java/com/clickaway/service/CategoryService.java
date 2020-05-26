package com.clickaway.service;

import com.clickaway.model.entity.Category;
import com.clickaway.service.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(Category category);

    CategoryDTO getCategory(Long id);

    List<CategoryDTO> getAllCategories();

    void deleteCategory(Long id);
}
