package com.kiddiesave.kiddiesave.controllers;

import com.kiddiesave.kiddiesave.RequestsAndResponses.JwtResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.LoginRequest;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import com.kiddiesave.kiddiesave.repository.UserRepo;
import com.kiddiesave.kiddiesave.security.JWTUtil;
import com.kiddiesave.kiddiesave.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

   private UserRepo userRepo;
   private JWTUtil jwtUtil;
   private AuthenticationManager authManager;

    public AuthController(UserRepo userRepo, JWTUtil jwtUtil, AuthenticationManager authManager) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }

    @PostMapping("/signin")
            public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws UserNotFoundException
    {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),
        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
       User user = userRepo.getUserByEmail(loginRequest.getUsernameOrEmail());
       String jwt = jwtUtil.generateToken(user);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        logger.info("User authenticated. Generating token...");
        //return token with user roles

        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getId(),
                userDetails.getEmail(),
                roles));
    }


}
