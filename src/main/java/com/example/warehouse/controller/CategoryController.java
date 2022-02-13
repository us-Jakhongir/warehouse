package com.example.warehouse.controller;


import com.example.warehouse.dto.CategoryCreateDto;
import com.example.warehouse.entity.Category;
import com.example.warehouse.rest.response.Response;
import com.example.warehouse.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public Category save(@RequestBody CategoryCreateDto dto) {
        return categoryService.save(dto);
    }

    @GetMapping
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{category_id}")
    public Category findById(@PathVariable("category_id") Long categoryId) {
        return categoryService.findById(categoryId);
    }

    @PutMapping("/{category_id}")
    public Category edit(@PathVariable("category_id") Long categoryId, @RequestBody CategoryCreateDto dto) {
        return categoryService.updateCategory(categoryId, dto);
    }


    @DeleteMapping("/{category_id}")
    public Response remove(@PathVariable("category_id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new  Response("Successfully Deleted");
    }


}
