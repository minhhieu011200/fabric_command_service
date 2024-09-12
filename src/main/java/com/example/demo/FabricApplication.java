package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication

//@EnableJpaRepositories(basePackages = {"com.example.demo.repository"})
//@EnableRedisRepositories(basePackages = "com.example.demo.repository.redis")
@EnableJpaRepositories
public class FabricApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabricApplication.class, args);
	}

}
