package com.example.warehouse.service;

import com.example.warehouse.dto.CategoryCreateDto;
import com.example.warehouse.entity.Category;

import java.util.List;

public interface CategoryService {
    Category save(CategoryCreateDto dto);

    List<Category> findAll();

    Category findById(Long categoryId);

    Category updateCategory(Long categoryId, CategoryCreateDto dto);

    void deleteCategory(Long categoryId);
}
