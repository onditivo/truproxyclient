package com.exercise.truproxy.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	@Autowired
	private TruProxyProperties truProxyProperties;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.setConnectTimeout(Duration.ofMillis(3000))
				.setReadTimeout(Duration.ofMillis(3000))
				.additionalInterceptors(
						(request, body, execution) -> {
							request.getHeaders().set(truProxyProperties.getKeyid(), truProxyProperties.getKeysecret());
							
							return execution.execute(request, body);
	              })
				.build();
	}
}
