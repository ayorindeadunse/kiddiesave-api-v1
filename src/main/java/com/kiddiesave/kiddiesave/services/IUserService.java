package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.SignUpRequest;
import com.kiddiesave.kiddiesave.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService {
    User createUser(SignUpRequest user) throws UsernameNotFoundException;
    User editUser(Long id,User user) throws UsernameNotFoundException;
    String deleteUser(Long id,User user) throws UsernameNotFoundException;
    Long getUsersCount();
    List<User> getAllUsers();
}
