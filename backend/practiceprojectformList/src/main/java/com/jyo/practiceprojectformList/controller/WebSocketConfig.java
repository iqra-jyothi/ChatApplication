package com.jyo.practiceprojectformList.controller;

import com.jyo.practiceprojectformList.component.JwtHandshakeInterceptor;
//import org.apache.catalina.filters.CorsFilter;
import com.jyo.practiceprojectformList.component.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
//import org.springframework.web.filter.

import java.security.Principal;
import java.util.Map;
import org.springframework.web.filter.CorsFilter;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;



@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
@Autowired
    private JwtHandshakeInterceptor jwtHandshakeInterceptor;
@Autowired
private JwtUtils jwtUtils;
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")

//
                .addInterceptors(jwtHandshakeInterceptor)
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    @Override
                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
                                                      Map<String, Object> attributes) {
                        String username = (String) attributes.get("user");
                        return () -> username; // return a Principal
                    }
                })
                .setAllowedOriginPatterns("*")
                .withSockJS(); // WebSocket endpoint
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); // Prefix to send messages
        registry.enableSimpleBroker("/topic", "/queue");     // Broker destinations for public (/topic) and private (/queue)
        registry.setUserDestinationPrefix("/user");          // For private messaging
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addExposedHeader("Authorization");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE); // run early before Spring Security

        return bean;
    }
//    @Override
//    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new ChannelInterceptor() {
//            @Override
//            public Message<?> preSend(Message<?> message, MessageChannel channel) {
//                StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
//                String token = accessor.getFirstNativeHeader("Authorization");
//                if (token != null && token.startsWith("Bearer ")) {
//                    String jwt = token.substring(7);
//                    String email = jwtUtils.extractUsername(jwt); // Extract username (email) from JWT token
//                    accessor.setUser(new UsernamePasswordAuthenticationToken(email, null, List.of())); // Set the authenticated user
//                }
//                return message; // Return the modified message
//            }
//        });
//    }


}
