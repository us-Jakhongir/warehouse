package com.example.warehouse.entity;


import com.example.warehouse.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends AbsEntity {


    @Column(nullable = false)
    private String firstname;

    @Column()
    private String lastname;

    @Column(nullable = false)
    @Size(min = 7, max = 9)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne
    private Warehouse warehouse;


    @Column(nullable = false, unique = true)
    private Integer uniqueNumber;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

}
