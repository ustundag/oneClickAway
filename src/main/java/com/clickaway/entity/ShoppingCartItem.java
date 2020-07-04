package com.clickaway.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShoppingCartItem Entity", description = "Database Table Design", parent = AbstractEntity.class)
public class ShoppingCartItem extends AbstractEntity {

    @OneToOne
    @ApiModelProperty(value = "Product of ShoppingCartItem")
    private Product product;
    @ApiModelProperty(value = "Quantity of ShoppingCartItem")
    private int quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cart_id")
    @ApiModelProperty(value = "ShoppingCart of ShoppingCartItem")
    private ShoppingCart cart;
}
