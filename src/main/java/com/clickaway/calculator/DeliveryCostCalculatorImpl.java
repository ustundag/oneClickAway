package com.clickaway.calculator;

import com.clickaway.constant.Constant;
import com.clickaway.service.dto.ShoppingCartIndividualDTO;
import com.clickaway.service.dto.ShoppingCartItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DeliveryCostCalculatorImpl {

    public BigDecimal calculateDeliveryCost(ShoppingCartIndividualDTO individualCart) {
        Map<String, List<ShoppingCartItemDTO>> itemProductsByCategory = individualCart.getCategories();
        int numOfDeliveries = itemProductsByCategory.keySet().size();
        int numOfProducts = 0;

        for (String category : itemProductsByCategory.keySet()) {
            List<ShoppingCartItemDTO> itemList = itemProductsByCategory.get(category);
            numOfProducts += itemList.stream().mapToInt(ShoppingCartItemDTO::getProductQuantity).sum();
        }

        BigDecimal cargoPrice = Constant.COST_PER_DELIVERY
                .multiply(BigDecimal.valueOf(numOfDeliveries));

        BigDecimal packagePrice = Constant.COST_PER_PRODUCT
                .multiply(BigDecimal.valueOf(numOfProducts));

        return cargoPrice.add(packagePrice).add(Constant.FIXED_COST);
    }


}
