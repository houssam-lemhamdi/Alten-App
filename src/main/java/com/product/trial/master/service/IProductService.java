package com.product.trial.master.service;

import com.product.trial.master.dto.ProductDto;

import java.util.List;

public interface IProductService {
    public List<ProductDto> getAllProducts();
    public ProductDto getProductById(Long id);
    public void createProduct(ProductDto productDto);
    public ProductDto updateProduct(Long id, ProductDto productDto);
    public void deleteProduct(Long id);
}
