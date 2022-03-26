package com.kiddiesave.kiddiesave.security;

import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {
    // inject the dependencies
    @Autowired
    private UserRepo userRepo;
    User user;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        // get the user from the database
       user = userRepo.findByEmail(email);

        // No user found.
        if(user == null) {
            throw new UsernameNotFoundException("Could not find user with email " + email);
        }
        //Else, return a User Details object using the fetched user information
        return user;

       /* User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))); *///sets the role of the found user
        // consider setting separate roles for PARENT_USER and CHILD_USER to make the distinctions when both
        // user types log in.

        // Add implementation
        

    }
    public User getUserByUsername(String email)
    {
        user = userRepo.findByEmail(email);
        if(user == null)
        {
            throw new UsernameNotFoundException("Email not found" + email);
        }
        return user;
    }
}
