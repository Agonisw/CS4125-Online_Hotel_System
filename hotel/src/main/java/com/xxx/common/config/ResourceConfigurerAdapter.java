package com.xxx.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfigurerAdapter implements WebMvcConfigurer {
    @Value("${image.path}")
    private String imagePath;

    /**
     * Configuring Resource Access
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Static resource - Image url
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + imagePath);

    }

}
