package com.jyo.practiceprojectformList.controller;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS to all endpoints under /api
                .allowedOrigins("http://localhost:5173") // React app URL
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS") // Allow these methods
                .allowedHeaders("*")

              .allowCredentials(true);


        // Allow all headers
    }
//
//@Bean
//    public CorsFilter corsFilter() {
//    CorsConfiguration config = new CorsConfiguration();
//    config.setAllowCredentials(true);
//    config.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // your frontend
//    config.setAllowedHeaders(Arrays.asList("*"));
//    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//    config.setExposedHeaders(Arrays.asList("Authorization"));
//
//    // Step 2: Register the config to a URL pattern
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//    source.registerCorsConfiguration("/**", config);
//
//    // Step 3: Return the filter
//    return new CorsFilter(source);// No error
//    }
}

