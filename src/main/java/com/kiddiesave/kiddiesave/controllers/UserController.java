package com.kiddiesave.kiddiesave.controllers;

import com.kiddiesave.kiddiesave.RequestsAndResponses.*;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import com.kiddiesave.kiddiesave.repository.UserRepo;
import com.kiddiesave.kiddiesave.security.util.Claims;
import com.kiddiesave.kiddiesave.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private UserRepo userRepo;
    private Claims claims;

    public UserController(UserService userService, UserRepo userRepo, Claims claims) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.claims = claims;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signupRequest) throws UserNotFoundException {
        User usr = userRepo.getUserByEmail(signupRequest.getEmail());
        if(usr != null)
        {
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

    // edit user
    // ensure that the user is in the right role for edit
    @PostMapping(value = "/edituser")
    public ResponseEntity<?> editUser(@Valid @RequestBody UpdateUserRequest user, HttpServletRequest request) throws UserNotFoundException {
        String username = claims.getLoggedOnUsername(request); //make a global method or consider an alternate solution.
        if(username != null)
        {
            User editUser = userService.editUser(user,username);
            if(editUser != null)
            {
                // return a success message
                return ResponseEntity.ok(new MessageResponse("User update successful. "));
            }
        }
        return ResponseEntity.ok(new MessageResponse("An error occurred. Wrong user credentials for required for update. "));

    }

    // delete/disable user
    @PostMapping(value = "/deleteuser")
    public ResponseEntity<?> deleteUser(@Valid @RequestBody DeleteUserRequest deleteUserRequest) throws UserNotFoundException
    {
        if(deleteUserRequest.getEmail() != null)
        {
            String status = userService.deleteUser(deleteUserRequest.getEmail());

         if(status.equalsIgnoreCase("User deleted successfully."))
         {
             return ResponseEntity.ok(new MessageResponse(status));
         }
            return ResponseEntity.ok(new MessageResponse(status));
        }
        return new ResponseEntity(new ApiResponse(false, "An Error occurred on the server!", HttpStatus.INTERNAL_SERVER_ERROR),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // count users
    @PostMapping(value = "/usercount")
        public ResponseEntity<?> count(@Valid @RequestBody UserCountRequest status)
        {
            Long userCount = userService.getUsersCount(status.getUserStatus());
            if(userCount != null) {
                return ResponseEntity.ok(new ApiResponse(true, "User count returned.", userCount));
            }
            else
                return new ResponseEntity(new ApiResponse(false, "An Error occurred on the server!", HttpStatus.INTERNAL_SERVER_ERROR),
                        HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
