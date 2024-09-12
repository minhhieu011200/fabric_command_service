package com.example.demo.configuration;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

	@Bean("asyncTask")
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(4);
		taskExecutor.setQueueCapacity(200);
		taskExecutor.setMaxPoolSize(4);
		taskExecutor.setThreadNamePrefix("AsyncTask-");
		taskExecutor.initialize();
		return taskExecutor;
	}
}
