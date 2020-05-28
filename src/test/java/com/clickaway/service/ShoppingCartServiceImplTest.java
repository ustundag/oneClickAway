package com.clickaway.service;

import com.clickaway.constant.Constant;
import com.clickaway.entity.Product;
import com.clickaway.entity.ShoppingCart;
import com.clickaway.entity.ShoppingCartItem;
import com.clickaway.repository.CategoryIDAndTitle;
import com.clickaway.repository.CategoryRepository;
import com.clickaway.repository.ShoppingCartRepository;
import com.clickaway.service.dto.ProductDTO;
import com.clickaway.service.dto.ShoppingCartDTO;
import com.clickaway.transformer.ProductTransformer;
import com.clickaway.transformer.ShoppingCartTransformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ShoppingCartServiceImplTest {
    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;
    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ShoppingCartTransformer shoppingCartTransformer;
    @Mock
    private ProductTransformer productTransformer;

    @Test
    void getShoppingCart() {
        Long id = 1L;
        ShoppingCart cart = new ShoppingCart();
        cart.setId(id);
        cart.setTitle(Constant.TITLE);
        cart.setCartItems(new ArrayList<>());
        // given

        // when
        Product productMock = mock(Product.class);
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(cart);
        when(productMock.getId()).thenReturn(id);

        ShoppingCartDTO shoppingCartDTOMock = mock(ShoppingCartDTO.class);
        when(shoppingCartDTOMock.getId()).thenReturn(id);
        when(shoppingCartTransformer.transformToShoppingCartDTO(any(ShoppingCart.class)))
                .thenReturn(shoppingCartDTOMock);

        // then
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.getShoppingCart();
        assertEquals(shoppingCartDTO.getId(), id);
    }

    @Test
    void addItem() {
        /*
        // prepare
        Long id = 1L;
        Long catID = 2L;
        String title = "product";
        int quantity = 3;
        List<ShoppingCartItem> cartItemList = new ArrayList<>();
        ShoppingCart cart = new ShoppingCart();
        cart.setId(id);
        cart.setTitle(Constant.TITLE);
        cart.setCartItems(cartItemList);

        ShoppingCartItem cartItem = new ShoppingCartItem();
        cartItem.setQuantity(quantity);
        cartItem.setTitle(title);

        ShoppingCart cart_saved = new ShoppingCart();
        cart_saved.setId(id);
        cart_saved.setTitle(Constant.TITLE);
        cartItemList.add(cartItem);
        cart_saved.setCartItems(cartItemList);

        ShoppingCartDTO maiShoppingCartDTO = new ShoppingCartDTO();
        maiShoppingCartDTO.setId(id);
        maiShoppingCartDTO.setTitle(Constant.TITLE);
        maiShoppingCartDTO.setQuantity(quantity);

        List<CategoryIDAndTitle> categories = new ArrayList<>();
        Map<Long, String> asd = new HashMap<>();
        asd.put(catID, title);
        // TODO Throws ClassCastException
        categories.add((CategoryIDAndTitle) asd);

        // given
        ProductDTO productDTO = new ProductDTO();
        productDTO.setTitle(title);
        productDTO.setCategoryId(catID);
        productDTO.setQuantity(quantity);

        // when
        //when(shoppingCartRepository.updateCartItemQuantityByTitle(any(String.class),any(Integer.class))).thenReturn(null);
        when(shoppingCartRepository.existsCartItemByTitle(any(String.class))).thenReturn(false);
        when(shoppingCartRepository.findById(any(Long.class))).thenReturn(Optional.of(cart));
        when(productTransformer.transformToCartItem(any(ProductDTO.class))).thenReturn(cartItem);
        when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(cart_saved);
        when(shoppingCartTransformer.transformToShoppingCartDTO(any(ShoppingCart.class))).thenReturn(maiShoppingCartDTO);
        when(categoryRepository.findAllBy()).thenReturn((List<CategoryIDAndTitle>) categories);

        // then
        ShoppingCartDTO shoppingCartDTO = shoppingCartService.addItem(productDTO);
        assertEquals(shoppingCartDTO.getId(), id);
        assertEquals(shoppingCartDTO.getTitle(), title);
        assertEquals(shoppingCartDTO.getQuantity(), quantity);
         */
    }

    @Test
    void finishShopping() {

    }
}