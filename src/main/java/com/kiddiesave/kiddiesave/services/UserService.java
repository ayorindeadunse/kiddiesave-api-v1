package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.SignUpRequest;
import com.kiddiesave.kiddiesave.RequestsAndResponses.SignUpResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.UpdateUserRequest;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.ApplicationException;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface UserService {
    SignUpResponse createUser(SignUpRequest user);
    User editUser(UpdateUserRequest user, String loggedOnUser) throws UserNotFoundException, ApplicationException;
    String deleteUser(String userEmail) throws UserNotFoundException, ApplicationException;
    Long getUsersCount(Boolean status);
}
