package com.kiddiesave.kiddiesave.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component // Marks this as a component. Now, Spring Boot will handle the creation and management of the JWTUtil Bean
// and you will be able to inject it in other parts of the code
public class JWTUtil {

    // Injects the jwt-secret and jwt-issuer properties set in the resources/application.properties file
    @Value("${jwt_secret}")
    private String SECRET_KEY;
    @Value("${jwt_issuer}")
    private String ISSUER; //replace with a url for prod

    //method to sign and create a JWT using the injected secret in application.properties file
    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException
    {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("email",email)
                .withIssuedAt(new Date())
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    // Method to verify the JWT and then decode and extract the user email stored in the payload of the token
    public String validateTokenAndRetrieveSubject(String token)
    {
        // throw parent Exception class because JWTVerificationException doesn't seem to be working
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .withSubject("User Details")
                .withIssuer(ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }
}
