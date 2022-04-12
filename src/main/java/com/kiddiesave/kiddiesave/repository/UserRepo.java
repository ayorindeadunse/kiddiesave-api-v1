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
List<User> findAll();

//@Query(value = "FROM JwtUser WHERE email = ?1",nativeQuery = true) //replace with Linq query in service class
//User findUserByEmail(String email);

@Query(value = "FROM JwtUser WHERE bvn = ?1",nativeQuery = true) // replace with Linq query in service class
User getUserByBvn(String bvn);

@Query(value = "FROM JwtUser WHERE phoneNumberLinkedWithBvn = ?1",nativeQuery = true)
User getUserByPhone(String mobile);

// Create role
}
