package com.clickaway.transformer;

import com.clickaway.calculator.DeliveryCostCalculatorImpl;
import com.clickaway.calculator.DiscountCalculatorFactory;
import com.clickaway.calculator.ShoppingCartCalculatorImpl;
import com.clickaway.entity.ShoppingCart;
import com.clickaway.entity.ShoppingCartItem;
import com.clickaway.service.dto.ShoppingCartDTO;
import com.clickaway.service.dto.ShoppingCartIndividualDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
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

    public ShoppingCartDTO transformToShoppingCartDTO(ShoppingCart shoppingCart) {
        URI uri = createUri(shoppingCart.getId());
        ShoppingCartDTO shoppingCartDTO = ShoppingCartDTO.builder()
                //.items(shoppingCart.getItems())
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

    private Map<String, List<ShoppingCartItem>> getProductsByCategory(List<ShoppingCartItem> cartItems) {
        Map<String, List<ShoppingCartItem>> itemsGroupedByCategory =
                cartItems.stream().collect(Collectors.groupingBy(shoppingCartItem ->
                        shoppingCartItem.getProduct().getCategory().getTitle()));
        return itemsGroupedByCategory;
    }

}
