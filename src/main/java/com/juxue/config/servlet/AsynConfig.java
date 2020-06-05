package com.juxue.config.servlet;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
* 创建时间：2018年8月25日 上午9:24:16
* @author yhsh
* @version 1.0
* 类说明  对线程配置
*/
@Configuration
@EnableAsync(proxyTargetClass=true)
public class AsynConfig implements AsyncConfigurer{
	
	private static final Logger logger  = LoggerFactory.getLogger(AsynConfig.class);
	@Bean
	public Executor getAsyncExecutor() {
		logger.info("----多线程初始化");
		ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
		threadPool.setMaxPoolSize(20);
		threadPool.setCorePoolSize(10);
		threadPool.setQueueCapacity(200); //队列容量
		threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		threadPool.setWaitForTasksToCompleteOnShutdown(true);
		threadPool.setAwaitTerminationSeconds(60);
		threadPool.initialize();
		return threadPool;
	}

}
