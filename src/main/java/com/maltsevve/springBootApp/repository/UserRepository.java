package com.maltsevve.springBootApp.repository;

import com.maltsevve.springBootApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
