package com.clickaway.service.dto;

import com.clickaway.entity.Product;
import lombok.*;

import java.net.URI;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class CategoryDTO extends AbstractDTO {
    private List<ProductDTO> productDTOs;
    private String parentCategory;
    private URI uri;
}
