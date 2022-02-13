package com.example.warehouse.repository;

import com.example.warehouse.dto.ProductDto;
import com.example.warehouse.entity.InputOutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InputOutputProductRepository extends JpaRepository<InputOutputProduct, Long> {
    List<InputOutputProduct> findAllByInputOutputId(Long id);

    @Query("select new com.example.warehouse.dto.ProductDto(io.product.id, io.product.name, io.expiredDate, io.amount, io.price, io.inputOutput) from " +
            "InputOutputProduct io where  io.expiredDate > ?1")
    List<ProductDto> selectAllExpireDateSoon(LocalDateTime afterWeek);

}
