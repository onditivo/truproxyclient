package com.exercise.truproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.exercise.truproxy.config.TruProxyProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = TruProxyProperties.class)
public class TruproxyclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruproxyclientApplication.class, args);
	}

}
