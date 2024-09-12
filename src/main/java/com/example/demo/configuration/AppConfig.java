package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

	@Bean
	public WebMvcConfigurer configFilter() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// TODO Auto-generated method stub
//				WebMvcConfigurer.super.addCorsMappings(registry);
				registry.addMapping("/**")
				.allowCredentials(true)
				.allowedOrigins("http://localhost:3000")
				.allowedMethods("*")
				.allowedHeaders("*");
			}
		};
	}

}
