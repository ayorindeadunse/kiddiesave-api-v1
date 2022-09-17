package com.kiddiesave.kiddiesave.controllers;

import com.kiddiesave.kiddiesave.RequestsAndResponses.*;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.ApplicationException;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import com.kiddiesave.kiddiesave.repository.UserRepository;
import com.kiddiesave.kiddiesave.security.util.Claims;
import com.kiddiesave.kiddiesave.services.UserServiceImpl;
import com.kiddiesave.kiddiesave.services.ValidateEmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserServiceImpl userServiceImpl;
    private UserRepository userRepository;
    private Claims claims;
    private ValidateEmailServiceImpl validateEmailService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, UserRepository userRepository, Claims claims,
                          ValidateEmailServiceImpl validateEmailService) {
        this.userServiceImpl = userServiceImpl;
        this.userRepository = userRepository;
        this.claims = claims;
        this.validateEmailService = validateEmailService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signupRequest) throws MessagingException {

        // Check if the object is null
        SignUpResponse user = userServiceImpl.createUser(signupRequest);
        // consider sending a token to the client so frontend can use as logic to send user to dashboard.
        // send validation email
        String message = validateEmailService.sendValidationEmail(signupRequest.getEmail());
        if (message.equalsIgnoreCase("Email successfully sent to user")) {
            return ResponseEntity.ok(new ApiResponse(true, "User registered successfully! " +
                    "Please check your email to activate your account. ", user));
        }
        return new ResponseEntity(new ApiResponse(false, "User Registration Failed", HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    // edit user
    // ensure that the user is in the right role for edit
    @PostMapping(value = "/edituser")
    public ResponseEntity<?> editUser(@Valid @RequestBody UpdateUserRequest user, HttpServletRequest request) throws UserNotFoundException, ApplicationException {
        String username = claims.getLoggedOnUsername(request); //make a global method or consider an alternate solution.
        if(username != null)
        {
            User editUser = userServiceImpl.editUser(user,username);
            if(editUser != null)
            {
                // return a success message
                return ResponseEntity.ok(new MessageResponse("User update successful."));
            }
        }
        return new ResponseEntity(new ApiResponse(false, "Error updating user information. Username" +
                "cannot be null or empty or other parameters are invalid", HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);

    }

    // delete/disable user
    @PostMapping(value = "/deleteuser")
    public ResponseEntity<?> deleteUser(HttpServletRequest request) throws UserNotFoundException, ApplicationException {
       // if(deleteUserRequest.getEmail() != null)
        String usernameOrEmail = claims.getLoggedOnUsername(request);
        {
           // String status = userServiceImpl.deleteUser(deleteUserRequest.getEmail());
            String status = userServiceImpl.deleteUser(usernameOrEmail);

         if(status.equalsIgnoreCase("User deleted successfully."))
         {
             return ResponseEntity.ok(new MessageResponse(status));
         }
            return ResponseEntity.ok(new MessageResponse(status));
        }
    }

    // count users
    @PostMapping(value = "/usercount")
        public ResponseEntity<?> count(@Valid @RequestBody UserCountRequest status)
        {
            Long userCount = userServiceImpl.getUsersCount(status.getUserStatus());
            if(userCount != null) {
                return ResponseEntity.ok(new ApiResponse(true, "User count returned.", userCount));
            }
            else
                return new ResponseEntity(new ApiResponse(false, "Please" +
                        "specify an appropriate parameter for the user status i.e, true or false", HttpStatus.BAD_REQUEST),
                        HttpStatus.BAD_REQUEST);
        }
}
