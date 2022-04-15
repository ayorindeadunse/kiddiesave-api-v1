package com.kiddiesave.kiddiesave.security;
import com.kiddiesave.kiddiesave.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTUtil {

    // Injects the jwt-secret and jwt-issuer properties set in the resources/application.properties file
    @Value("${jwt_Secret}")
    private String SECRET_KEY;
    @Value("${jwt_issuer}")
    private String ISSUER; //host these details in GCP or AWS when it's deployed

    public String extractUsername(String token) {return extractClaim(token, Claims::getSubject);}
    public Date extractExpiration(String token) {return extractClaim(token, Claims::getExpiration);}
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {return extractExpiration(token).before(new Date());}

    public String generateToken(User user)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName",user.getFirstName());
        claims.put("lastName",user.getLastName());
        claims.put("email",user.getEmail());
        claims.put("address",user.getAddress());
        claims.put("userRole",user.getRoles());
        claims.put("mobile",user.getPhoneNumberLinkedWithBvn());
        claims.put("gender",user.getGender());
        claims.put("dob",user.getDob());

        return createToken(claims,user.getEmail());
    }

    private String createToken(Map<String, Object> claims, String subject) throws IllegalArgumentException
    {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuer(ISSUER).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact();
    }

    public String validateToken(String token)
    {
        final String username = extractUsername(token);
       // check if token has expired
        if(!isTokenExpired(token))
        {
            return "The token has expired. Please log in again to continue.";
        }
        return username;
       // return(username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
