package com.kiddiesave.kiddiesave.security;

import com.kiddiesave.kiddiesave.repository.UserRepo;
import com.kiddiesave.kiddiesave.security.services.UserDetailsServiceImpl;
import com.kiddiesave.kiddiesave.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Inject dependencies
    @Autowired private JWTFilter filter;
    @Autowired private UserDetailsServiceImpl uds;

    private static final String[] AUTH_WHITELIST = {
            "/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/h2-console/**",
            "/webjars/**"
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception // Method to configure your app security settings
    {

        http.csrf().disable().headers().frameOptions().deny().and() //disabling csrf
                .cors() //enabling cors
                .and()
                .authorizeHttpRequests() //authorize incoming http requests
                .antMatchers("/api/auth/**").permitAll() // allows auth requests to be made without authentication
              //  .antMatchers("/api/user/**").hasRole("USER") //allows only users with the "USER" role to make requests to the user routes
                .antMatchers("/api/user/**").permitAll()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .userDetailsService(uds) //sets the user details service to the custom implementation
                .exceptionHandling()
                .authenticationEntryPoint(
                        //rejecting request as unauthorized when entry point is reached
                        // if this point is reached it means that the current request requires authentication
                        // and no JWT was found attached to the authorization header of the current request.
                        (request,response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized")

                )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); //setting session to be stateless because
        // you know, this is a REST API

        //Adding the JWT filter
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);


    }

//create a bean here for the password encoder to encrypt the password before storing in the database
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Exposing the bean of the authentication manager which will be used to run the authentication process
@Bean
@Override
public AuthenticationManager authenticationManager() throws Exception
{
    return super.authenticationManagerBean();
}
}

