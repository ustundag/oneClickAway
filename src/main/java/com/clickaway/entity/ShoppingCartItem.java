package com.clickaway.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "item")
@EqualsAndHashCode(callSuper = true)
public class ShoppingCartItem extends AbstractEntity {

    @OneToOne
    private Product product;
    private int quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCart cart;
}
