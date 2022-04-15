package com.kiddiesave.kiddiesave.controllers;

import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Marks the class as a rest controller
@RequestMapping("/api/user") // Requests made to /api/user/anything will be handled by this class
public class UserController {
    // Inject dependencies
    @Autowired private UserRepo userRepo;

    // Define the function to handle the GET route to fetch user information of the authenticated user.
    @GetMapping("/info")
    public User getUserDetails()
    {
        // Retrieve the email from the security context
      /*  String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Fetch and return user details
        return userRepo.findByEmail(email).get();*/
        return null;
    }
}
