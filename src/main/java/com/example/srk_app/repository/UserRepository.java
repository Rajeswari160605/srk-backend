package com.example.srk_app.repository;

import com.example.srk_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    Optional<User> findByPin(String pin);
    Optional<User> findByPhone(String phone);

}
