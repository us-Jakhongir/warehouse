package com.example.warehouse.service.impl;

import com.example.warehouse.dto.ProductCreateDto;
import com.example.warehouse.entity.Attachment;
import com.example.warehouse.entity.Category;
import com.example.warehouse.entity.Measurement;
import com.example.warehouse.entity.Product;
import com.example.warehouse.exception.NotFoundException;
import com.example.warehouse.repository.AttachmentRepository;
import com.example.warehouse.repository.CategoryRepository;
import com.example.warehouse.repository.MeasurementRepository;
import com.example.warehouse.repository.ProductRepository;
import com.example.warehouse.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final MeasurementRepository measurementRepository;
    private final AttachmentRepository attachmentRepository;


    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              MeasurementRepository measurementRepository,
                              AttachmentRepository attachmentRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.measurementRepository = measurementRepository;
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Product save(ProductCreateDto dto) {

        List<Attachment> attachmentList = new ArrayList<>();
        for (Long attachmentId : dto.getAttachmentIds()) {
            Optional<Attachment> attachmentOptional = attachmentRepository.findById(attachmentId);
            if (attachmentOptional.isEmpty())
                throw new NotFoundException("Not Found Attachment");
            attachmentList.add(attachmentOptional.get());
        }

        Product product = new Product(
                dto.getName(),
                categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new NotFoundException("Not Found Category ID")),
                measurementRepository.findById(dto.getMeasurementId()).orElseThrow(() -> new NotFoundException("Not Found Measurement ID")),
                attachmentList,
                UUID.randomUUID());

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        List<Product> productSet = productRepository.findAll();
        return productSet;
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Not Found Product"));
    }

    @Override
    public Product updateProduct(Long productId, ProductCreateDto dto) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty())
            throw new NotFoundException("Not Found Product with ID: " + productId);
        Product product = productOptional.get();

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Not Found Category ID: " + dto.getCategoryId()));

        Measurement measurement = measurementRepository.findById(dto.getMeasurementId())
                .orElseThrow(() -> new NotFoundException("Not Found Measurement ID: " + dto.getMeasurementId()));

        List<Attachment> attachmentList = new ArrayList<>();
        for (Long attachmentId : dto.getAttachmentIds()) {
            Optional<Attachment> attachmentOptional = attachmentRepository.findById(attachmentId);
            if (attachmentOptional.isEmpty())
                throw new NotFoundException("Not Found Attachment ID: " + attachmentId);
            Attachment attachment = attachmentOptional.get();

            if (!product.getAttachments().contains(attachment))
                attachmentList.add(attachment);
        }

        product.setAttachments(attachmentList);

        if (!product.getName().equals(dto.getName())) {
            product.setName(dto.getName());
        }

        if (!product.getCategory().equals(category)) {
            product.setCategory(category);
        }

        if (!product.getMeasurement().equals(dto.getMeasurementId())) {
            product.setMeasurement(measurement);
        }

        return product;
    }
}
