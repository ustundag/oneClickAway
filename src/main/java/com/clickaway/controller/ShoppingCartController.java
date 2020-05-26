package com.clickaway.controller;

import com.clickaway.service.ShoppingCartServiceImpl;
import com.clickaway.service.dto.ProductDTO;
import com.clickaway.service.dto.ShoppingCartDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class ShoppingCartController {

    //@Autowired
    private final ShoppingCartServiceImpl shoppingCartService;

    @GetMapping
    public ResponseEntity<ShoppingCartDTO> getShoppingCart() {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.getShoppingCart();
        return ResponseEntity.ok().body(shoppingCartDTO);
    }

    @PostMapping
    public ResponseEntity<ShoppingCartDTO> addItem(@RequestBody ProductDTO productDTO) {
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.addItem(productDTO);
        return ResponseEntity.ok(shoppingCartDTO);
    }

    /*
    @DeleteMapping
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        shoppingCartService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
    */
}
