package com.clickaway.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cart")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShoppingCart Entity", description = "Database Table Design", parent = AbstractEntity.class)
public class ShoppingCart extends AbstractEntity {

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cart")
    @ApiModelProperty(value = "Item List in ShoppingCart")
    private List<ShoppingCartItem> items;

}
