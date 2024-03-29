package com.kiddiesave.kiddiesave.security;

import com.kiddiesave.kiddiesave.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class JWTFilter extends OncePerRequestFilter {

    private JWTUtil jwtUtil;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public JWTFilter(JWTUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    private static final Logger logger = LoggerFactory.getLogger(JWTFilter.class);

   @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
           throws ServletException, IOException
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
             UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                     userDetails,
                     null,
                     userDetails.getAuthorities()
             );
             authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(authentication);
         }

       }
       catch(Exception e)
       {
            //log error
           logger.error("Cannot set user authentication: {}",e);
       }
       filterChain.doFilter(request,response);
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

