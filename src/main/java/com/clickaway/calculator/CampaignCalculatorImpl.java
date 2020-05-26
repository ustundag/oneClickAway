package com.clickaway.calculator;

import com.clickaway.constant.Constant;
import com.clickaway.entity.Campaign;
import com.clickaway.entity.ShoppingCart;
import com.clickaway.entity.ShoppingCartItem;
import com.clickaway.repository.CampaignRepository;
import com.clickaway.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CampaignCalculatorImpl implements DiscountCalculator {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CampaignRepository campaignRepository;

    @Override
    public BigDecimal calculateDiscount(BigDecimal current) {
        ShoppingCart cart = shoppingCartRepository.findById(Constant.shoppingCartID).get();
        List<ShoppingCartItem> cartItems = cart.getCartItems();

        BigDecimal campaignDiscount = new BigDecimal(0);
        Map<Long, List<ShoppingCartItem>> itemsGroupedByCategory =
                cartItems.stream().collect(Collectors.groupingBy(ShoppingCartItem::getCategoryId));

        int quantity = cartItems.stream()
                .mapToInt(ShoppingCartItem::getQuantity)
                .sum();

        List<Campaign> eligibleCampaigns = new ArrayList<>();
        for (Long catId : itemsGroupedByCategory.keySet()) {
            eligibleCampaigns = campaignRepository.findAllByCategoryIdAndItemLimitIsLessThanEqual(catId, quantity);
            if (eligibleCampaigns.size() > 0) {
                // TODO apply the campaign with max discount, but now only first.
                Campaign campaign = eligibleCampaigns.get(0);
                System.out.println("[ShoppingCartCalculator] calculateCampaignDiscount() -> Successfully applied the campaign below...");
                System.out.println(campaign.toString());
                switch (campaign.getDiscountType()) {
                    case AMOUNT:
                        campaignDiscount = campaign.getDiscount();
                        System.out.println("current:" + current + ", campaignDiscount: " + campaignDiscount + ", AMOUNT");
                        break;
                    case RATE:
                        campaignDiscount = current.multiply(campaign.getDiscount()).divide(Constant.HUNDRED);
                        System.out.println("current:" + current + ", campaignDiscount: " + campaignDiscount + ", RATE");
                        break;
                }
                break;
            }
        }
        return campaignDiscount;
    }

}
