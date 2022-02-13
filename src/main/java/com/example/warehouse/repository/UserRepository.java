package com.example.warehouse.repository;


import com.example.warehouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u.* from users u" +
            "inner join users_roles ru on u.id = ru.user_id " +
            "inner join roles r on r.id = ru.roles_id where r.name = ?1", nativeQuery = true )
    List<User> selectAllUserRoleName(Set<String> name);
}
