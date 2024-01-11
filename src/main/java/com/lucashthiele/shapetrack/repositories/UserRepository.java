package com.lucashthiele.shapetrack.repositories;

import com.lucashthiele.shapetrack.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
