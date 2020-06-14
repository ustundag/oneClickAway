package com.clickaway.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Category Entity", description = "Database Table Design", parent = AbstractEntity.class)
public class Category extends AbstractEntity {
    @ApiModelProperty(value = "Parent Category of Category")
    private String parentCategory;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    @ApiModelProperty(value = "Product List in Category")
    private List<Product> products = new ArrayList<>();

}
