package com.product.trial.master.service.Impl;

import com.product.trial.master.dto.ProductDto;
import com.product.trial.master.entity.Product;
import com.product.trial.master.entity.ProductDocument;
import com.product.trial.master.exception.RessourceNotFoundException;
import com.product.trial.master.mapper.NoSqlProductMapper;
import com.product.trial.master.mapper.SqlProductMapper;
import com.product.trial.master.repository.Impl.JsonProductRepositoryImpl;
import com.product.trial.master.repository.Impl.NoSqlProductRepositoryImpl;
import com.product.trial.master.repository.Impl.SqlProductRepositoryImpl;
import com.product.trial.master.repository.ProductRepository;
import com.product.trial.master.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final SqlProductMapper sqlProductMapper;
    private final NoSqlProductMapper noSqlProductMapper;
    @Override
    public List<ProductDto> getAllProducts() {
        if(productRepository instanceof SqlProductRepositoryImpl){
            return sqlProductMapper.toDtoList(productRepository.getAllProducts());
        }
        else if (productRepository instanceof NoSqlProductRepositoryImpl){
            return noSqlProductMapper.toDtoList(productRepository.getAllProducts());
        }
        else {
            return sqlProductMapper.toDtoList(productRepository.getAllProducts());
        }
    }

    @Override
    public ProductDto getProductById(Object id) throws Throwable {
        if(productRepository instanceof SqlProductRepositoryImpl){
            Product product= (Product) productRepository.getProductById(Long.valueOf((String)id))
                    .orElseThrow(()->new RessourceNotFoundException("Product","id",id.toString()));
            return sqlProductMapper.toDto(product);
        }
        else if(productRepository instanceof NoSqlProductRepositoryImpl){
            ProductDocument product= (ProductDocument) productRepository.getProductById((String)id)
                    .orElseThrow(()->new RessourceNotFoundException("Product","id",id.toString()));
            return noSqlProductMapper.toDto(product);
        }
        else{
            Product product= (Product) productRepository.getProductById(Long.valueOf((String)id))
                    .orElseThrow(()->new RessourceNotFoundException("Product","id",id.toString()));
            return sqlProductMapper.toDto(product);
        }
    }

    @Override
    public void createProduct(ProductDto productDto) {
        if(productRepository instanceof SqlProductRepositoryImpl){
            productRepository.createProduct(sqlProductMapper.toEntity(productDto));
        }
        else if (productRepository instanceof NoSqlProductRepositoryImpl){
            productRepository.createProduct(noSqlProductMapper.toEntity(productDto));
        }
        else {
            productRepository.createProduct(sqlProductMapper.toEntity(productDto));
        }
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Object id, ProductDto productDto) throws Throwable {
        if(productRepository instanceof SqlProductRepositoryImpl){
            Product existingProduct = (Product) productRepository.getProductById(Long.valueOf((String)id))
                    .orElseThrow(() -> new RessourceNotFoundException("Product", "id", id.toString()));
            updateProductFields(productDto,existingProduct);
            return sqlProductMapper.toDto((Product) productRepository.updateProduct(existingProduct));
        }
        else if (productRepository instanceof NoSqlProductRepositoryImpl){
            ProductDocument existingProductDocument = (ProductDocument) productRepository.getProductById((String)id)
                    .orElseThrow(() -> new RessourceNotFoundException("Product", "id", id.toString()));
            updateProductDocumentFields(productDto,existingProductDocument);
            return noSqlProductMapper.toDto((ProductDocument) productRepository.updateProduct(existingProductDocument));
        }
        else {
            Product product= (Product) productRepository.getProductById(Long.valueOf((String)id))
                    .orElseThrow(() -> new RessourceNotFoundException("Product", "id", id.toString()));
            updateProductFields(productDto,product);
            return sqlProductMapper.toDto((Product) productRepository.updateProduct(product));
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Object id) throws Throwable {
        if (productRepository instanceof SqlProductRepositoryImpl){
            Product product = (Product) productRepository.getProductById(Long.valueOf((String)id))
                    .orElseThrow(() -> new RessourceNotFoundException("Product", "id", id.toString()));
            productRepository.deleteProduct(product);
        }
        else if (productRepository instanceof NoSqlProductRepositoryImpl){
            ProductDocument productDocument = (ProductDocument) productRepository.getProductById((String)id)
                    .orElseThrow(() -> new RessourceNotFoundException("Product", "id", id.toString()));
            productRepository.deleteProduct(productDocument);
        }
        else {
            Product prod = (Product) productRepository.getProductById(Long.valueOf((String)id))
                    .orElseThrow(() -> new RessourceNotFoundException("Product", "id", id.toString()));
            productRepository.deleteProduct(prod);
        }
    }

    private void updateProductFields(ProductDto productDto,Product existingProduct){
            if (productDto.getCode() != null) existingProduct.setCode(productDto.getCode());
            if (productDto.getName() != null) existingProduct.setName(productDto.getName());
            if (productDto.getDescription() != null) existingProduct.setDescription(productDto.getDescription());
            if (productDto.getImage() != null) existingProduct.setImage(productDto.getImage());
            if (productDto.getCategory() != null) existingProduct.setCategory(productDto.getCategory());
            if (productDto.getPrice() != 0) existingProduct.setPrice(productDto.getPrice());
            if (productDto.getQuantity() != 0) existingProduct.setQuantity(productDto.getQuantity());
            if (productDto.getInternalReference() != null) existingProduct.setInternalReference(productDto.getInternalReference());
            if (productDto.getShellId() != null) existingProduct.setShellId(productDto.getShellId());
            if (productDto.getInventoryStatus() != null) existingProduct.setInventoryStatus(productDto.getInventoryStatus());
            if (productDto.getRating() != 0) existingProduct.setRating(productDto.getRating());
    }
    private void updateProductDocumentFields(ProductDto productDto,ProductDocument existingProductDocument){
        if (productDto.getCode() != null) existingProductDocument.setCode(productDto.getCode());
        if (productDto.getName() != null) existingProductDocument.setName(productDto.getName());
        if (productDto.getDescription() != null) existingProductDocument.setDescription(productDto.getDescription());
        if (productDto.getImage() != null) existingProductDocument.setImage(productDto.getImage());
        if (productDto.getCategory() != null) existingProductDocument.setCategory(productDto.getCategory());
        if (productDto.getPrice() != 0) existingProductDocument.setPrice(productDto.getPrice());
        if (productDto.getQuantity() != 0) existingProductDocument.setQuantity(productDto.getQuantity());
        if (productDto.getInternalReference() != null) existingProductDocument.setInternalReference(productDto.getInternalReference());
        if (productDto.getShellId() != null) existingProductDocument.setShellId(productDto.getShellId());
        if (productDto.getInventoryStatus() != null) existingProductDocument.setInventoryStatus(productDto.getInventoryStatus());
        if (productDto.getRating() != 0) existingProductDocument.setRating(productDto.getRating());
    }
}
