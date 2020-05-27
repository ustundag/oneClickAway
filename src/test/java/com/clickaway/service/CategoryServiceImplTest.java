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

        CategoryDTO CategoryMockDTO = mock(CategoryDTO.class);
        when(CategoryMockDTO.getId()).thenReturn(id);
        when(CategoryMockDTO.getTitle()).thenReturn(title);
        when(categoryTransformer.transformToCategoryDTO(any(Category.class)))
                .thenReturn(CategoryMockDTO);

        // then
        CategoryDTO CategoryDTO = categoryService.addCategory(category);
        assertEquals(CategoryDTO.getId(), id);
        assertEquals(CategoryDTO.getTitle(), title);
    }

    @Test
    void getCategory() {
        // given
        Long id = 1L;

        // when
        Category CategoryMock = mock(Category.class);
        when(categoryRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(CategoryMock));
        when(CategoryMock.getId()).thenReturn(id);

        CategoryDTO CategoryMockDTO = mock(CategoryDTO.class);
        when(CategoryMockDTO.getId()).thenReturn(id);
        when(categoryTransformer.transformToCategoryDTO(any(Category.class)))
                .thenReturn(CategoryMockDTO);

        // then
        CategoryDTO CategoryDTO = categoryService.getCategory(id);
        assertEquals(CategoryDTO.getId(), id);
    }

    @Test
    void getAllCategories() {
        List<Category> CategoryList = new ArrayList<>();
        Category Category = new Category();
        Category.setId(1L);
        CategoryList.add(Category);
        Category = new Category();
        Category.setId(2L);
        CategoryList.add(Category);

        // given

        // when
        when(categoryRepository.findAll()).thenReturn(CategoryList);
        CategoryDTO CategoryMockDTO_1 = mock(CategoryDTO.class);
        CategoryDTO CategoryMockDTO_2 = mock(CategoryDTO.class);
        when(CategoryMockDTO_1.getId()).thenReturn(1L);
        when(CategoryMockDTO_2.getId()).thenReturn(2L);
        when(categoryTransformer.transformToCategoryDTO(any(Category.class)))
                .thenReturn(CategoryMockDTO_1, CategoryMockDTO_2);

        // then
        List<CategoryDTO> allCategorys = categoryService.getAllCategories();
        assertEquals(allCategorys.size(), 2);
        assertEquals(allCategorys.get(1).getId(), 2L);
    }

    @Test
    void deleteCategory() {
        Long id = 1L;
        categoryService.deleteCategory(id);
        verify(categoryRepository, times(1)).deleteById(eq(id));
    }
}