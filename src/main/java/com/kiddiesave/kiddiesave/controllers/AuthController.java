package com.kiddiesave.kiddiesave.controllers;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ApiResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.JwtResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.LoginRequest;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.ApplicationException;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import com.kiddiesave.kiddiesave.repository.UserRepository;
import com.kiddiesave.kiddiesave.security.JWTUtil;
import com.kiddiesave.kiddiesave.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

   private UserRepository userRepository;
   private JWTUtil jwtUtil;
   private AuthenticationManager authManager;

   @Autowired
    public AuthController(UserRepository userRepository, JWTUtil jwtUtil, AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }

    @PostMapping("/signin")
            public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws UserNotFoundException, ApplicationException {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),
        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
       User user = userRepository.getUserByEmail(loginRequest.getUsernameOrEmail());

       // check user status
        if(!user.isStatus())
            return new ResponseEntity(new ApiResponse(false, "" +
                    "Your profile is inactive, disabled or has been deleted.", HttpStatus.BAD_REQUEST),
                    HttpStatus.BAD_REQUEST);

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
