package com.example.jwt.repository;

import com.example.jwt.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,String> {
    Role findByName(String name);
}
