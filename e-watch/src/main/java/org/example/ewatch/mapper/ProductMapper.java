package org.example.ewatch.mapper;

import org.example.ewatch.dto.request.ProductRequest;
import org.example.ewatch.dto.response.ProductResponse;
import org.example.ewatch.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
     ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

     Product toProduct(ProductRequest productRequest);

     @Mapping(source = "category.id", target = "categoryResponse.id")
     @Mapping(source = "category.name", target = "categoryResponse.name")
     @Mapping(source = "category.description", target = "categoryResponse.description")
     ProductResponse toProductResponse(Product product);
}
