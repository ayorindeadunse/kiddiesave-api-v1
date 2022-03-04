package com.kiddiesave.kiddiesave.security;

import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {
    // inject the dependencies
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        // get the user from the database
        Optional<User> userRes = userRepo.findByEmail(email);

        // No user found.
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Could not find user with email "+ email);
        //Else, return a User Details object using the fetched user information
        User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))); //sets the role of the found user
        // consider setting separate roles for PARENT_USER and CHILD_USER to make the distinctions when both
        // user types log in.
        
    }
}
