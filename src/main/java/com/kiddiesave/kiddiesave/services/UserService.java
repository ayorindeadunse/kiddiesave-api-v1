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
    private WalletRepo walletRepo;
    private PasswordEncoder passwordEncoder;
    private UserDeviceRepo userDeviceRepo;
    private RoleRepo roleRepo;

    public UserService(UserRepo userRepo,WalletRepo walletRepo,PasswordEncoder passwordEncoder,UserDeviceRepo userDeviceRepo,
                       RoleRepo roleRepo)
    {
        this.userRepo = userRepo;
        this.walletRepo = walletRepo;
        this.passwordEncoder = passwordEncoder;
        this.userDeviceRepo = userDeviceRepo;
        this.roleRepo = roleRepo;
    }
    @Override
    public User createUser(SignUpRequest user) throws UsernameNotFoundException {
        //return null;
            // check if user exists in database
            if(userRepo.existsByUsername(user.getEmail()))
            {
                // user already exists, send as response to client
                //
                //throw user exists exception (add that to exception class)
            }
            //alternate course of action, validate bvn and use that to fetch fields to register user;
            User newUser = new User(user.getEmail(),
                    passwordEncoder.encode(user.getPassword()),
                    user.getAddress(),
                    user.getFirstName(),
                    user.getMiddleName(),
                    user.getLastName(),
                    user.getGender(),
                    user.getBvn(),
                    user.getTitle(),
                    user.getMobile(),
                    user.getDob(),
                    user.getCountry());
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
    public User editUser(Long id, User user) throws UserNotFoundException {
       User us = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
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
    public String deleteUser(Long id) throws UserNotFoundException {
      User us = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
      if(us != null)
      {
          userRepo.delete(us);
          // delete wallet?
      }
      return "user deleted successfully.";
    }

    @Override
    public Long getUsersCount() {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    //
}
