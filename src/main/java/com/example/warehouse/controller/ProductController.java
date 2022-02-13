package com.example.warehouse.controller;


import com.example.warehouse.dto.ProductCreateDto;
import com.example.warehouse.entity.Product;
import com.example.warehouse.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.persistence.SecondaryTable;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public Product save(@RequestBody ProductCreateDto dto) {
        Product product = productService.save(dto);
        return product;
    }

    @GetMapping
    private List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{product_id}")
    public Product findById(@PathVariable("product_id") Long productId) {
        Product product = productService.findById(productId);
        return product;
    }

    @PutMapping("/{product_id}")
    public Product edit(@PathVariable("product_id") Long productId, @RequestBody ProductCreateDto dto) {
        Product product = productService.updateProduct(productId, dto);
        return product;
    }

}
