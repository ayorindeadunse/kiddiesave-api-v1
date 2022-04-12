package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.SignUpRequest;
import com.kiddiesave.kiddiesave.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService{

    //Logger
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Override
    public User createUser(SignUpRequest user) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public User editUser(Long id, User user) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public String deleteUser(Long id, User user) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public Long getUsersCount() {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    //
}
