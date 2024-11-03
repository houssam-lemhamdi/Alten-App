package com.product.trial.master.repository.Impl;

import com.product.trial.master.entity.Product;
import com.product.trial.master.entity.ProductDocument;
import com.product.trial.master.repository.mongo.MongoProductRepository;
import com.product.trial.master.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NoSqlProductRepositoryImpl implements ProductRepository<ProductDocument,String> {
    private final MongoProductRepository mongoProductRepository;
    @Override
    public List<ProductDocument> getAllProducts() {
        return mongoProductRepository.findAll();
    }

    @Override
    public Optional<ProductDocument> getProductById(String id) {
        return mongoProductRepository.findById(id);
    }

    @Override
    public void createProduct(ProductDocument product) {
        mongoProductRepository.save(product);
    }

    @Override
    public ProductDocument updateProduct(ProductDocument product) {
        return mongoProductRepository.save(product);
    }

    @Override
    public void deleteProduct(ProductDocument product) {
        mongoProductRepository.delete(product);
    }
}
