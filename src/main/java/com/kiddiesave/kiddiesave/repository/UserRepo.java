package com.kiddiesave.kiddiesave.repository;

import com.kiddiesave.kiddiesave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
public Optional<User> findByEmail(String email);
}
