package com.product.trial.master.service.Impl;

import com.product.trial.master.dto.ProductDto;
import com.product.trial.master.entity.Product;
import com.product.trial.master.entity.ProductDocument;
import com.product.trial.master.exception.RessourceNotFoundException;
import com.product.trial.master.mapper.NoSqlProductMapper;
import com.product.trial.master.mapper.SqlProductMapper;
import com.product.trial.master.repository.Impl.NoSqlProductRepositoryImpl;
import com.product.trial.master.repository.Impl.SqlProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private SqlProductRepositoryImpl sqlProductRepository;

    @Mock
    private NoSqlProductRepositoryImpl noSqlProductRepository;


    @Mock
    private SqlProductMapper sqlProductMapper;

    @Mock
    private NoSqlProductMapper noSqlProductMapper;

    private List<Product> sqlProducts;
    private List<ProductDocument> noSqlProducts;
    private ProductDto updatedProductDto;
    @BeforeEach
    void setUp() {
        // Prepare test data
        sqlProducts = new ArrayList<>();
        sqlProducts.add(new Product(1L, "P001", "Product 1", "Description 1", "http://example.com/image1.jpg", "Category 1", 19.99, 50, "REF001", 101L, null, 4));

        noSqlProducts = new ArrayList<>();
        noSqlProducts.add(new ProductDocument("1", "P002", "Product 2", "Description 2", "http://example.com/image2.jpg", "Category 2", 29.99, 30, "REF002", 102L, null, 5));

        updatedProductDto = new ProductDto("P001", "Updated Product", "Updated Description", "http://example.com/image1.jpg", "Category 1", 19.99, 50, "REF001", 101L, null, 4);
    }

    @Test
    void testGetAllProducts_nosql() {
        when(noSqlProductRepository.getAllProducts()).thenReturn(noSqlProducts);
        when(noSqlProductMapper.toDtoList(any())).thenReturn(List.of(new ProductDto("P002", "Product 2", "Description 2", "http://example.com/image2.jpg", "Category 2", 29.99, 30, "REF002", 102L, null, 5)));

        productService = new ProductService(noSqlProductRepository, sqlProductMapper, noSqlProductMapper); // Injecting NoSql repository
        List<ProductDto> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("P002", result.get(0).getCode());
        verify(noSqlProductRepository).getAllProducts();
        verify(noSqlProductMapper).toDtoList(any());
        verify(sqlProductMapper, never()).toDtoList(any());
    }
    @Test
    void testCreateProduct_sql() {
        ProductDto productDto = new ProductDto("P001", "Product 1", "Description 1", "http://example.com/image1.jpg", "Category 1", 19.99, 50, "REF001", 101L, null, 4);
        Product product = new Product(1L, "P001", "Product 1", "Description 1", "http://example.com/image1.jpg", "Category 1", 19.99, 50, "REF001", 101L, null, 4);

        when(sqlProductMapper.toEntity(any(ProductDto.class))).thenReturn(product);

        productService = new ProductService(sqlProductRepository, sqlProductMapper, noSqlProductMapper); // Injecting SQL repository
        productService.createProduct(productDto);

        verify(sqlProductRepository, times(1)).createProduct(product);
        verify(noSqlProductRepository, never()).createProduct(any());
    }
    @Test
    void testCreateProduct_nosql() {
        ProductDto productDto = new ProductDto("P002", "Product 2", "Description 2", "http://example.com/image2.jpg", "Category 2", 29.99, 30, "REF002", 102L, null, 5);
        ProductDocument productDocument = new ProductDocument("1", "P002", "Product 2", "Description 2", "http://example.com/image2.jpg", "Category 2", 29.99, 30, "REF002", 102L, null, 5);

        when(noSqlProductMapper.toEntity(any(ProductDto.class))).thenReturn(productDocument);

        productService = new ProductService(noSqlProductRepository, sqlProductMapper, noSqlProductMapper); // Injecting NoSQL repository
        productService.createProduct(productDto);

        verify(noSqlProductRepository, times(1)).createProduct(productDocument);
        verify(sqlProductRepository, never()).createProduct(any());
    }
    @Test
    void testDeleteProduct_sql() throws Throwable {
        Long productId = 1L;
        Product product = new Product(productId, "P001", "Product 1", "Description 1", "http://example.com/image1.jpg", "Category 1", 19.99, 50, "REF001", 101L, null, 4);

        when(sqlProductRepository.getProductById(productId)).thenReturn(Optional.of(product));

        productService = new ProductService(sqlProductRepository, sqlProductMapper, noSqlProductMapper); // Injecting SQL repository
        productService.deleteProduct(productId);

        verify(sqlProductRepository, times(1)).deleteProduct(product);
        verify(noSqlProductRepository, never()).deleteProduct(any());
    }
    @Test
    void testDeleteProduct_nosql() throws Throwable {
        String productId = "1";
        ProductDocument productDocument = new ProductDocument(productId, "P002", "Product 2", "Description 2", "http://example.com/image2.jpg", "Category 2", 29.99, 30, "REF002", 102L, null, 5);

        when(noSqlProductRepository.getProductById(productId)).thenReturn(Optional.of(productDocument));

        productService = new ProductService(noSqlProductRepository, sqlProductMapper, noSqlProductMapper); // Injecting NoSQL repository
        productService.deleteProduct(productId);

        verify(noSqlProductRepository, times(1)).deleteProduct(productDocument);
        verify(sqlProductRepository, never()).deleteProduct(any());
    }
    @Test
    void testDeleteProduct_sql_notFound() {
        Long productId = 1L;

        when(sqlProductRepository.getProductById(productId)).thenReturn(Optional.empty());

        productService = new ProductService(sqlProductRepository, sqlProductMapper, noSqlProductMapper); // Injecting SQL repository

        assertThrows(RessourceNotFoundException.class, () -> productService.deleteProduct(productId));
        verify(sqlProductRepository, never()).deleteProduct(any());
    }
    @Test
    void testDeleteProduct_nosql_notFound() {
        String productId = "1";

        when(noSqlProductRepository.getProductById(productId)).thenReturn(Optional.empty());

        productService = new ProductService(noSqlProductRepository, sqlProductMapper, noSqlProductMapper); // Injecting NoSQL repository

        assertThrows(RessourceNotFoundException.class, () -> productService.deleteProduct(productId));
        verify(noSqlProductRepository, never()).deleteProduct(any());
    }
}
