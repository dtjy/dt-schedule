package com.dtjy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableJms
@EnableScheduling
@Slf4j
public class ScheduleApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ScheduleApplication.class);
		app.addListeners(new ApplicationPidFileWriter());
		app.run(args);
		log.info("启动成功");
	}
	
	@Bean
	public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
		ThreadPoolTaskScheduler threadPool = new ThreadPoolTaskScheduler();
		threadPool.setPoolSize(5);
		threadPool.initialize();
		return threadPool;
	}
}
