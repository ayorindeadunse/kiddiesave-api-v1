package com.kiddiesave.kiddiesave.controllers;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ApiResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.MessageResponse;
import com.kiddiesave.kiddiesave.RequestsAndResponses.SignUpRequest;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.repository.UserRepo;
import com.kiddiesave.kiddiesave.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController // Marks the class as a rest controller
@RequestMapping("/api/user")
public class UserController {
    // Inject dependencies
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signupRequest) {
        User usr = userRepo.getUserByEmail(signupRequest.getEmail());
        if(usr != null)
        {
            //return message to user
            return ResponseEntity.ok(new MessageResponse("User already exists."));
        }
        User user = userService.createUser(signupRequest);
        if (user.getId() > 0) {
            // consider sending a token to the client so frontend can use as logic to send user to dashboard.
            return ResponseEntity.ok(new ApiResponse(true, "User registered successfully! " +
                    "Please check your email to activate your account. ",user));
            // remember to include logic for email activation.
        } else {
            return ResponseEntity.ok(new ApiResponse(false,"User registration failed",null));// or return
        }
    }
}
