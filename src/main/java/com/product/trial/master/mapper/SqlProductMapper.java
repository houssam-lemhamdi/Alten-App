package com.product.trial.master.mapper;

import com.product.trial.master.dto.ProductDto;
import com.product.trial.master.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SqlProductMapper {

    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDTO);

    List<ProductDto> toDtoList(List<Product> products);
    List<Product> toEntityList(List<ProductDto> productDTOs);
}
