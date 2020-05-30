package com.clickaway.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// https://stackoverflow.com/a/37727206/2966857
//@Data
@Getter
@Setter
@Entity
@Table(name = "item")
@EqualsAndHashCode(callSuper = true)
public class ShoppingCartItem extends AbstractEntity {

    @OneToOne
    private Product product;
    private int quantity;

    // https://stackoverflow.com/questions/16577907/hibernate-onetomany-relationship-causes-infinite-loop-or-empty-entries-in-json/47118403#47118403
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private ShoppingCart cart;

}
