package com.clickaway.calculator;

import com.clickaway.constant.Constant;
import com.clickaway.entity.Campaign;
import com.clickaway.entity.Category;
import com.clickaway.repository.CampaignRepository;
import com.clickaway.repository.CategoryRepository;
import com.clickaway.service.dto.ShoppingCartIndividualDTO;
import com.clickaway.service.dto.ShoppingCartItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CampaignCalculatorImpl implements DiscountCalculator {
    private final CategoryRepository categoryRepository;
    private final CampaignRepository campaignRepository;

    @Override
    public BigDecimal calculateDiscount(ShoppingCartIndividualDTO individualCart) {
        BigDecimal campaignDiscount = new BigDecimal(0);
        Map<String, List<ShoppingCartItemDTO>> itemsGroupedByCategory = individualCart.getCategories();
        BigDecimal current = individualCart.getTotal();

        List<Campaign> eligibleCampaigns = new ArrayList<>();
        for (String title : itemsGroupedByCategory.keySet()) {
            int quantity = itemsGroupedByCategory.get(title).size();
            Category category = categoryRepository.getCategoryByTitle(title);
            eligibleCampaigns = campaignRepository.findAllByCategoryIdAndItemLimitIsLessThanEqual(category.getId(), quantity);
            if (eligibleCampaigns.size() > 0) {
                // TODO selection process is max(discount)
                //Campaign campaign = eligibleCampaigns.get(0);
                Campaign campaign = Collections.max(eligibleCampaigns, Comparator.comparing(c -> c.getDiscount()));
                System.out.println("[CampaignCalculatorImpl] calculateDiscount() -> Successfully applied the campaign below...");
                System.out.println(campaign.toString());
                switch (campaign.getDiscountType()) {
                    case AMOUNT:
                        campaignDiscount = campaign.getDiscount();
                        System.out.println("[CampaignCalculatorImpl] current:" + current + ", campaignDiscount: " + campaignDiscount + ", type: AMOUNT");
                        break;
                    case RATE:
                        campaignDiscount = current.multiply(campaign.getDiscount()).divide(Constant.HUNDRED);
                        System.out.println("[CampaignCalculatorImpl] current:" + current + ", campaignDiscount: " + campaignDiscount + ", type: RATE");
                        break;
                }
                break;
            }
        }
        return campaignDiscount;
    }

}
