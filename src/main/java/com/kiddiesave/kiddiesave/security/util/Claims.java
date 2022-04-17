package com.kiddiesave.kiddiesave.security.util;

import com.kiddiesave.kiddiesave.RequestsAndResponses.ClaimsResponse;
import com.kiddiesave.kiddiesave.entity.User;
import com.kiddiesave.kiddiesave.exceptions.UserNotFoundException;
import com.kiddiesave.kiddiesave.repository.UserRepo;
import com.kiddiesave.kiddiesave.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class Claims {
    private String jwt;
    private String username;
    private User user;
    private String bearerToken;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTUtil jwtUtil;

    private ClaimsResponse claimsResponse;

    public ClaimsResponse getClaim(HttpServletRequest request) throws UserNotFoundException {
        // return a claims object used to access api resources
        bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            jwt = bearerToken.substring(7, bearerToken.length());
            username = jwtUtil.extractUsername(jwt);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                user = userRepo.getUserByEmail(username);
                String validUser = jwtUtil.validateToken(jwt);
                if (validUser == user.getEmail()) {
                    claimsResponse = new ClaimsResponse();
                    claimsResponse.setFirstName(user.getFirstName());
                    claimsResponse.setLastName(user.getLastName());
                    claimsResponse.setAddress(user.getAddress());
                    claimsResponse.setBvn(user.getBvn());
                    claimsResponse.setDob(user.getDob());
                    claimsResponse.setRoles(user.getRoles());
                    claimsResponse.setMobile(user.getPhoneNumberLinkedWithBvn());
                    claimsResponse.setGender(user.getGender());
                    claimsResponse.setEmail(user.getEmail());
                    claimsResponse.setTitle(user.getTitle());
                    claimsResponse.setCountry(user.getCountry());
                }
            }
        }
        return claimsResponse;
    }

    // Return the username from the Security Context
    public String getLoggedOnUsername(HttpServletRequest request)
    {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer"))
        {
            jwt = bearerToken.substring(7,bearerToken.length());
                username = jwtUtil.extractUsername(jwt);
                if(username != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null)
                {
                    return username;
        }
    }
        //else return null or token cannot be found
        return "token not available. ";
    }
}