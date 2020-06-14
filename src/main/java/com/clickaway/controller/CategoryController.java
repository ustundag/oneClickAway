package com.clickaway.controller;

import com.clickaway.entity.Category;
import com.clickaway.service.CategoryService;
import com.clickaway.service.dto.CategoryDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
@Api(value = "Rest Controller for Category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    @ApiOperation(value = "Get All Categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get One Category")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable long id) {
        Optional<CategoryDTO> category = categoryService.getCategory(id);
        return category.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Create New Categories", notes = "Notice that Transactional Method")
    public ResponseEntity<List<CategoryDTO>> addCategory(@RequestBody List<Category> categories) {
        List<CategoryDTO> category_created = categoryService.addCategory(categories);
        return ResponseEntity.ok(category_created);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete One Category")
    public ResponseEntity<Void> deleteCategory(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

}
