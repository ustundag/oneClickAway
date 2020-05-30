package com.clickaway.controller;

import com.clickaway.entity.Category;
import com.clickaway.service.CategoryServiceImpl;
import com.clickaway.service.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable long id) {
        Optional<CategoryDTO> category = categoryService.getCategory(id);
        return category.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<List<CategoryDTO>> addCategory(@RequestBody List<Category> categories) {
        List<CategoryDTO> category_created = categoryService.addCategory(categories);
        return ResponseEntity.ok(category_created);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

}
