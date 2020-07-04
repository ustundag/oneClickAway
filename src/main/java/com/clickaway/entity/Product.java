package com.clickaway.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Product Entity", description = "Database Table Design", parent = AbstractEntity.class)
public class Product extends AbstractEntity {
    @ApiModelProperty(value = "Price of Product")
    private BigDecimal price;
    @ApiModelProperty(value = "Quantity of Product")
    private int quantity = 1;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    @ApiModelProperty(value = "Category of Product")
    private Category category;

}
