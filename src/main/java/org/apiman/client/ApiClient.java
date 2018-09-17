package org.apiman.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public abstract class ApiClient {

	@Autowired
	@Qualifier("redhatApimanRestClient")
	private RestTemplate restTemplate;
	
	@Value(value = "${apiman.url}")
	protected String apimanUrl;

	protected static final String ORGANIZATIONS_PATH = "/organizations";
	
	protected static final String SEARCH_PATH = "/search";
}
