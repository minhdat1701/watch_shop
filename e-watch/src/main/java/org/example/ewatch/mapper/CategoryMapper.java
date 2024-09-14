package org.example.ewatch.mapper;

import org.example.ewatch.dto.request.CategoryRequest;
import org.example.ewatch.dto.response.CategoryResponse;
import org.example.ewatch.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategory(CategoryRequest categoryRequest);

    CategoryResponse toCategoryResponse(Category category);
}
