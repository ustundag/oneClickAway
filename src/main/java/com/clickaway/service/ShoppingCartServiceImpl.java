package com.clickaway.service;

import com.clickaway.calculator.DeliveryCostCalculatorImpl;
import com.clickaway.calculator.DiscountCalculatorFactory;
import com.clickaway.constant.Constant;
import com.clickaway.entity.ShoppingCart;
import com.clickaway.entity.ShoppingCartItem;
import com.clickaway.repository.CategoryIDAndTitle;
import com.clickaway.repository.CategoryRepository;
import com.clickaway.repository.ShoppingCartRepository;
import com.clickaway.service.dto.ProductDTO;
import com.clickaway.service.dto.ShoppingCartDTO;
import com.clickaway.transformer.ProductTransformer;
import com.clickaway.transformer.ShoppingCartTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.clickaway.types.CalculatorType.CAMPAIGN;
import static com.clickaway.types.CalculatorType.COUPON;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartTransformer shoppingCartTransformer;
    private final ProductTransformer productTransformer;
    private final CategoryRepository categoryRepository;
    private final DiscountCalculatorFactory discountCalculatorFactory;
    private final DeliveryCostCalculatorImpl deliveryCostCalculatorImpl;
    private ShoppingCartDTO mainShoppingCartDTO = null;

    @Override
    public ShoppingCartDTO getShoppingCart() {
        return Optional.ofNullable(mainShoppingCartDTO).orElseGet(() -> fetchShoppingCart());
    }

    @Transactional
    @Override
    public ShoppingCartDTO addItem(ProductDTO productDTO) {
        mainShoppingCartDTO = Optional.ofNullable(mainShoppingCartDTO).orElseGet(() -> fetchShoppingCart());

        boolean exists = shoppingCartRepository.existsCartItemByTitle(productDTO.getTitle());
        if (exists) {
            shoppingCartRepository.updateCartItemQuantityByTitle(productDTO.getTitle(), productDTO.getQuantity());
        } else {
            ShoppingCart cart = shoppingCartRepository.findById(mainShoppingCartDTO.getId()).get();
            cart = appendShoppingCart(cart, productDTO);
            cart = shoppingCartRepository.save(cart);
        }
        return updateMainShoppingCartDTO();
    }

    @Override
    public ShoppingCartDTO finishShopping() throws InstantiationException, IllegalAccessException {
        mainShoppingCartDTO = Optional.ofNullable(mainShoppingCartDTO).orElseGet(() -> fetchShoppingCart());

        BigDecimal campaignDiscount = (BigDecimal) discountCalculatorFactory
                .getDiscountCalculator(CAMPAIGN)
                .calculateDiscount(mainShoppingCartDTO.getTotal());

        BigDecimal couponDiscount = (BigDecimal) discountCalculatorFactory
                .getDiscountCalculator(COUPON)
                .calculateDiscount(mainShoppingCartDTO.getTotal().subtract(campaignDiscount));

        BigDecimal deliveryCost = deliveryCostCalculatorImpl.calculateDeliveryCost();
        BigDecimal finalAmount = mainShoppingCartDTO.getTotal()
                .subtract(campaignDiscount)
                .subtract(couponDiscount);

        mainShoppingCartDTO.setCampaignDiscount(campaignDiscount);
        mainShoppingCartDTO.setCouponDiscount(couponDiscount);
        mainShoppingCartDTO.setDeliveryCost(deliveryCost);
        mainShoppingCartDTO.setFinalAmount(finalAmount);
        return mainShoppingCartDTO;
    }

    private ShoppingCartDTO fetchShoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setTitle(Constant.TITLE);
        cart.setCartItems(new ArrayList<>());
        cart = shoppingCartRepository.save(cart);
        this.mainShoppingCartDTO = shoppingCartTransformer.transformToShoppingCartDTO(cart);
        return this.mainShoppingCartDTO;
    }

    private ShoppingCartDTO updateMainShoppingCartDTO() {
        ShoppingCart cart = shoppingCartRepository.findById(mainShoppingCartDTO.getId()).get();
        mainShoppingCartDTO = shoppingCartTransformer.transformToShoppingCartDTO(cart);
        mainShoppingCartDTO.setCategories(getProductsByCategory(cart.getCartItems()));
        return this.mainShoppingCartDTO;
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

    private ShoppingCart appendShoppingCart(ShoppingCart shoppingCart, ProductDTO productDTO) {
        List<ShoppingCartItem> cartItemList = shoppingCart.getCartItems();
        ShoppingCartItem cartItem = productTransformer.transformToCartItem(productDTO);
        cartItemList.add(cartItem);
        shoppingCart.setCartItems(cartItemList);
        return shoppingCart;
    }

}