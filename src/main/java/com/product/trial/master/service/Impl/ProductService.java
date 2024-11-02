package com.product.trial.master.service.Impl;

import com.product.trial.master.dto.ProductDto;
import com.product.trial.master.entity.Product;
import com.product.trial.master.exception.RessourceNotFoundException;
import com.product.trial.master.mapper.ProductMapper;
import com.product.trial.master.repository.ProductRepository;
import com.product.trial.master.service.IProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public List<ProductDto> getAllProducts() {
        return productMapper.toDtoList(productRepository.findAll());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product=productRepository.findById(id)
                .orElseThrow(()->new RessourceNotFoundException("Product","id",id.toString()));
        return productMapper.toDto(product);
    }

    @Override
    public void createProduct(ProductDto productDto) {
        productRepository.save(productMapper.toEntity(productDto));
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Product", "id", id.toString()));

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

        return productMapper.toDto(productRepository.save(existingProduct));
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Product", "id", id.toString()));
        productRepository.delete(product);
    }
}
