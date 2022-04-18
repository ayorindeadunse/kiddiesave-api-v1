package com.kiddiesave.kiddiesave.services;

import com.kiddiesave.kiddiesave.RequestsAndResponses.SignUpRequest;
import com.kiddiesave.kiddiesave.entity.Role;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.entity.UserType;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import com.kiddiesave.kiddiesave.repository.RoleRepo;
import com.kiddiesave.kiddiesave.repository.UserDeviceRepo;
import com.kiddiesave.kiddiesave.repository.UserRepo;
import com.kiddiesave.kiddiesave.repository.WalletRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService implements IUserService{

    //Logger
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private RoleRepo roleRepo;

    public UserService(UserRepo userRepo,WalletRepo walletRepo,PasswordEncoder passwordEncoder,
                       RoleRepo roleRepo)
    {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }
    @Override
    @Transactional
    public User createUser(SignUpRequest user) throws UsernameNotFoundException {
            //alternate course of action, validate bvn and use that to fetch fields to register user;
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
                    user.getDob());

                    //set other properties
            Set<String> strRoles = user.getRole();
            Set<Role> roles = new HashSet<>();
/***Expose a method to create the roles. this is just test to refactor later***/
            if(strRoles == null)
            {
                Role userRole = roleRepo.findByName(UserType.ROLE_ADMIN)
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
                            Role childRole = roleRepo.findByName(UserType.KIDDIESAVE_CHILD)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                                    roles.add(childRole);
                                    break;
                        default:
                            Role parentRole = roleRepo.findByName(UserType.KIDDIESAVE_PARENT)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                    roles.add(parentRole);
                    }
                });
            }
            newUser.setRoles(roles);
            newUser.setDateCreated(new Date());
            newUser.setDateUpdated(new Date());
            newUser.setBvnValidated(true);
            newUser.setPhoneValidated(true);
            newUser.setStatus(true);
            userRepo.save(newUser);
            logger.info("User "+ user.getFirstName() + "  "+ "  "+user.getMiddleName() + "  "+user.getLastName() + " successfully added.");

            return newUser;
    }
    @Override
    @Transactional
    public User editUser(User user) throws UserNotFoundException
    {
        User us = userRepo.findById(user.getId()).orElseThrow(() -> new UserNotFoundException(user.getId()));
       us.setTitle(user.getTitle());
       us.setFirstName(user.getFirstName());
       us.setMiddleName(user.getMiddleName());
       us.setLastName(user.getLastName());
       us.setAddress(user.getAddress());
       us.setGender(user.getGender());
       us.setCountry(user.getCountry());
       us.setDob(user.getDob());
       us.setBvn(user.getBvn());
       us.setNin(user.getNin());
       us.setDateUpdated(new Date());

     // call JPA method to update use.
        User editedUser = userRepo.save(us);
        logger.info("User record updated: "+ editedUser.getFirstName() + "" +editedUser.getLastName());
        return editedUser;
    }

    @Override
    @Transactional
    public String deleteUser(User user) throws UserNotFoundException
    {
        User us = userRepo.findById(user.getId()).orElseThrow(() -> new UserNotFoundException(user.getId()));
      if(us != null)
      {
          userRepo.delete(us);
          //consider doing a safe delete by updating user status instead.
          // delete wallet?
      }
      return "user deleted successfully.";
    }

    @Override
    @Transactional
    public Long getUsersCount() {
       List<User> users = userRepo.findAll();
       // count the items in the list
        long noOfUsers = users.size();
       return noOfUsers;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users;
    }
}
