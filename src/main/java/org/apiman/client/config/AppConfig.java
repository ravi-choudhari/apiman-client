package org.apiman.client.config;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan("org.apiman.client")
public class AppConfig {
	
	@Value("${apiman.admin.username:admin}")
	private String username;
	@Value("${apiman.admin.password:admin123!}")
	private String password;
	
	@Bean(name = "redhatApimanRestClient")
	RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().clear();
		restTemplate.getMessageConverters().add(jacksonConverter());
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
		restTemplate.setErrorHandler(serviceErrorHandler());
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new JsonMimeInterceptor());
		interceptors.add(new AdminBasicAuthInterceptor(username, password));
		restTemplate.setInterceptors(interceptors);
		ClientHttpRequestFactory requestFactory = restTemplate.getRequestFactory();
		if(requestFactory instanceof SimpleClientHttpRequestFactory) {
			((SimpleClientHttpRequestFactory) requestFactory).setConnectTimeout(30000);
			((SimpleClientHttpRequestFactory) requestFactory).setReadTimeout(60000);
		}
		return restTemplate;
	}
	
	private MappingJackson2HttpMessageConverter jacksonConverter() {
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper());
		return converter;
	}
	
	private ObjectMapper objectMapper() {
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
		return objectMapper;
	}
	
	private ServiceErrorHandler serviceErrorHandler() {
		
		return new ServiceErrorHandler(objectMapper());
	}
}
