package com.kiddiesave.kiddiesave.security.services;

import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: "+email));

        return UserDetailsImpl.build(user);
    }
}
