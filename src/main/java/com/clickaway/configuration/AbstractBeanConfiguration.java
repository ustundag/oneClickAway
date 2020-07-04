package com.clickaway.configuration;

import com.clickaway.repository.*;
import com.clickaway.transformer.*;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractBeanConfiguration {
    @Autowired
    public CampaignRepository campaignRepository;
    @Autowired
    public CampaignTransformer campaignTransformer;
    @Autowired
    public CategoryRepository categoryRepository;
    @Autowired
    public CategoryTransformer categoryTransformer;
    @Autowired
    public CouponRepository couponRepository;
    @Autowired
    public CouponTransformer couponTransformer;
    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public ProductTransformer productTransformer;
    @Autowired
    public ShoppingCartItemRepository cartItemRepository;
    @Autowired
    public ShoppingCartItemTransformer cartItemTransformer;
    @Autowired
    public ShoppingCartRepository shoppingCartRepository;
    @Autowired
    public ShoppingCartTransformer shoppingCartTransformer;
}
