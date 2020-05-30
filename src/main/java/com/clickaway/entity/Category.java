package com.clickaway.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

// https://stackoverflow.com/a/37727206/2966857
//@Data
@Getter
@Setter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Category extends AbstractEntity {
    private String parentCategory;

    // https://stackoverflow.com/questions/16577907/hibernate-onetomany-relationship-causes-infinite-loop-or-empty-entries-in-json/47118403#47118403
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> products = new ArrayList<>();

}
