package com.kiddiesave.kiddiesave.repository;

import com.kiddiesave.kiddiesave.entity.Role;
import com.kiddiesave.kiddiesave.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
   Optional<Role> findByName(UserType userType);
}
