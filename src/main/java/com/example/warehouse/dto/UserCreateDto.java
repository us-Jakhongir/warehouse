package com.example.warehouse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;
    private Integer unique_number;
    private Long warehouseId;
    private Set<Long> roleIds;


}
