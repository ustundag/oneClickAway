package com.clickaway.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

// https://stackoverflow.com/a/37727206/2966857
//@Data
@Getter
@Setter
@Entity
@Table(name = "cart")
@EqualsAndHashCode(callSuper = true)
public class ShoppingCart extends AbstractEntity {

    // https://stackoverflow.com/questions/16577907/hibernate-onetomany-relationship-causes-infinite-loop-or-empty-entries-in-json/47118403#47118403
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cart")
    private List<ShoppingCartItem> items;

}
