package com.jyo.practiceprojectformList.component;

//package com.jyo.practiceprojectformList.manualfilter;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtils {
//
//    @Value("${app.jwt.secret}")
//    private String secret;
//
//    private Key getSignKey() {
//        return Keys.hmacShaKeyFor(secret.getBytes());
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Claims claims = extractClaims(token);
//            Date expirationDate = claims.getExpiration();
//            return !expirationDate.before(new Date());
//        } catch (JwtException e) {
//            return false;
//        }
//    }
//
//    public String extractUsername(String token) {
//        return extractClaims(token).getSubject();
//    }
//
//    private Claims extractClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
//                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//}


//
//
//
////package com.jyo.practiceprojectformList.manualfilter;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtils {
//
//    @Value("${app.jwt.secret}")
//    private String secret;
//
//    private Key getSignKey() {
//        return Keys.hmacShaKeyFor(secret.getBytes());
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Claims claims = extractClaims(token);
//            Date expirationDate = claims.getExpiration();
//            return !expirationDate.before(new Date());
//        } catch (JwtException e) {
//            return false;
//        }
//    }
//
//    public String extractUsername(String token) {
//        return extractClaims(token).getSubject();
//    }
//
//    private Claims extractClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
//                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//}





//package com.jyo.practiceprojectformList.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    // Secret key for signing JWTs (ensure it's secure and kept private in production)
    private static final String SECRET = "mysecretkeymysecretkeymysecretkeymysecretkey"; // should be 256-bit
    private static final long EXPIRATION_TIME = 86400000; // 24 hours in milliseconds

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Generate token with username (email in your case)
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException |
                 UnsupportedJwtException | IllegalArgumentException |
                 SignatureException e) {
            System.out.println("JWT validation failed: " + e.getMessage());
            return false;
        }
    }

    // Extract email (username)
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

