package com.FinalProject.SafePaws.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.FinalProject.SafePaws.service.impl.CustomUserDetailsService;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

@Component
public class JwtTokenProvider {

    private final String jwtSignatureSecret = "bGF1cmEtZnJpZW5kLXByb2plY3QtdGVhbS0xLWZpbmFsLXByb2plY3QtdGVhbS1pbg";

    private final long validityInMilliseconds = 3600000;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public SecretKey getSigningKey(){
        byte[] decodedKey = Base64.getDecoder().decode(jwtSignatureSecret);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }

    public String createToken(String email, List<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("email", email);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder().claims(claims)
                .setSubject(email)
                .issuedAt(now)
                .expiration(validity)
                .signWith(getSigningKey())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(jwtSignatureSecret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSignatureSecret).build().parseClaimsJws(token);
            return !claims.getPayload().getExpiration().before(new Date());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        } catch (JwtException e) {
            System.out.println("JWT Authentication Exception: " + e.getMessage());
        }
        return false;
    }
}