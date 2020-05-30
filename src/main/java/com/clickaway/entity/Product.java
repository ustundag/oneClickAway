package com.clickaway.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

// https://stackoverflow.com/a/37727206/2966857
//@Data
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Product extends AbstractEntity {
    private BigDecimal price;
    private int quantity = 1;
    // https://stackoverflow.com/questions/16577907/hibernate-onetomany-relationship-causes-infinite-loop-or-empty-entries-in-json/47118403#47118403
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
