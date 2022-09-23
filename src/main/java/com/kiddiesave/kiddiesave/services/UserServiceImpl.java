package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.*;
import com.kiddiesave.kiddiesave.entity.Role;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.entity.UserType;
import com.kiddiesave.kiddiesave.exceptions.ApplicationException;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import com.kiddiesave.kiddiesave.repository.RoleRepository;
import com.kiddiesave.kiddiesave.repository.UserRepository;
import com.kiddiesave.kiddiesave.security.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    //Logger
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final DateUtils dateUtils;
    private final BvnLookupServiceImpl bvnLookupService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository, DateUtils dateUtils,
                           BvnLookupServiceImpl bvnLookupService)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.dateUtils = dateUtils;
        this.bvnLookupService = bvnLookupService;

    }

    @Override
    public SignUpResponse createUserWithBvn(ValidateBvnRequest validateBvnRequest) throws IOException {
       // return null;
        BvnLookupServiceResponse bvnLookupServiceResponse = bvnLookupService.bvnLookup(validateBvnRequest.getBvn());
        if(bvnLookupServiceResponse != null)
        {
            //Map bvnLookupServiceResponse to SignUpRequest pojo and send to user
        }
        return null;
    }
    @Override
    @Transactional
    // Create User without BVN
    public SignUpResponse createUser(SignUpRequest user) {
            //alternate course of action, validate bvn and use that to fetch fields to register user;
        // Validate bvn

            User newUser = new User(user.getEmail(),
                    passwordEncoder.encode(user.getPassword()),
                    user.getBvn(),
                    user.getFirstName(),
                    user.getMiddleName(),
                    user.getLastName(),
                    user.getAddress(),
                    user.getGender(),
                    user.getCountry(),
                    user.getTitle(),
                    user.getMobile(),
                    this.dateUtils.createDateFromDateString(user.getDob()));

                    //set other properties
            Set<String> strRoles = user.getRole();
            Set<Role> roles = new HashSet<>();
/***Expose a method to create the roles. this is just test to refactor later***/
            if(strRoles == null)
            {
                Role userRole = roleRepository.findByName(UserType.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                roles.add(userRole);
            }
            else
            {
                strRoles.forEach(role ->
                {
                    switch(role)
                    {

                        case "kiddiesave_parent":
                            Role childRole = roleRepository.findByName(UserType.KIDDIESAVE_CHILD)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                                    roles.add(childRole);
                                    break;
                        default:
                            Role parentRole = roleRepository.findByName(UserType.KIDDIESAVE_PARENT)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                    roles.add(parentRole);
                    }
                });
            }
            newUser.setRoles(roles);
            newUser.setDateCreated(new Date());
            newUser.setDateUpdated(new Date());
            newUser.setBvnValidated(false); // remember bvn validation check
            newUser.setPhoneValidated(false); // phone validation check
            newUser.setPushNotifications(false); // enable to true in edit
            newUser.setStatus(false); // set to true after user has been email validated and bvn validated(see below)
            newUser.setEmailValidated(false);

            userRepository.save(newUser);
            // add to Sign Up Response and send to user
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setEmail(user.getEmail());
        signUpResponse.setFirstName(user.getFirstName());
        signUpResponse.setMiddleName(user.getMiddleName());
        signUpResponse.setCountry(user.getCountry());
        signUpResponse.setDob(user.getDob());
        signUpResponse.setBvn(user.getBvn());
        signUpResponse.setGender(user.getGender());
        signUpResponse.setRole(user.getRole());
        signUpResponse.setAddress(user.getAddress());
        signUpResponse.setTitle(user.getTitle());
        signUpResponse.setMobile(user.getMobile());

            logger.info("User "+ user.getFirstName() + "  "+ "  "+user.getMiddleName() + "  "+user.getLastName() + " successfully added.");

            return signUpResponse;
    }

    @Override
    @Transactional
    public User editUser(UpdateUserRequest user, String loggedOnUser) throws UserNotFoundException, ApplicationException {
        User us = userRepository.getUserByEmail(loggedOnUser);
       us.setTitle(user.getTitle());
       us.setFirstName(user.getFirstName());
       us.setMiddleName(user.getMiddleName());
       us.setLastName(user.getLastName());
       us.setAddress(user.getAddress());
       us.setGender(user.getGender());
       us.setCountry(user.getCountry());
       us.setNin(user.getNin());
       us.setDateUpdated(new Date());

       User editedUser = userRepository.save(us);
        logger.info("User record updated: "+ editedUser.getFirstName() + "" +editedUser.getLastName());
        return editedUser;
    }

    @Override
    @Transactional
    public String deleteUser(String userEmail) throws UserNotFoundException, ApplicationException {
        User us = userRepository.getUserByEmail(userEmail);
      if(us != null)
      {
          us.setStatus(false);
          //consider doing a safe delete by updating user status instead.
          // delete wallet?
          User deletedUser =  userRepository.save(us);
         if(deletedUser.isStatus() == false)
        return "User deleted successfully.";
      }
        return "failed to delete user.";
    }

    @Override
    @Transactional
    public Long getUsersCount(Boolean status) {
       List<User> users = userRepository.findAllByStatus(status);
       // count the items in the list
        long noOfUsers = users.size();
       return noOfUsers;
    }
}
