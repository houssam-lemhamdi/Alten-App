package com.product.trial.master.repository;

import com.product.trial.master.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository<T,ID> {
    List<T> getAllProducts();
    Optional<T> getProductById(ID id);
    void createProduct(T product);
    T updateProduct(T product);
    void deleteProduct(T product);
}
