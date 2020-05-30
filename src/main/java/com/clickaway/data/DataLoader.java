package com.clickaway.data;

import com.clickaway.entity.*;
import com.clickaway.repository.*;
import com.clickaway.types.DiscountType;
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
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartItemRepository shoppingCartItemRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        insertCampaigns();
        insertCoupons();
        insertCategories();
        insertProducts();
        insertShoppingCart();
        insertShoppingCartItem();
        System.out.println("[oneClickAway] DataLoader() -> Initial data added.");
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
        campaign.setCategoryId(2L);
        campaign.setDiscount(new BigDecimal(50));
        campaign.setItemLimit(5);
        campaign.setDiscountType(DiscountType.RATE);
        campaignRepository.save(campaign);
        campaign = new Campaign();
        campaign.setTitle("occasional");
        campaign.setCategoryId(1L);
        campaign.setDiscount(new BigDecimal(15));
        campaign.setItemLimit(5);
        campaign.setDiscountType(DiscountType.AMOUNT);
        campaignRepository.save(campaign);
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

    private void insertCategories() {
        Category category = new Category();
        category.setTitle("tech");
        categoryRepository.save(category);
        category = new Category();
        category.setTitle("device");
        category.setParentCategory("tech");
        categoryRepository.save(category);
        category = new Category();
        category.setTitle("clothes");
        categoryRepository.save(category);
    }

    private void insertProducts() {
        Category category = new Category();
        Product product = new Product();
        product.setTitle("laptop");
        product.setPrice(new BigDecimal(15));
        category.setId(1L);
        product.setCategory(category);
        productRepository.save(product);
        product = new Product();
        product.setTitle("ssd");
        product.setQuantity(4);
        product.setPrice(new BigDecimal(15));
        category.setId(2L);
        product.setCategory(category);
        productRepository.save(product);
        product = new Product();
        product.setTitle("t-shirt");
        product.setQuantity(2);
        product.setPrice(new BigDecimal(15));
        category.setId(3L);
        product.setCategory(category);
        productRepository.save(product);
    }

    private void insertShoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setTitle("shopping_cart_customerID_123");
        shoppingCartRepository.save(cart);
    }

    private void insertShoppingCartItem() {
        ShoppingCart cart = new ShoppingCart();
        cart.setId(1L);
        Product product = new Product();
        product.setId(2L);
        ShoppingCartItem item = new ShoppingCartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(2);
        shoppingCartItemRepository.save(item);
    }
}