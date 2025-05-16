package com.jyo.practiceprojectformList.service;//package com.jyo.practiceprojectformList.service;
//
//
////
//package com.jyo.practiceprojectformList(copy).service;
//package com.jyo.practiceprojectformList.service;
import com.jyo.practiceprojectformList.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class GenerateJWTtoken {
    private  String secreteKey=null;

    public String generateToken(Users users) {
        Map<String ,Object> claims=new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(users.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decode= Decoders.BASE64.decode(getSecretKey());
        return Keys.hmacShaKeyFor(decode);
    }

    private String getSecretKey() {
        return secreteKey="RqxPOuVfHoBA8Uq40MhJvfY6qEHOOWWvg6N9W9vt23s=";
    }


    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T>T extractClaims(String token, Function<Claims,T>claimsolver) {
        Claims claims= extractClaims(token);
        return claimsolver.apply(claims);
    }
//    private Claims extractClaims(String token) {
//        return Jwts.parser().verifyWith(generateKey()).build().parseSignedClaims(token).getPayload();
//    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username=extractUserName(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}


//package com.jyo.practiceprojectformList.service;
//
//import com.jyo.practiceprojectformList.entity.Users;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//@Service
//public class GenerateJWTtoken {
//
//    private static final String SECRET_KEY = "RqxPOuVfHoBA8Uq40MhJvfY6qEHOOWWvg6N9W9vt23s="; // Must be Base64-encoded
//
//    // ✅ Generate JWT token
//    public String generateToken(Users users) {
//        Map<String, Object> claims = new HashMap<>();
//
//        return Jwts
//                .builder()
//                .setClaims(claims)
//                .setSubject(users.getName())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 1 hour
//                .signWith(getSignKey())
//                .compact();
//    }
//
//    // ✅ Get signing key
//    private SecretKey getSignKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    // ✅ Extract username
//    public String extractUserName(String token) {
//        return extractClaims(token, Claims::getSubject);
//    }
//
////     ✅ Extract all claims
//    private Claims extractClaims(String token) {
//        return Jwts
//                .parser()
//                .setSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//
//
//
//    // ✅ Extract a specific claim
//    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    // ✅ Validate token against UserDetails
//    public boolean isTokenValid(String jwt, UserDetails userDetails) {
//        final String username = extractUserName(jwt);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
//    }
//
//    // ✅ Expiration check
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaims(token, Claims::getExpiration);
//    }
//}
//
