package com.example.test.repository;

import com.example.test.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query("select u from UserModel u where u.email=?1 and u.password=?2")
    Optional<UserModel> login(String email, String password);

    Optional<UserModel> findByEmail(String email);
}
