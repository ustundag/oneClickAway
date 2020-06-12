package com.clickaway.transformer;

import com.clickaway.calculator.DeliveryCostCalculatorImpl;
import com.clickaway.calculator.DiscountCalculatorFactory;
import com.clickaway.calculator.ShoppingCartCalculatorImpl;
import com.clickaway.entity.ShoppingCart;
import com.clickaway.entity.ShoppingCartItem;
import com.clickaway.service.dto.ShoppingCartDTO;
import com.clickaway.service.dto.ShoppingCartIndividualDTO;
import com.clickaway.service.dto.ShoppingCartItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.clickaway.types.CalculatorType.CAMPAIGN;
import static com.clickaway.types.CalculatorType.COUPON;

@Service
@RequiredArgsConstructor
public class ShoppingCartTransformer extends AbstractTransformer {
    private final ShoppingCartCalculatorImpl shoppingCartCalculator;
    private final DiscountCalculatorFactory discountCalculatorFactory;
    private final DeliveryCostCalculatorImpl deliveryCostCalculatorImpl;
    private final ShoppingCartItemTransformer shoppingCartItemTransformer;

    public ShoppingCartDTO transformToShoppingCartDTO(ShoppingCart shoppingCart) {
        URI uri = createUri(shoppingCart.getId(), "cart");
        ShoppingCartDTO shoppingCartDTO = ShoppingCartDTO.builder()
                .uri(uri).build();
        shoppingCartDTO.setId(shoppingCart.getId());
        shoppingCartDTO.setTitle(shoppingCart.getTitle());
        return shoppingCartDTO;
    }

    public ShoppingCartIndividualDTO transformToShoppingCartIndividualDTO(ShoppingCart shoppingCart)
            throws InstantiationException, IllegalAccessException {
        List<ShoppingCartItem> cartItems = shoppingCart.getItems();
        BigDecimal total = new BigDecimal(0);
        int quantity = 0;

        if (cartItems.size() > 0) {
            total = shoppingCartCalculator.calculateTotal(cartItems);
            quantity = cartItems.stream()
                    .mapToInt(ShoppingCartItem::getQuantity).sum();
        }

        ShoppingCartIndividualDTO individualCartDTO = ShoppingCartIndividualDTO.builder()
                .total(total)
                .categories(getProductsByCategory(cartItems))
                .quantity(quantity).build();

        BigDecimal deliveryCost = deliveryCostCalculatorImpl.calculateDeliveryCost(individualCartDTO);
        individualCartDTO.setDeliveryCost(deliveryCost);

        BigDecimal campaignDiscount = (BigDecimal) discountCalculatorFactory
                .getDiscountCalculator(CAMPAIGN)
                .calculateDiscount(individualCartDTO);
        individualCartDTO.setCampaignDiscount(campaignDiscount);

        BigDecimal couponDiscount = (BigDecimal) discountCalculatorFactory
                .getDiscountCalculator(COUPON)
                .calculateDiscount(individualCartDTO);
        individualCartDTO.setCouponDiscount(couponDiscount);

        BigDecimal finalAmount = total.subtract(campaignDiscount.add(couponDiscount));

        individualCartDTO.setFinalAmount(finalAmount);
        individualCartDTO.setId(shoppingCart.getId());
        individualCartDTO.setTitle(shoppingCart.getTitle());
        return individualCartDTO;
    }

    private Map<String, List<ShoppingCartItemDTO>> getProductsByCategory(List<ShoppingCartItem> cartItems) {
        Map<String, List<ShoppingCartItemDTO>> productItemsByCategory = new HashMap<>();
        Map<String, List<ShoppingCartItem>> itemsGroupedByCategory =
                cartItems.stream().collect(Collectors.groupingBy(shoppingCartItem ->
                        shoppingCartItem.getProduct().getCategory().getTitle()));

        for (String category : itemsGroupedByCategory.keySet()) {
            List<ShoppingCartItemDTO> cartItemDTOs = new ArrayList<>();
            itemsGroupedByCategory.get(category).stream().forEach(cartItem -> {
                ShoppingCartItemDTO cartItemDTO = shoppingCartItemTransformer.transformToCartItemDTO(cartItem);
                cartItemDTOs.add(cartItemDTO);
            });
            productItemsByCategory.put(category, cartItemDTOs);
        }

        return productItemsByCategory;
    }

}
