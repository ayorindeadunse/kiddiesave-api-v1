package com.kiddiesave.kiddiesave.repository;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    User getUserByEmail(String email) throws UserNotFoundException;
    User getUserByBvn(String bvn);
   User getUserByPhoneNumberLinkedWithBvn(String phoneNumberLinkedWithBvn);

}
