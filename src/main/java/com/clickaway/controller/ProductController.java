package com.clickaway.controller;

import com.clickaway.entity.Product;
import com.clickaway.service.ProductService;
import com.clickaway.service.dto.ProductDTO;
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
@RequestMapping("/api/v1/product")
@Api(value = "Rest Controller for Product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @ApiOperation(value = "Get All Products")
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get One Product")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable long id) {
        Optional<ProductDTO> product = productService.getProduct(id);
        return product.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Create New Products", notes = "Notice that Transactional Method")
    public ResponseEntity<List<ProductDTO>> addProduct(@RequestBody List<Product> products) {
        List<ProductDTO> products_created = productService.addProduct(products);
        return ResponseEntity.ok(products_created);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Delete One Product")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
