package org.example.ewatch.service.Impl;

import org.example.ewatch.dto.request.CategoryRequest;
import org.example.ewatch.dto.response.CategoryResponse;
import org.example.ewatch.entity.Category;
import org.example.ewatch.exception.NotFoundException;
import org.example.ewatch.mapper.CategoryMapper;
import org.example.ewatch.repository.CategoryRepository;
import org.example.ewatch.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toCategoryResponse).orElseThrow(() -> new NotFoundException("Category not found"));
    }

    @Override
    public void createCategory(CategoryRequest categoryRequest) {
        categoryRepository.save(categoryMapper.toCategory(categoryRequest));
    }

    @Override
    public void updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
