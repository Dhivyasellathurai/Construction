package com.app.construction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class RemoteCallService {

	@Autowired
	RestTemplate restTemplate;

	public HttpHeaders getHeader() {
		HttpHeaders headers = new HttpHeaders();
		return headers;
	}

}
