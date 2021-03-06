package com.clickaway.controller;

import com.clickaway.entity.ShoppingCartItem;
import com.clickaway.service.ShoppingCartItemService;
import com.clickaway.service.dto.ShoppingCartItemDTO;
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
@RequestMapping("/api/v1/item")
@Api(value = "Rest Controller for Shopping Cart Item")
public class ShoppingCartItemController {
    private final ShoppingCartItemService cartItemService;

    @GetMapping
    @ApiOperation(value = "Get All ShoppingCartItems")
    public ResponseEntity<List<ShoppingCartItemDTO>> getAll() {
        return ResponseEntity.ok().body(cartItemService.getAllCartItems());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get One ShoppingCartItem")
    public ResponseEntity<ShoppingCartItemDTO> getCartItem(@PathVariable Long id) {
        Optional<ShoppingCartItemDTO> cartItem = cartItemService.getCartItem(id);
        return cartItem.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Create New ShoppingCartItems", notes = "Notice that Transactional Method")
    public ResponseEntity<ShoppingCartItemDTO> addCartItem(@RequestBody ShoppingCartItem shoppingCartItem) {
        ShoppingCartItemDTO cartItem = cartItemService.addCartItem(shoppingCartItem);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete One ShoppingCartItem")
    public ResponseEntity<Void> deleteCart(@PathVariable long id) {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.ok().build();
    }

}
