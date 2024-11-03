package com.product.trial.master.controllers;

import com.product.trial.master.constants.InventoryStatus;
import com.product.trial.master.constants.ProductConstants;
import com.product.trial.master.dto.ProductDto;
import com.product.trial.master.dto.ResponseDto;
import com.product.trial.master.exception.RessourceNotFoundException;
import com.product.trial.master.service.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {
    @Mock
    private IProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductDto product1;
    private ProductDto product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product1 = new ProductDto("P001", "Product 1", "Description of product 1", "image1.jpg", "Category 1", 19.99, 50, "REF001", 101L, InventoryStatus.INSTOCK, 4);
        product2 = new ProductDto("P002", "Product 2", "Description of product 2", "image2.jpg", "Category 2", 29.99, 30, "REF002", 102L, InventoryStatus.LOWSTOCK, 5);
    }

    @Test
    void getAllProducts_ShouldReturnListOfProducts() {
        when(productService.getAllProducts()).thenReturn(Arrays.asList(product1, product2));

        ResponseEntity<List<ProductDto>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void getProductById_ShouldReturnProduct_WhenProductExists() throws Throwable {
        when(productService.getProductById(1L)).thenReturn(product1);

        ResponseEntity<ProductDto> response = productController.getProductById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product1, response.getBody());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void getProductById_ShouldThrowException_WhenProductDoesNotExist() throws Throwable {
        when(productService.getProductById(99L)).thenThrow(new RessourceNotFoundException("Product", "id", "99"));

        Exception exception = assertThrows(RessourceNotFoundException.class, () -> {
            productController.getProductById(99L);
        });

        assertEquals("Product not found with the given input data id :'99'", exception.getMessage());
    }



    @Test
    void updateProduct_ShouldUpdateProduct_WhenProductExists() throws Throwable {
        when(productService.updateProduct(1L, product1)).thenReturn(product1);

        ResponseEntity<ProductDto> response = productController.updateProduct(1L, product1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product1, response.getBody());
        verify(productService, times(1)).updateProduct(1L, product1);
    }

    @Test
    void deleteProduct_ShouldDeleteProduct_WhenProductExists() throws Throwable {
        doNothing().when(productService).deleteProduct(1L);

        ResponseEntity<Void> response = productController.deleteProduct(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(1L);
    }

    @Test
    void deleteProduct_ShouldThrowException_WhenProductDoesNotExist() throws Throwable {
        doThrow(new RessourceNotFoundException("Product", "id", "99")).when(productService).deleteProduct(99L);

        Exception exception = assertThrows(RessourceNotFoundException.class, () -> {
            productController.deleteProduct(99L);
        });

        assertEquals("Product not found with the given input data id :'99'", exception.getMessage());
    }
    @Test
    void createProduct_ShouldReturnCreatedStatus_WhenProductIsCreatedSuccessfully() {
        doNothing().when(productService).createProduct(product1);
        ResponseEntity<ResponseDto> response = productController.createProduct(product1);
        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Expected HTTP status 201 CREATED");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(ProductConstants.STATUS_201, response.getBody().getStatusCode(), "Status should match STATUS_201 constant");
        assertEquals(ProductConstants.MESSAGE_201, response.getBody().getStatusMsg(), "Message should match MESSAGE_201 constant");
        verify(productService, times(1)).createProduct(product1);
    }

}