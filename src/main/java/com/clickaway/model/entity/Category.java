package com.clickaway.model.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Category extends AbstractEntity {
    private Long parentCategoryId;
}
