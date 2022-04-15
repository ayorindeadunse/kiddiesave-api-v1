package com.kiddiesave.kiddiesave.security.util;

import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Claims {
    private String jwtToken;
    private String username;
    private String bearerToken;

    @Autowired
    private UserService userService;
    private User user;

}
