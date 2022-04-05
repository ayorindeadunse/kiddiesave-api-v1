package com.kiddiesave.kiddiesave.security;

import antlr.StringUtils;
import com.kiddiesave.kiddiesave.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTFilter extends OncePerRequestFilter {

   @Autowired
    private JWTUtil jwtUtil;
   @Autowired
    private UserDetailsServiceImpl userDetailsService;
   private static final Logger logger = LoggerFactory.getLogger(JWTFilter.class);

   @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
   {
       try
       {
           String jwt = parseJwt(request);
         if(jwt != null)
         {
             // validate token
            String username = jwtUtil.validateToken(jwt);
            // Use UserDetails
             UserDetails userDetails = userDetailsService.loadUserByUsername(username);
         }

       }
       catch(Exception e)
       {

       }
   }

   private String parseJwt(HttpServletRequest request)
   {
       String headerAuth = request.getHeader("Authorization");

       if (headerAuth != null && (headerAuth.startsWith("Bearer "))) //revisit this
       {
        return headerAuth.substring(7,headerAuth.length());
       }
       return null;
   }
}

