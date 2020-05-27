package com.clickaway.service;

import com.clickaway.entity.Product;
import com.clickaway.repository.ProductRepository;
import com.clickaway.service.dto.ProductDTO;
import com.clickaway.transformer.ProductTransformer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductServiceImplTest {
    @InjectMocks
    private ProductServiceImpl productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductTransformer productTransformer;

    @Test
    void addProduct() {
        Long id = 1L;
        String title = "clothes";
        // given
        Product Product = new Product();
        Product.setId(id);
        Product.setTitle(title);

        // when
        Product ProductMock = mock(Product.class);
        when(ProductMock.getId()).thenReturn(id);
        when(ProductMock.getTitle()).thenReturn(title);
        when(productRepository.save(any(Product.class)))
                .thenReturn(ProductMock);

        ProductDTO ProductMockDTO = mock(ProductDTO.class);
        when(ProductMockDTO.getId()).thenReturn(id);
        when(ProductMockDTO.getTitle()).thenReturn(title);
        when(productTransformer.transformToProductDTO(any(Product.class)))
                .thenReturn(ProductMockDTO);

        // then
        ProductDTO ProductDTO = productService.addProduct(Product);
        assertEquals(ProductDTO.getId(), id);
        assertEquals(ProductDTO.getTitle(), title);
    }

    @Test
    void getProduct() {
        // given
        Long id = 1L;

        // when
        Product ProductMock = mock(Product.class);
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(ProductMock));
        when(ProductMock.getId()).thenReturn(id);

        ProductDTO ProductMockDTO = mock(ProductDTO.class);
        when(ProductMockDTO.getId()).thenReturn(id);
        when(productTransformer.transformToProductDTO(any(Product.class)))
                .thenReturn(ProductMockDTO);

        // then
        ProductDTO ProductDTO = productService.getProduct(id);
        assertEquals(ProductDTO.getId(), id);
    }

    @Test
    void getAllProducts() {
        List<Product> ProductList = new ArrayList<>();
        Product Product = new Product();
        Product.setId(1L);
        ProductList.add(Product);
        Product = new Product();
        Product.setId(2L);
        ProductList.add(Product);

        // given

        // when
        when(productRepository.findAll()).thenReturn(ProductList);
        ProductDTO ProductMockDTO_1 = mock(ProductDTO.class);
        ProductDTO ProductMockDTO_2 = mock(ProductDTO.class);
        when(ProductMockDTO_1.getId()).thenReturn(1L);
        when(ProductMockDTO_2.getId()).thenReturn(2L);
        when(productTransformer.transformToProductDTO(any(Product.class)))
                .thenReturn(ProductMockDTO_1, ProductMockDTO_2);

        // then
        List<ProductDTO> allProducts = productService.getAllProducts();
        assertEquals(allProducts.size(), 2);
        assertEquals(allProducts.get(1).getId(), 2L);
    }

    @Test
    void deleteProduct() {
        Long id = 1L;
        productService.deleteProduct(id);
        verify(productRepository, times(1)).deleteById(eq(id));
    }
}