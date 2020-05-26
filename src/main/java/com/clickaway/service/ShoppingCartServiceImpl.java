package com.clickaway.service;

import com.clickaway.model.entity.ShoppingCart;
import com.clickaway.model.entity.ShoppingCartItem;
import com.clickaway.repository.CategoryIDAndTitle;
import com.clickaway.repository.CategoryRepository;
import com.clickaway.repository.ShoppingCartRepository;
import com.clickaway.service.dto.ProductDTO;
import com.clickaway.service.dto.ShoppingCartDTO;
import com.clickaway.transformer.ProductTransformer;
import com.clickaway.transformer.ShoppingCartTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CategoryRepository categoryRepository;
    private final ShoppingCartTransformer shoppingCartTransformer;
    private final ProductTransformer productTransformer;
    private ShoppingCartDTO mainShoppingCartDTO = null;

    @Override
    public ShoppingCartDTO getShoppingCart() {
        return Optional.ofNullable(mainShoppingCartDTO).orElseGet(() -> fetchShoppingCart());
    }

    private ShoppingCartDTO fetchShoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setTitle("Welcome to OneClickAway. Let's fill your shopping cart up!");
        cart.setCartItems(new ArrayList<>());
        cart = shoppingCartRepository.save(cart);
        this.mainShoppingCartDTO = shoppingCartTransformer.transformToShoppingCartDTO(cart);
        return this.mainShoppingCartDTO;
    }

    @Override
    public ShoppingCartDTO addItem(ProductDTO productDTO) {
        ShoppingCart cart = shoppingCartRepository.findById(mainShoppingCartDTO.getId()).get();
        cart = updateShoppingCart(cart, productDTO);
        cart = shoppingCartRepository.save(cart);
        mainShoppingCartDTO = shoppingCartTransformer.transformToShoppingCartDTO(cart);
        mainShoppingCartDTO.setCategories(getProductsByCategory(cart.getCartItems()));
        return this.mainShoppingCartDTO;
    }

    private ShoppingCart updateShoppingCart(ShoppingCart shoppingCart, ProductDTO productDTO) {
        List<ShoppingCartItem> cartItemList = shoppingCart.getCartItems();
        ShoppingCartItem cartItem = productTransformer.transformToCartItem(productDTO);
        cartItemList.add(cartItem);
        shoppingCart.setCartItems(cartItemList);
        return shoppingCart;
    }

    private Map<String, List<ShoppingCartItem>> getProductsByCategory(List<ShoppingCartItem> cartItems) {
        Map<String, List<ShoppingCartItem>> categoryMap = new HashMap<>();

        // convert list to hashmap with key value
        List<CategoryIDAndTitle> categoryIDAndTitleMap = categoryRepository.findAllBy();
        Map<Long, String> categories = categoryIDAndTitleMap.stream().collect(
                Collectors.toMap(CategoryIDAndTitle::getId, CategoryIDAndTitle::getTitle));

        cartItems.forEach(item -> {
            Long catID = item.getCategoryId();
            String catTitle = categories.get(catID);
            Optional<List> optionalCatItems = Optional.ofNullable(categoryMap.get(catTitle));
            List<ShoppingCartItem> subCatItems = optionalCatItems.orElseGet(() -> new ArrayList());
            subCatItems.add(item);
            categoryMap.put(catTitle, subCatItems);
        });

        return categoryMap;
    }

}