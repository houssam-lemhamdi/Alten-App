package com.product.trial.master.service;

import com.product.trial.master.dto.ProductDto;

import java.util.List;

public interface IProductService {
    public List<ProductDto> getAllProducts();
    public ProductDto getProductById(Object id) throws Throwable;
    public void createProduct(ProductDto productDto);
    public ProductDto updateProduct(Object id, ProductDto productDto) throws Throwable;
    public void deleteProduct(Object id) throws Throwable;
}
