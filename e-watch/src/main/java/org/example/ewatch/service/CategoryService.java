package org.example.ewatch.service;

import org.example.ewatch.dto.request.CategoryRequest;
import org.example.ewatch.dto.response.CategoryResponse;
import org.example.ewatch.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<CategoryResponse> getAllCategories();
    public CategoryResponse getCategoryById(Long id);
    public void createCategory(CategoryRequest categoryRequest);
    public void updateCategory(Long id, CategoryRequest categoryRequest);
    public void deleteCategory(Long id);
}
