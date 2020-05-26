package com.clickaway.service.dto;

import lombok.*;

import java.net.URI;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class CategoryDTO extends AbstractDTO{
    private Long parentCategoryId;
    private URI uri;
}
