package org.apiman.client.config;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServiceErrorHandler implements ResponseErrorHandler {

	private ObjectMapper objectMapper = null;
	
	
	public ServiceErrorHandler(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		
		return false;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		
	}
}
