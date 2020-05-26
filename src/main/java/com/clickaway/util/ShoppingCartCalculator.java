package com.clickaway.util;

import com.clickaway.model.entity.Campaign;
import com.clickaway.model.entity.Coupon;
import com.clickaway.model.entity.ShoppingCartItem;
import com.clickaway.repository.CampaignRepository;
import com.clickaway.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartCalculator {
    @Autowired
    private final CouponRepository couponRepository;
    @Autowired
    private final CampaignRepository campaignRepository;
    private final BigDecimal HUNDRED = new BigDecimal(100);

    public BigDecimal calculateTotal(List<ShoppingCartItem> items) {
        BigDecimal total = new BigDecimal(0);
        total = items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal::add)
                .get();
        return total;
    }

    public BigDecimal calculateCouponDiscount(BigDecimal current) {
        BigDecimal couponDiscount = new BigDecimal(0);
        List<Coupon> eligibleCoupons = couponRepository.findAllByMinAmountIsLessThanEqual(current);
        if (eligibleCoupons.size() != 0) {
            Coupon coupon = Collections.max(eligibleCoupons, Comparator.comparing(c -> c.getMinAmount()));
            System.out.println("[ShoppingCartCalculator] calculateCouponDiscount() -> Successfully applied the coupon below...");
            System.out.println(coupon.toString());
            switch (coupon.getDiscountType()) {
                case AMOUNT:
                    couponDiscount = coupon.getDiscount();
                    System.out.println("current:" + current + ", couponDiscount: " + couponDiscount + ", AMOUNT");
                    break;
                case RATE:
                    couponDiscount = current.multiply(coupon.getDiscount()).divide(HUNDRED);
                    System.out.println("current:" + current + ", couponDiscount: " + couponDiscount + ", RATE");
                    break;
            }
        }
        return couponDiscount;
    }

    public BigDecimal calculateCampaignDiscount(List<ShoppingCartItem> cartItems, BigDecimal current) {
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
                        campaignDiscount = current.multiply(campaign.getDiscount()).divide(HUNDRED);
                        System.out.println("current:" + current + ", campaignDiscount: " + campaignDiscount + ", RATE");
                        break;
                }
                break;
            }
        }
        return campaignDiscount;
    }

    public BigDecimal calculateDeliveryCost(List<ShoppingCartItem> cartItems) {
        return new BigDecimal(0);
    }

}
