package com.kiddiesave.kiddiesave.repository;

import com.kiddiesave.kiddiesave.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    @Query(value = "FROM User WHERE email = ?1",nativeQuery = true)
User findByEmail(String email);
List<User> findAll();

//@Query(value = "FROM JwtUser WHERE email = ?1",nativeQuery = true) //replace with Linq query in service class
//User findUserByEmail(String email);

    //@Query(value = "FROM JwtUser WHERE bvn = ?1",nativeQuery = true) // replace with Linq query in service class
User getUserByBvn(String bvn);

//@Query(value = "FROM JwtUser WHERE phoneNumberLinkedWithBvn = ?1",nativeQuery = true) replace with Linq query in service class
User getUserByPhone(String mobile);
}
