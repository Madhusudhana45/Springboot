package com.example.emp.Security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.emp.Exception.ResourceNotFoundException;  // Assuming this exception exists in your project

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Component
public class JwtTokenProvider {

    // Secure key generated for HS512 algorithm
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication) {
        String email = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + 3600000);  // 1 hour expiration in milliseconds

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(secretKey)  // Use secure key
                .compact();

        return token;
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)  // Use secure key for parsing
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()  // Use parserBuilder instead of deprecated parser()
                .setSigningKey(secretKey)  // Use the correct key object
                .build()
                .parseClaimsJws(token);  // Parse the JWT
            return true;
        } catch (Exception e) {
            // You can throw a more specific exception, like JwtTokenException, or keep ResourceNotFoundException if that's what you intend.
            throw new ResourceNotFoundException("Token validation failed: " + e.getMessage());
        }
    }
}
