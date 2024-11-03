package com.product.trial.master.mapper;

import com.product.trial.master.dto.ProductDto;
import com.product.trial.master.entity.ProductDocument;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface NoSqlProductMapper {
    ProductDto toDto(ProductDocument product);
    ProductDocument toEntity(ProductDto productDTO);

    List<ProductDto> toDtoList(List<ProductDocument> products);
    List<ProductDocument> toEntityList(List<ProductDto> productDTOs);
}
