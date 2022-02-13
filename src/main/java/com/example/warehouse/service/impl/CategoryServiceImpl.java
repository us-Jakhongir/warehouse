package com.example.warehouse.service.impl;

import com.example.warehouse.dto.CategoryCreateDto;
import com.example.warehouse.entity.Category;
import com.example.warehouse.exception.NotFoundException;
import com.example.warehouse.repository.CategoryRepository;
import com.example.warehouse.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(CategoryCreateDto dto) {
        Category category = new Category(
                dto.getName(),
                categoryRepository.findById(dto.getParentId()).orElseThrow(() -> new NotFoundException("Parent Category Not Found")),
                dto.isActive()
        );
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new NotFoundException("Category Not Found"));
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryCreateDto dto) {
        Optional<Category> categoryParent = categoryRepository.findById(dto.getParentId());
        if (categoryParent.isEmpty())
            throw new NotFoundException("Category Parent Not Found");

        Category parentCategory = categoryParent.get();

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty())
            throw new NotFoundException("Category Not Found");
        Category category = categoryOptional.get();

        if (!category.getName().equals(dto.getName())) {
            category.setName(dto.getName());
        }

        if (!category.getActive().equals(dto.isActive())) {
            category.setActive(dto.isActive());
        }

        if (!category.getParent().getId().equals(dto.getParentId())) {
            category.setParent(parentCategory);
        }

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
