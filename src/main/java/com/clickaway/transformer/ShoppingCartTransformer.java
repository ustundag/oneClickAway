package com.clickaway.transformer;

import com.clickaway.model.entity.ShoppingCart;
import com.clickaway.model.entity.ShoppingCartItem;
import com.clickaway.service.dto.ProductDTO;
import com.clickaway.service.dto.ShoppingCartDTO;
import com.clickaway.util.ShoppingCartCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartTransformer {
    private final ShoppingCartCalculator shoppingCartCalculator;

    private BigDecimal total = new BigDecimal(0);
    private BigDecimal couponDiscount = new BigDecimal(0);
    private BigDecimal campaignDiscount = new BigDecimal(0);
    private BigDecimal deliveryCost = new BigDecimal(0);
    private BigDecimal finalAmount = new BigDecimal(0);
    private int quantity = 0;

    public ShoppingCartDTO transformToShoppingCartDTO(ShoppingCart shoppingCart) {
        List<ShoppingCartItem> cartItems = shoppingCart.getCartItems();
        if (cartItems.size() > 0) {
            total = shoppingCartCalculator.calculateTotal(cartItems);
            campaignDiscount = shoppingCartCalculator.calculateCampaignDiscount(cartItems, total);
            couponDiscount = shoppingCartCalculator.calculateCouponDiscount(total.subtract(campaignDiscount));
            deliveryCost = shoppingCartCalculator.calculateDeliveryCost(cartItems);
        }
        quantity = cartItems.stream()
                .mapToInt(ShoppingCartItem::getQuantity)
                .sum();

        ShoppingCartDTO shoppingCartDTO = ShoppingCartDTO.builder()
                .categories(new HashMap<>())
                .quantity(quantity)
                .total(total)
                .couponDiscount(couponDiscount)
                .campaignDiscount(campaignDiscount)
                .deliveryCost(deliveryCost)
                .finalAmount(total.subtract(couponDiscount.add(campaignDiscount))).build();

        shoppingCartDTO.setId(shoppingCart.getId());
        shoppingCartDTO.setTitle(shoppingCart.getTitle());
        return shoppingCartDTO;
    }

    public ShoppingCartItem transformToShoppingCartItem(ProductDTO productDTO) {
        ShoppingCartItem cartItem = new ShoppingCartItem(
                productDTO.getPrice(),
                productDTO.getQuantity(),
                productDTO.getCategoryId());
        cartItem.setTitle(productDTO.getTitle());
        return cartItem;
    }

}
