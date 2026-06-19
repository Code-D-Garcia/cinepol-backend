package com.codedgarcia.cinepol.repository;

import com.codedgarcia.cinepol.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(UUID id);
    Optional<User> findByUsernameIgnoreCase(String username);
    boolean existsByUsernameIgnoreCase(String username);

}
