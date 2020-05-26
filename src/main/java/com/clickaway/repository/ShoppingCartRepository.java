package com.clickaway.repository;

import com.clickaway.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    // update shopping_cart_item set quantity = 10 where title = 't-shirt';
    @Modifying
    @Query("update ShoppingCartItem set quantity = quantity + ?2 where title = ?1")
    void updateCartItemQuantityByTitle(String title, int quantity);

    @Query("SELECT CASE WHEN COUNT(i) > 0 THEN true " +
            "ELSE false " +
            "END FROM ShoppingCartItem i WHERE i.title = :title")
    boolean existsCartItemByTitle(@Param("title") String title);

    @Query("select count(distinct item.categoryId) from ShoppingCartItem item")
    int countDistinctByCategoryID();

    @Query("SELECT SUM(item.quantity) FROM ShoppingCartItem item")
    int numberOfProductQuantity();

}
