package com.kiddiesave.kiddiesave.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component //Marks this as a component. Now, Spring Boot will handle the creation and management of the
//JWTUtil Bean and you will be able to inject it in other places in your code.
public class JWTFilter extends OncePerRequestFilter {

    // injecting dependencies
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain
                                    filterChain) throws ServletException, IOException
    {
        // Extract the user information from the Authorization header
        String authHeader = request.getHeader("Authorization");
        //check if the header contains a bearer token.
        if(authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer "))
        {
            // Extract the JWT
            String jwt = authHeader.substring(7);
            if(jwt == null || jwt.isBlank())
            {
                // jwt isn't authentic
                response.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid JWT Token in Bearer Header");
            }
            //else, verify the token gotten
            else
            {
                try
                {
                    // verify the token and extract the email
                    String email = jwtUtil.validateTokenAndRetrieveSubject(jwt);
                    // Fetch User Details
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    // Create Authentication Token
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(email,userDetails.getPassword(),userDetails.getAuthorities());

                    // Setting the authentication on the Security Context using the created token
                    if(SecurityContextHolder.getContext().getAuthentication() == null)
                    {
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
                catch(JWTVerificationException exc)
                {
                    // Failed to verify JWT
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT");
                }
            }
        }
        // Continuing the execution of the filter chain
        filterChain.doFilter(request,response);
    }
}
