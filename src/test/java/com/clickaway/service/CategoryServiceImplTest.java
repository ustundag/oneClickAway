package com.clickaway.service;

import com.clickaway.entity.Category;
import com.clickaway.entity.Category;
import com.clickaway.repository.CategoryRepository;
import com.clickaway.repository.CategoryRepository;
import com.clickaway.service.dto.CategoryDTO;
import com.clickaway.service.dto.CategoryDTO;
import com.clickaway.transformer.CategoryTransformer;
import com.clickaway.transformer.CategoryTransformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CategoryServiceImplTest {
    @InjectMocks
    private CategoryServiceImpl categoryService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryTransformer categoryTransformer;

    @Test
    void addCategory() {
        Long id = 1L;
        String title = "clothes";
        // given
        Category category = new Category();
        category.setId(id);
        category.setTitle(title);

        // when
        Category categoryMock = mock(Category.class);
        when(categoryMock.getId()).thenReturn(id);
        when(categoryMock.getTitle()).thenReturn(title);
        when(categoryRepository.save(any(Category.class)))
                .thenReturn(categoryMock);

        CategoryDTO categoryMockDTO = mock(CategoryDTO.class);
        when(categoryMockDTO.getId()).thenReturn(id);
        when(categoryMockDTO.getTitle()).thenReturn(title);
        when(categoryTransformer.transformToCategoryDTO(any(Category.class)))
                .thenReturn(categoryMockDTO);

        // then
        CategoryDTO categoryDTO = categoryService.addCategory(category);
        assertEquals(categoryDTO.getId(), id);
        assertEquals(categoryDTO.getTitle(), title);
    }

    @Test
    void getCategory() {
        // given
        Long id = 1L;

        // when
        Category categoryMock = mock(Category.class);
        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(categoryMock));
        when(categoryMock.getId()).thenReturn(id);

        CategoryDTO categoryMockDTO = mock(CategoryDTO.class);
        when(categoryMockDTO.getId()).thenReturn(id);
        when(categoryTransformer.transformToCategoryDTO(any(Category.class)))
                .thenReturn(categoryMockDTO);

        // then
        CategoryDTO categoryDTO = categoryService.getCategory(id);
        assertEquals(categoryDTO.getId(), id);
    }

    @Test
    void getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setId(1L);
        categoryList.add(category);
        category = new Category();
        category.setId(2L);
        categoryList.add(category);

        // given

        // when
        when(categoryRepository.findAll()).thenReturn(categoryList);
        CategoryDTO categoryMockDTO_1 = mock(CategoryDTO.class);
        CategoryDTO categoryMockDTO_2 = mock(CategoryDTO.class);
        when(categoryMockDTO_1.getId()).thenReturn(1L);
        when(categoryMockDTO_2.getId()).thenReturn(2L);
        when(categoryTransformer.transformToCategoryDTO(any(Category.class)))
                .thenReturn(categoryMockDTO_1, categoryMockDTO_2);

        // then
        List<CategoryDTO> allCategories = categoryService.getAllCategories();
        assertEquals(allCategories.size(), 2);
        assertEquals(allCategories.get(1).getId(), 2L);
    }

    @Test
    void deleteCategory() {
        Long id = 1L;
        categoryService.deleteCategory(id);
        verify(categoryRepository, times(1)).deleteById(eq(id));
    }
}