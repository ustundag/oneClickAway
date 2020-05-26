package com.clickaway.data;

import com.clickaway.model.entity.Campaign;
import com.clickaway.model.entity.Category;
import com.clickaway.model.entity.Coupon;
import com.clickaway.model.entity.Product;
import com.clickaway.model.types.DiscountType;
import com.clickaway.repository.CampaignRepository;
import com.clickaway.repository.CategoryRepository;
import com.clickaway.repository.CouponRepository;
import com.clickaway.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
    private final CouponRepository couponRepository;
    private final CampaignRepository campaignRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        insertCampaigns();
        insertCategories();
        insertCoupons();
        insertProducts();
        System.out.println("[DataLoader] Initial data added.");
    }

    private void insertCampaigns() {
        Campaign campaign = new Campaign();
        campaign.setTitle("device-summer");
        campaign.setCategoryId(2L);
        campaign.setDiscount(new BigDecimal(20));
        campaign.setItemLimit(3);
        campaign.setDiscountType(DiscountType.RATE);
        campaignRepository.save(campaign);
        campaign = new Campaign();
        campaign.setTitle("clothes-winter");
        campaign.setCategoryId(3L);
        campaign.setDiscount(new BigDecimal(50));
        campaign.setItemLimit(5);
        campaign.setDiscountType(DiscountType.RATE);
        campaignRepository.save(campaign);
        campaign = new Campaign();
        campaign.setTitle("occasional");
        campaign.setCategoryId(1L);
        campaign.setDiscount(new BigDecimal(5));
        campaign.setItemLimit(5);
        campaign.setDiscountType(DiscountType.AMOUNT);
        campaignRepository.save(campaign);
    }

    private void insertCategories() {
        Category category = new Category();
        category.setTitle("tech");
        categoryRepository.save(category);
        category = new Category();
        category.setTitle("device");
        category.setParentCategoryId(1L);
        categoryRepository.save(category);
        category = new Category();
        category.setTitle("clothes");
        categoryRepository.save(category);
    }

    private void insertCoupons() {
        Coupon coupon = new Coupon();
        coupon.setTitle("gift");
        coupon.setMinAmount(new BigDecimal(30));
        coupon.setDiscount(new BigDecimal(10));
        coupon.setDiscountType(DiscountType.RATE);
        couponRepository.save(coupon);
        coupon = new Coupon();
        coupon.setTitle("promotion");
        coupon.setMinAmount(new BigDecimal(40));
        coupon.setDiscount(new BigDecimal(15));
        coupon.setDiscountType(DiscountType.AMOUNT);
        couponRepository.save(coupon);
    }

    private void insertProducts() {
        Product product = new Product();
        product.setTitle("laptop");
        product.setCategoryId(2L);
        product.setPrice(new BigDecimal(10));
        productRepository.save(product);
        product = new Product();
        product.setTitle("phone");
        product.setCategoryId(2L);
        product.setPrice(new BigDecimal(20));
        productRepository.save(product);
        product = new Product();
        product.setTitle("t-shirt");
        product.setCategoryId(3L);
        product.setPrice(new BigDecimal(30));
        productRepository.save(product);
    }
}