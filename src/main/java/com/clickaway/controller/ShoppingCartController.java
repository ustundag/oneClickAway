package com.clickaway.controller;

import com.clickaway.entity.ShoppingCart;
import com.clickaway.service.ShoppingCartServiceImpl;
import com.clickaway.service.dto.ShoppingCartDTO;
import com.clickaway.service.dto.ShoppingCartIndividualDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class ShoppingCartController {
    private final ShoppingCartServiceImpl shoppingCartService;

    @GetMapping
    public ResponseEntity<List<ShoppingCartDTO>> getAll() {
        return ResponseEntity.ok().body(shoppingCartService.getAllShoppingCarts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartIndividualDTO> getShoppingCart(@PathVariable long id) {
        Optional<ShoppingCartIndividualDTO> shoppingCart = shoppingCartService.getShoppingCart(id);
        return shoppingCart.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ShoppingCartDTO> createShoppingCart(@RequestBody ShoppingCart cart) {
        ShoppingCartDTO shoppingCart = shoppingCartService.createShoppingCart(cart);
        return ResponseEntity.ok(shoppingCart);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteShoppingCart(@PathVariable long id) {
        shoppingCartService.deleteShoppingCart(id);
        return ResponseEntity.ok().build();
    }

}
