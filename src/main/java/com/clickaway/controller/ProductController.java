package com.clickaway.controller;

import com.clickaway.entity.Product;
import com.clickaway.service.ProductServiceImpl;
import com.clickaway.service.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getOne(@PathVariable long id) {
        ProductDTO productDTO = productService.getProduct(id);
        return (productDTO != null) ? ResponseEntity.ok().body(productDTO)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addOne(@RequestBody Product product) {
        ProductDTO productDTOCreated = productService.addProduct(product);
        return ResponseEntity.ok(productDTOCreated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
