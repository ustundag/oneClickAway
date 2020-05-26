package com.clickaway.controller;

import com.clickaway.model.entity.Category;
import com.clickaway.service.CategoryServiceImpl;
import com.clickaway.service.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    //@Autowired
    private final CategoryServiceImpl categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getOne(@PathVariable long id) {
        CategoryDTO categoryDTO = categoryService.getCategory(id);
        return (categoryDTO != null) ? ResponseEntity.ok().body(categoryDTO)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> addOne(@RequestBody Category category) {
        CategoryDTO campaignDTOCreated = categoryService.addCategory(category);
        return ResponseEntity.ok(campaignDTOCreated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

}
