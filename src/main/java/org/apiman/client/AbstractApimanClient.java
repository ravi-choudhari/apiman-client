package org.apiman.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import lombok.AccessLevel;
import lombok.Getter;

public abstract class AbstractApimanClient {

	@Autowired
	@Qualifier("redhatApimanRestClient")
	protected RestTemplate restTemplate;
	
	@Value(value = "${apiman.url:http://localhost:8080/apiman}")
	protected String apimanUrl;

	@Getter(value = AccessLevel.PUBLIC)
	protected static enum DEFAULT_VALUES {
		
		PAGE_NUMBER(1),
		COUNT_PER_PAGE(20);
		
		private int value;
		
		DEFAULT_VALUES(int value) {
			this.value = value;
		}		
	};
	
	protected static final String ORGANIZATIONS_PATH = "/organizations";
	protected static final String SEARCH_PATH = "/search";
	protected static final String ACTIVITY_PATH = "/activity";
	protected static final String VERSION_PATH = "/versions";
	protected static final String CONTRACTS_PATH = "/contracts";
	protected static final String DEFINITION_PATH = "/definition";
	protected static final String ENDPOINT_PATH = "/endpoint";
	protected static final String REORDER_POLICIES_PATH = "/reorderPolicies";
	protected static final String STATUS_PATH = "/status";
	protected static final String POLICY_CHAIN_PATH = "/policyChain";
	protected static final String CLIENTS_PATH = "/clients";
	protected static final String APIS_PATH = "/apis";
	protected static final String PLUGINS_PATH = "/plugins";
	
	protected static final String DOWNLOAD = "?download=${download}";
	
	protected static final String ORGANIZATION_PLANS_PATH = ORGANIZATIONS_PATH + "/${organizationId}/plans";
	protected static final String ORGANIZATION_CLIENTS_PATH = ORGANIZATIONS_PATH + "/${organizationId}/clients";
	protected static final String ORGANIZATION_APIS_PATH = ORGANIZATIONS_PATH + "/${organizationId}/apis";
	
	protected static final String PAGE_NUMBER_AND_COUNT = "?page=${pageNumber}&count=${countPerPage}";
	protected static final String FROM_AND_TO_DATES = "?from=${fromDate}&to=${toDate}";
	protected static final String INTERVAL = "&interval=${interval}";
	
	protected HttpHeaders getHeaders() {
		
		HttpHeaders headers = new HttpHeaders();
		return headers;
	}
}
