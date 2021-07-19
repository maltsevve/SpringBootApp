package com.maltsevve.springBootApp.repository;

import com.maltsevve.springBootApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
