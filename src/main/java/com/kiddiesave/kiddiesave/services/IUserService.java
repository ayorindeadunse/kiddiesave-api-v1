package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.SignUpRequest;
import com.kiddiesave.kiddiesave.RequestsAndResponses.UpdateUserRequest;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService {
    User createUser(SignUpRequest user) throws UsernameNotFoundException;
    User editUser(UpdateUserRequest user, String loggedOnUser) throws UserNotFoundException;
    String deleteUser(String userEmail) throws UserNotFoundException;
    Long getUsersCount(Boolean status);
    List<User> getAllUsers();
}
