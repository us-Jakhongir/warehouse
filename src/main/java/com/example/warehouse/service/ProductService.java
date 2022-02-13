package com.example.warehouse.service;

import com.example.warehouse.dto.ProductCreateDto;
import com.example.warehouse.entity.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {
    Product save(ProductCreateDto dto);

    List<Product> findAll();

    Product findById(Long productId);

    Product updateProduct(Long productId, ProductCreateDto dto);
}
