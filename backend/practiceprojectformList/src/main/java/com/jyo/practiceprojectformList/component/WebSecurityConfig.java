package com.jyo.practiceprojectformList.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;
private final  Jwtauthenticationfilter jwtauthenticationfilter;
    public WebSecurityConfig(UserDetailsService userDetailsService, Jwtauthenticationfilter jwtauthenticationfilter) {
        this.userDetailsService = userDetailsService;
        this.jwtauthenticationfilter = jwtauthenticationfilter;
    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//
//                .csrf(csrf->csrf.disable())
//                .cors(Customizer.withDefaults())
//                .authorizeHttpRequests(authorizeRequests ->authorizeRequests
//                        .requestMatchers("/register","/login","/api/auth/forgot-password","/api/auth/verify-otp","/api/auth/reset-password"
//                                ,"/api/user/updateprofile",  "/api/user/**",
//                                "/ws/**","/ws"
////                                ,"/api/message/public","/api/message/private"
//
//
//                        ).permitAll()
//
////
//                        .anyRequest()
//                        .authenticated())
//
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
////                .headers(headers -> headers
////                        .frameOptions(frame -> frame.sameOrigin()) // ✅ Fix for X-Frame-Options
////                )
//
//                .addFilterBefore(jwtauthenticationfilter, UsernamePasswordAuthenticationFilter.class);
//        return httpSecurity.build();
//
//
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Disable X-Frame-Options
                .headers().frameOptions().disable()

                // Disable CSRF for stateless APIs (you can enable it if necessary)
                .and().csrf().disable()

                // Enable CORS if you have custom CORS settings
                .cors().and()

                // Configure request access permissions
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(
                                        "/register",
                                        "/login",
                                        "/api/auth/forgot-password",
                                        "/api/auth/verify-otp",
                                        "/api/auth/reset-password",
//                                        "/api/user/updateprofile",
                                        "/api/user/**",
                                        "/ws/**",
                                        "/ws"
                                ).permitAll()
                                .anyRequest().authenticated()  // Secure all other endpoints
                )

                // Stateless session management (ideal for JWT-based authentication)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Add JWT authentication filter before UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtauthenticationfilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }




    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(14);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}
