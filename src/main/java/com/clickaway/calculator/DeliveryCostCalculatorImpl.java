package com.clickaway.calculator;

import com.clickaway.constant.Constant;
import com.clickaway.entity.ShoppingCartItem;
import com.clickaway.repository.ShoppingCartItemRepository;
import com.clickaway.repository.ShoppingCartRepository;
import com.clickaway.service.dto.ShoppingCartIndividualDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DeliveryCostCalculatorImpl {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;

    public BigDecimal calculateDeliveryCost(ShoppingCartIndividualDTO individualCart) {
        Map<String, List<ShoppingCartItem>> itemProductsByCategory = individualCart.getCategories();
        int numOfDeliveries = itemProductsByCategory.keySet().size();
        int numOfProducts = 0;

        for (String category : itemProductsByCategory.keySet()) {
            List<ShoppingCartItem> itemList = itemProductsByCategory.get(category);
            numOfProducts += itemList.stream().mapToInt(ShoppingCartItem::getQuantity).sum();
        }

        BigDecimal cargoPrice = Constant.COST_PER_DELIVERY
                .multiply(BigDecimal.valueOf(numOfDeliveries));

        BigDecimal packagePrice = Constant.COST_PER_PRODUCT
                .multiply(BigDecimal.valueOf(numOfProducts));

        return cargoPrice.add(packagePrice).add(Constant.FIXED_COST);
    }


}
