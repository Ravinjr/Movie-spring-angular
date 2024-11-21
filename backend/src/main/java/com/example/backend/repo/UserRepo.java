package com.example.backend.repo;

import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    boolean existsByEmailAndPassword(String email, String password);
    User findByFirstName(String firstName);

    Optional<User> findByEmail(String email);
}
