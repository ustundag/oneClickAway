package com.clickaway.controller;

import com.clickaway.entity.ShoppingCart;
import com.clickaway.service.ShoppingCartService;
import com.clickaway.service.dto.ShoppingCartDTO;
import com.clickaway.service.dto.ShoppingCartIndividualDTO;
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
@RequestMapping("/api/v1/cart")
@Api(value = "Rest Controller for Shopping Cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping
    @ApiOperation(value = "Get All ShoppingCarts")
    public ResponseEntity<List<ShoppingCartDTO>> getAll() {
        return ResponseEntity.ok().body(shoppingCartService.getAllShoppingCarts());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get One ShoppingCart")
    public ResponseEntity<ShoppingCartIndividualDTO> getShoppingCart(@PathVariable long id) {
        Optional<ShoppingCartIndividualDTO> shoppingCart = shoppingCartService.getShoppingCart(id);
        return shoppingCart.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Create New ShoppingCarts", notes = "Notice that Transactional Method")
    public ResponseEntity<ShoppingCartDTO> createShoppingCart(@RequestBody ShoppingCart cart) {
        ShoppingCartDTO shoppingCart = shoppingCartService.createShoppingCart(cart);
        return ResponseEntity.ok(shoppingCart);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete One ShoppingCart")
    public ResponseEntity<Void> deleteShoppingCart(@PathVariable long id) {
        shoppingCartService.deleteShoppingCart(id);
        return ResponseEntity.ok().build();
    }

}
