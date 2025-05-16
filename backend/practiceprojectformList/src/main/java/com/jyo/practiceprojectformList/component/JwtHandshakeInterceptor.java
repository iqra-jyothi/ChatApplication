package com.jyo.practiceprojectformList.component;//package com.jyo.practiceprojectformList.manualfilter;
//
//import com.jyo.practiceprojectformList.service.GenerateJWTtoken;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import java.util.Map;
//
//@Component
//public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {
//
//    @Autowired
//    private GenerateJWTtoken generateJWTtoken;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Override
//    public boolean beforeHandshake(
//            ServerHttpRequest request,
//            ServerHttpResponse response,
//            WebSocketHandler wsHandler,
//            Map<String, Object> attributes
//    ) throws Exception {
////        if (request instanceof ServletServerHttpRequest servletRequest) {
////            String authHeader = servletRequest.getServletRequest().getHeader("Authorization");
////
////            if (authHeader != null && authHeader.startsWith("Bearer ")) {
////                String jwt = authHeader.substring(7);
////                String username = generateJWTtoken.extractUserName(jwt);
////
////                if (username != null && generateJWTtoken.isTokenValid(jwt, userDetailsService.loadUserByUsername(username))) {
////                    attributes.put("username", username); // Custom key to pass to Principal
////                }
////            }
////        }
////        return true;
//        if (request instanceof ServletServerHttpRequest servletRequest) {
//            HttpServletRequest httpRequest = servletRequest.getServletRequest();
//            String token = httpRequest.getParameter("token");
//
//            if (token != null && token.startsWith("ey")) { // optionally validate format
//                attributes.put("token", token);
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
//                               WebSocketHandler wsHandler, Exception exception) {}
//}
//
//package com.jyo.practiceprojectformList.config;
//
//import com.jyo.practiceprojectformList.service.GenerateJWTtoken;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpRequest;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.server.HandshakeInterceptor;
//
//import java.util.List;
//import java.util.Map;
//@Component
//public class JwtHandshakeInterceptor implements HandshakeInterceptor {
//
//    @Autowired
//    private GenerateJWTtoken jwtTokenUtil;
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Autowired
//    private  JwtUtils jwtUtils;
//
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
//                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
//
//        if (request instanceof org.springframework.http.server.ServletServerHttpRequest servletRequest) {
//            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
//            String token = httpServletRequest.getParameter("token"); // Grab token from query param
//
//            if (token != null && token.startsWith("Bearer ")) {
//                token = token.substring(7);
//            }
//
//            if (token != null) {
//                String username = jwtTokenUtil.extractUserName(token);
//                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                    if (jwtTokenUtil.isTokenValid(token, userDetails)) {
//                        UsernamePasswordAuthenticationToken authentication =
//                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
//                        return true;
//                    }
//                }
//            }
//        }
//
//        return false; // Reject connection if token is invalid
//    }
//
//
//
//
//
//
//
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
//                               WebSocketHandler wsHandler, Exception ex) {
//        // No-op
//    }
//}


import com.jyo.practiceprojectformList.service.GenerateJWTtoken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {

    @Autowired
    private GenerateJWTtoken jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {
        System.out.println("Attempting WebSocket handshake...");

        if (request instanceof ServletServerHttpRequest servletRequest) {
            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();

            // Get token from query param
            String token = httpServletRequest.getParameter("token");

            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7); // Remove "Bearer " prefix if included
            }

            if (token != null) {
                System.out.println("Received token from query param: " + token);

                String username = jwtTokenUtil.extractUserName(token);
                System.out.println("Extracted username: " + username);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        System.out.println("the loadded is done"+userDetails);
                    if (jwtTokenUtil.isTokenValid(token, userDetails)) {
                        System.out.println("Token is valid for user: " + username);

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println("the user set is not null"
                        +username);

                        // Very important: store the username for Principal resolution
                        attributes.put("user", username);

//                        WebSocketSession session = getSessionFromContext();  // Ensure you're getting the WebSocket session correctly
//                        session.getAttributes().put("user", username);

                        return true;
                    } else {
                        System.out.println("Token is invalid for user: " + username);
                    }
                } else {
                    System.out.println("Username is null or authentication already exists.");
                }
            } else {
                System.out.println("No token found in query parameters.");
            }
        }

        return false; // Reject the connection if token is missing or invalid
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception ex) {
    }
}





