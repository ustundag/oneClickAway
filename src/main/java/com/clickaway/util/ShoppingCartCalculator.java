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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
        /*
        total = items.stream()
                .map(ShoppingCartItem::getPrice)
                .reduce(BigDecimal::add)
                .get();
        */
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
            switch (coupon.getDiscountType()) {
                case AMOUNT:
                    couponDiscount = current.subtract(coupon.getDiscount());
                case RATE:
                    couponDiscount = current.multiply(coupon.getDiscount()).divide(HUNDRED);
            }
        }
        return couponDiscount;
    }

    public BigDecimal calculateCampaignDiscount(List<ShoppingCartItem> cartItems, BigDecimal total) {
        BigDecimal campaignDiscount = new BigDecimal(0);
        Map<Long, List<ShoppingCartItem>> itemsGroupedByCategory =
                cartItems.stream().collect(Collectors.groupingBy(ShoppingCartItem::getCategoryId));

        campaignDiscount = itemsGroupedByCategory.keySet().stream()
                .map(catId -> calculateDiscount(catId, itemsGroupedByCategory.get(catId), total))
                .reduce(BigDecimal::add)
                .get();

        return campaignDiscount;
    }

    private BigDecimal calculateDiscount(Long catId, List<ShoppingCartItem> cartItems, BigDecimal total) {
        BigDecimal discount = new BigDecimal(0);
        List<Campaign> eligibleCampaigns = campaignRepository.findAllByCategoryIdAndItemLimitIsLessThanEqual(catId, cartItems.size());
        if (eligibleCampaigns.size() > 0) {
            // TODO apply the campaign with max discount, but now only first.
            Campaign campaign = eligibleCampaigns.get(0);
            switch (campaign.getDiscountType()) {
                case AMOUNT:
                    discount = campaign.getDiscount();
                case RATE:
                    discount = total.multiply(campaign.getDiscount()).divide(HUNDRED);
            }
        }
        return discount;
    }

    public BigDecimal calculateDeliveryCost(List<ShoppingCartItem> cartItems) {
        return new BigDecimal(0);
    }

}
