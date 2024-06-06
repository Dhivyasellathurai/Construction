package com.app.construction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.construction.dto.DecryptDataDto;

@Service
@Configuration
public class RemoteCallService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${base.url}")
	private String baseUrl;

	@Autowired
	private CircuitBreakerFactory breakerFactory;

	@Autowired
	public RemoteCallService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public DecryptDataDto getEncryptedData(String companyName) {

		CircuitBreaker breaker = breakerFactory.create("demoservicebreaker");
		String url = String.format("%s/generate/%s", baseUrl, companyName);
		return breaker.run(() -> restTemplate.getForObject(url, DecryptDataDto.class),
				throwable -> fallBackNameService());

	}

	private DecryptDataDto fallBackNameService() {
		DecryptDataDto dataDto = DecryptDataDto.builder().encryptedData("circuit break is working").secretKey(null)
				.build();
		return dataDto;
	}

}
