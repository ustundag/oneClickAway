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
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);

        // when
        Product productMock = mock(Product.class);
        when(productMock.getId()).thenReturn(id);
        when(productMock.getTitle()).thenReturn(title);
        when(productRepository.save(any(Product.class)))
                .thenReturn(productMock);

        ProductDTO productMockDTO = mock(ProductDTO.class);
        when(productMockDTO.getId()).thenReturn(id);
        when(productMockDTO.getTitle()).thenReturn(title);
        when(productTransformer.transformToProductDTO(any(Product.class)))
                .thenReturn(productMockDTO);

        // then
        ProductDTO productDTO = productService.addProduct(product);
        assertEquals(productDTO.getId(), id);
        assertEquals(productDTO.getTitle(), title);
    }

    @Test
    void getProduct() {
        // given
        Long id = 1L;

        // when
        Product productMock = mock(Product.class);
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(productMock));
        when(productMock.getId()).thenReturn(id);

        ProductDTO productMockDTO = mock(ProductDTO.class);
        when(productMockDTO.getId()).thenReturn(id);
        when(productTransformer.transformToProductDTO(any(Product.class)))
                .thenReturn(productMockDTO);

        // then
        ProductDTO productDTO = productService.getProduct(id);
        assertEquals(productDTO.getId(), id);
    }

    @Test
    void getAllProducts() {
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        productList.add(product);
        product = new Product();
        product.setId(2L);
        productList.add(product);

        // given

        // when
        when(productRepository.findAll()).thenReturn(productList);
        ProductDTO productMockDTO_1 = mock(ProductDTO.class);
        ProductDTO productMockDTO_2 = mock(ProductDTO.class);
        when(productMockDTO_1.getId()).thenReturn(1L);
        when(productMockDTO_2.getId()).thenReturn(2L);
        when(productTransformer.transformToProductDTO(any(Product.class)))
                .thenReturn(productMockDTO_1, productMockDTO_2);

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