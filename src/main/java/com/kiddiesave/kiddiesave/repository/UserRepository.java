package com.kiddiesave.kiddiesave.repository;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.ApplicationException;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User getUserByEmail(String email) throws UserNotFoundException, ApplicationException;
    List<User> findAllByStatus(boolean status);
    User getUserByBvn(String bvn);
    User getUserByPhoneNumberLinkedWithBvn(String phoneNumberLinkedWithBvn);

    // Change methods to return Optional to take care of null values

}
