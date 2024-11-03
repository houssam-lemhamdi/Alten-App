package com.product.trial.master.repository.Impl;

import com.product.trial.master.entity.Product;
import com.product.trial.master.repository.ProductRepository;
import com.product.trial.master.repository.jpa.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
@RequiredArgsConstructor
public class SqlProductRepositoryImpl implements ProductRepository<Product,Long> {
    private final JpaProductRepository jpaProductRepository;
    @Override
    public List<Product> getAllProducts() {
        return jpaProductRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return jpaProductRepository.findById(id);
    }

    @Override
    public void createProduct(Product product) {
        jpaProductRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return jpaProductRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        jpaProductRepository.delete(product);
    }
}
