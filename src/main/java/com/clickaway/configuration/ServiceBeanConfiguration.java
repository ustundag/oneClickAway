package com.clickaway.configuration;

import com.clickaway.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanConfiguration extends AbstractBeanConfiguration {

    @Bean
    public CampaignService campaignService() {
        return new CampaignServiceImpl(campaignRepository, campaignTransformer);
    }

    @Bean
    public CouponService couponService() {
        return new CouponServiceImpl(couponRepository, couponTransformer);
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl(categoryRepository, categoryTransformer);
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl(productRepository, productTransformer);
    }

    @Bean
    public ShoppingCartService shoppingCartService() {
        return new ShoppingCartServiceImpl(shoppingCartRepository, shoppingCartTransformer);
    }

    @Bean
    public ShoppingCartItemService shoppingCartItemService() {
        return new ShoppingCartItemServiceImpl(cartItemRepository, cartItemTransformer);
    }
}
