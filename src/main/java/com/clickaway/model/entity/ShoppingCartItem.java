package com.clickaway.model.entity;

import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class ShoppingCartItem extends AbstractEntity {
    private BigDecimal price;
    private int quantity;
    private Long categoryId;
}
