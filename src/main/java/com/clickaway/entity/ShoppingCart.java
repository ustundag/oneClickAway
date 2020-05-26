package com.clickaway.entity;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class ShoppingCart extends AbstractEntity {
    @OneToMany(cascade = CascadeType.ALL)
    private List<ShoppingCartItem> cartItems;
}
