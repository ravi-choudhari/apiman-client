package org.apiman.client.config;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class BasicAuthInterceptor implements ClientHttpRequestInterceptor {

	private String basicAuthHeader;
	
	public BasicAuthInterceptor(String username, String password) {
		
		if(isNotBlank(username) && isNotBlank(password)) {
			basicAuthHeader = "Basic " + Base64.getEncoder().encodeToString(
					String.join(":", new String[] {username, password}).getBytes(StandardCharsets.UTF_8));
		}
	}
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		if(isNotBlank(basicAuthHeader)) {
			HttpHeaders headers = request.getHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, basicAuthHeader);
		}
		return execution.execute(request, body);
	}
}
