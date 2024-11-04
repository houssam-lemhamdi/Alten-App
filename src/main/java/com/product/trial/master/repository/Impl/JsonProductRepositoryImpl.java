package com.product.trial.master.repository.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.trial.master.entity.Product;
import com.product.trial.master.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JsonProductRepositoryImpl implements ProductRepository<Product,Long> {
    @Value("${products.file.path}")
    private String filePath;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Product> getAllProducts() {
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return getAllProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public void createProduct(Product product) {
        List<Product> products = getAllProducts();
        if (products.isEmpty()){
            products=new ArrayList<>();
        }
        Long newId=products.stream()
                        .mapToLong(Product::getId)
                        .max()
                        .orElse(0)+1;
        product.setId(newId);
        products.add(product);
        writeToFile(products);
    }

    @Override
    public Product updateProduct(Product product) {
        List<Product> products = getAllProducts();
        products.removeIf(p -> p.getId().equals(product.getId()));
        products.add(product);
        writeToFile(products);
        return product;
    }

    @Override
    public void deleteProduct(Product product) {
        List<Product> products = getAllProducts();
        products.removeIf(p -> p.getId().equals(product.getId()));
        writeToFile(products);
    }

    private void writeToFile(List<Product> products) {
        try {
            objectMapper.writeValue(new File(filePath), products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
