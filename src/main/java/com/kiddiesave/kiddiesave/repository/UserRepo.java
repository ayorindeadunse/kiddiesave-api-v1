package com.kiddiesave.kiddiesave.repository;

import com.kiddiesave.kiddiesave.RequestsAndResponses.SignUpRequest;
import com.kiddiesave.kiddiesave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    //remember to add the database queries
    User getUserByEmail(String email);
   // User getUserByBvn(String bvn);
   // User getUserByPhone(String mobile);
   // Boolean existsByUsername(String email);

// Create role
}
