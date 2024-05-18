package com.app.construction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	public HttpHeaders getHeader() {
		HttpHeaders headers = new HttpHeaders();
		return headers;
	}

	@Autowired
    public RemoteCallService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

	public DecryptDataDto getEncryptedData(String companyName) {
		String url = String.format("%s/generate/%s", baseUrl, companyName);
		return restTemplate.getForObject(url, DecryptDataDto.class);
	}

}
