package com.example.warehouse.repository;

import com.example.warehouse.entity.InputOutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InputOutputProductRepository extends JpaRepository<InputOutputProduct, Long> {
    List<InputOutputProduct> findAllByInputOutputId(Long id);
}
