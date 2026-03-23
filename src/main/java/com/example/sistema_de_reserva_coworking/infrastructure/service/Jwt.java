package com.example.sistema_de_reserva_coworking.infrastructure.service;

import com.example.sistema_de_reserva_coworking.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class Jwt {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(User user){

        Map<String,Object> claims = new HashMap<>();

        claims.put("userId",user.getId());
        claims.put("role",user.getRol().name());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSingingkey(),SignatureAlgorithm.HS256)
                .compact();
    }

    // Funcion de firma del token
    public Key getSingingkey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    //Validacion de firma de token
    public Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSingingkey()) // validacion firma
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public boolean isTokenExpired(String token){
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    public String extractUserEmail (String token){
        return  extractAllClaims(token).getSubject();
    }

    public Long extractUserId (String token){
        Claims claims = extractAllClaims(token);
        // Obtener el userId del payload del token
        Object userId = claims.get("userId");

        if (userId == null) {
            // validar controllers
            throw new IllegalStateException("Invalid JWT: missing userId");
        }

        return Long.valueOf(userId.toString());
    }

    public String extractRole (String token){
        Claims claims = extractAllClaims(token);
        Object role = claims.get("role");
        return role != null ? role.toString() : null;
    }
    public boolean isTokenValid(String token,User user){

        String subject = extractUserEmail (token);
        return subject.equals(user.getEmail())
                && !isTokenExpired(token);

    }

}
