package com.app.taskmanager.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;


@Component
public class JWTUtils {

    private final String KEY = "your-very-long-secret-key-with-at-least-32-chars";
    private final long EXPIRATION_TIME = 5 * 60 * 60 ;
    SecretKey SECRET_KEY = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));



    public String extractUsername(String token){
        return getClaimsFromToken(token, Claims::getSubject);
    }

    public  Date extractExpirationDate(String token){
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    public boolean validateToken(UserDetails userDetails,String token){
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isExpired(token)) ;

    }

    private boolean isExpired(String token){
        Date expirationDate = extractExpirationDate(token);
        return new Date().after(expirationDate);
    }

    private <T> T getClaimsFromToken (String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }



    public String createToken(UserDetails userDetails){
        HashMap<String,Object> claims = new HashMap<>();
        return generateToken(userDetails.getUsername(),claims);
    }
    private String generateToken(String subject ,HashMap<String,Object> claims){

        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME *1000 ))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();

    }
}
