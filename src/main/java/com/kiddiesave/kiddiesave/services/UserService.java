package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.SignUpRequest;
import com.kiddiesave.kiddiesave.RequestsAndResponses.UpdateUserRequest;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.UserException;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService {
    User createUser(SignUpRequest user) throws UsernameNotFoundException, UserNotFoundException, UserException;
    User editUser(UpdateUserRequest user, String loggedOnUser) throws UserNotFoundException, UserException;
    String deleteUser(String userEmail) throws UserNotFoundException, UserException;
    Long getUsersCount(Boolean status);
}
