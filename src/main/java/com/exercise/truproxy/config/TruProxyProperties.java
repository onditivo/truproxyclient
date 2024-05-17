package com.exercise.truproxy.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "truproxy")
@Getter
@Setter
public class TruProxyProperties {
	 private String url;
	 private String keyid;
	 private String keysecret;
}
