package com.clickaway.repository;

import com.clickaway.entity.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    //boolean existsAllByProductId(Long productID);
    //Optional<ShoppingCartItem> findOneByProductId(Long productID);

}
