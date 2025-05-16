package com.jyo.practiceprojectformList.component;//package com.jyo.practiceprojectformList.manualfilter;
//
//import com.jyo.practiceprojectformList.service.GenerateJWTtoken;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class Jwtauthenticationfilter extends OncePerRequestFilter {
//    private final GenerateJWTtoken generateJWTtoken;
//private  final UserDetailsService userDetailsService;
//    public Jwtauthenticationfilter(GenerateJWTtoken generateJWTtoken, UserDetailsService userDetailsService) {
//        this.generateJWTtoken = generateJWTtoken;
//        this.userDetailsService = userDetailsService;
//    }
//
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer")) {
//            System.out.println("Authorization header is missing or does not start with 'Bearer'.");
//            filterChain.doFilter(request, response);  // Continue filter chain if no token
//            return;
//        }
//
//        final String jwt = authHeader.substring(7);
//        final String username = generateJWTtoken.extractUserName(jwt);  // Extract username from token
//
//        if (username != null) {
//            System.out.println("JWT Token is valid. Extracted username: " + username);
//        } else {
//            System.out.println("JWT Token extraction failed. Username is null.");
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Return 403 if the token extraction fails
//            return;
//        }
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (username != null && authentication == null) {
//            // Username is not null and no authentication exists yet
//            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//            // Check if the token is valid
//            if (generateJWTtoken.isTokenValid(jwt, userDetails)) {
//                System.out.println("JWT is valid for user: " + username);
//                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                // Set the authentication in the security context
//                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//            } else {
//                System.out.println("JWT token is invalid for user: " + username);
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);  // Return 403 if the token is invalid
//                return;
//            }
//        }
//
//        filterChain.doFilter(request, response);  // Continue filter chain
//    }
//}

//
//import com.jyo.practiceprojectformList.service.GenerateJWTtoken;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class Jwtauthenticationfilter extends OncePerRequestFilter {
//
//    private final GenerateJWTtoken generateJWTtoken;
//    private final UserDetailsService userDetailsService;
//
//    public Jwtauthenticationfilter(GenerateJWTtoken generateJWTtoken, UserDetailsService userDetailsService) {
//        this.generateJWTtoken = generateJWTtoken;
//        this.userDetailsService = userDetailsService;
//    }
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer")) {
//            System.out.println("Authorization header is missing or does not start with 'Bearer'.");
//            filterChain.doFilter(request, response);  // Continue filter chain if no token
//            return;
//        }
//
//        final String jwt = authHeader.substring(7);  // Extract JWT (remove "Bearer " part)
//        final String username = generateJWTtoken.extractUserName(jwt);  // Extract username from token
//        System.out.println(username+" the user name is ");
//        if (username != null) {
//            System.out.println("JWT Token is valid. Extracted username: " + username);
//        } else {
//            System.out.println("JWT Token extraction failed. Username is null.");
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Return 403 if extraction fails
//            return;
//        }
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (username != null && authentication == null) {
//            try {
//                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//                // Check if the token is valid
//                if (generateJWTtoken.isTokenValid(jwt, userDetails)) {
//                    System.out.println("JWT is valid for user: " + username);
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails, null, userDetails.getAuthorities());
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                    // Set authentication context
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                } else {
//                    System.out.println("JWT token is invalid for user: " + username);
//                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);  // Return 403 if invalid
//                    return;
//                }
//            } catch (Exception e) {
//                System.out.println("Error while processing JWT: " + e.getMessage());
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN); // Return 403 if error occurs
//                return;
//            }
//        }
//
//        filterChain.doFilter(request, response);  // Continue filter chain
//    }
//
//
//}




//package com.jyo.practiceprojectformList.manualfilter;

import com.jyo.practiceprojectformList.service.GenerateJWTtoken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class Jwtauthenticationfilter extends OncePerRequestFilter {
    private final GenerateJWTtoken generateJWTtoken;
    private final UserDetailsService userDetailsService;

    public Jwtauthenticationfilter(GenerateJWTtoken generateJWTtoken, UserDetailsService userDetailsService) {
        this.generateJWTtoken = generateJWTtoken;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = authHeader.substring(7);
            String username = generateJWTtoken.extractUserName(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (generateJWTtoken.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }
}

