package com.xxx.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cross-domain Configuration
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {


    //springboot Solve cross-domain problems
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
    //          .allowCredentials(true)
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(false)
                .maxAge(3600);
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
       //  You need cross-domain addresses  注意这里的 127.0.0.1 != localhost
       // corsConfiguration.addAllowedOrigin("http://localhost:3030");
        // * Indicates that all addresses can be accessed
        corsConfiguration.addAllowedOrigin("*");
        //  Cross-domain request headers
        corsConfiguration.addAllowedHeader("*");
        //  Cross-domain request methods
        corsConfiguration.addAllowedMethod("*");

        corsConfiguration.addExposedHeader("Authorization");

        return corsConfiguration;
    }
    @Bean
    public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //Configure the addresses that can be accessed
    source.registerCorsConfiguration("/**", buildConfig());
    return new CorsFilter(source);
    }
}
