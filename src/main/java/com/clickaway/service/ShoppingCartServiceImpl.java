package com.clickaway.service;

import com.clickaway.entity.ShoppingCart;
import com.clickaway.repository.ShoppingCartRepository;
import com.clickaway.service.dto.ShoppingCartDTO;
import com.clickaway.service.dto.ShoppingCartIndividualDTO;
import com.clickaway.transformer.ShoppingCartTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartTransformer shoppingCartTransformer;

    @Override
    public Optional<ShoppingCartIndividualDTO> getShoppingCart(Long id) {
        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findById(id);
        return shoppingCart.map(obj -> {
            try {
                return Optional.of(shoppingCartTransformer.transformToShoppingCartIndividualDTO(obj));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).orElse(null);
    }

    @Override
    public List<ShoppingCartDTO> getAllShoppingCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();
        List<ShoppingCartDTO> shoppingCartDTOs = new ArrayList<>();
        shoppingCarts.forEach(item -> {
            ShoppingCartDTO cartDTO = shoppingCartTransformer.transformToShoppingCartDTO(item);
            shoppingCartDTOs.add(cartDTO);
        });
        return shoppingCartDTOs;
    }

    @Override
    public ShoppingCartDTO createShoppingCart(ShoppingCart cart) {
        ShoppingCart cartItem_created = shoppingCartRepository.save(cart);
        ShoppingCartDTO cartItemDTO = shoppingCartTransformer.transformToShoppingCartDTO(cartItem_created);
        return cartItemDTO;
    }

    @Override
    public void deleteShoppingCart(Long id) {
        shoppingCartRepository.deleteById(id);
    }

}