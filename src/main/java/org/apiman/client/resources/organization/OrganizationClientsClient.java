package org.apiman.client.resources.organization;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.ActivityList;
import org.apiman.client.domain.ApiKey;
import org.apiman.client.domain.ApiMetrics;
import org.apiman.client.domain.ApiMetrics.METRICS_TYPE;
import org.apiman.client.domain.Client;
import org.apiman.client.domain.ClientVersion;
import org.apiman.client.domain.ReOrderPolicies;
import org.apiman.client.resources.organization.clients.ClientsApiRegistryClient;
import org.apiman.client.resources.organization.clients.ClientsContractsClient;
import org.apiman.client.resources.organization.clients.ClientsPoliciesClient;
import org.apiman.client.util.GenericUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
@Component
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PRIVATE)
public class OrganizationClientsClient extends AbstractApimanClient {
	
	private static final String ORGANIZATION_CLIENTS_VERION_PATH = ORGANIZATION_CLIENTS_PATH + "/${clientId}/versions";
	private static final String API_KEY_PATH = "/apikey";
	
	@Autowired
	private ClientsApiRegistryClient clientsApiRegistryClient;
	@Autowired
	private ClientsContractsClient clientsContractsClient;
	@Autowired
	private ClientsPoliciesClient clientsPoliciesClient;
	
	/* Use this endpoint to get a list of all Clients in the Organization.
	 * 
	 */
	public List<Client> listClients(String organizationId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map);
		
		Client[] organizationClients = restTemplate.getForObject(url, Client[].class);
		return organizationClients != null ? Arrays.asList(organizationClients) : null;
	}
	
	/* Use this endpoint to create a new Client. Note that it is important to also create an initial version of the Client (e.g. 1.0). 
	 * This can either be done by including the 'initialVersion' property in the request, or by immediately following up with a call 
	 * to "Create Client Version". If the former is done, then a first Client version will be created automatically by this endpoint.
	 */
	public Client createClient(String organizationId, Client client) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map);
		
		return restTemplate.postForObject(url, client, Client.class);
	}
	
	/* Use this endpoint to retrieve information about a single Client by ID. Note that this only returns information about the Client, 
	 * not about any particular *version* of the Client.
	 */
	public Client getClientById(String organizationId, String clientId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_PATH, "/${clientId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, Client.class);
	}
	
	/* Use this endpoint to update information about an Client.
	 * 
	 */
	public void updateClient(String organizationId, String clientId, Client client) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_PATH, "/${clientId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		url = substitute(url, map);
		
		restTemplate.exchange(url, PUT, new HttpEntity<Client>(client, getHeaders()), Void.class);
	}
	
	/* Delete a ClientApp
	 * 
	 */
	public void deleteClient(String organizationId, String clientId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_PATH, "/${clientId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		url = substitute(url, map);
		
		restTemplate.delete(url);
	}
	
	/* This endpoint returns audit activity information about the Client.
	 * 
	 */
	public ActivityList getClientActivity(String organizationId, String clientId, int page, int count) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_PATH, "/${clientId}", ACTIVITY_PATH, PAGE_NUMBER_AND_COUNT);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("pageNumber", String.valueOf(page != 0 ? page : DEFAULT_VALUES.PAGE_NUMBER.getValue()));
		map.put("countPerPage", String.valueOf(count != 0 ? count : DEFAULT_VALUES.COUNT_PER_PAGE.getValue()));
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, ActivityList.class);
	}
	
	/* Use this endpoint to list all of the versions of an Client.
	 * 
	 */
	public List<ClientVersion> listClientVersions(String organizationId, String clientId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_VERION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		url = substitute(url, map);
		
		ClientVersion[] clientVersions = restTemplate.getForObject(url, ClientVersion[].class);
		return clientVersions != null ? Arrays.asList(clientVersions) : null;
	}
	
	/* Use this endpoint to create a new version of the Client.
	 * 
	 */
	public ClientVersion createClientVersion(String organizationId, String clientId, ClientVersion clientVersion) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_VERION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		url = substitute(url, map);
		
		return restTemplate.postForObject(url, clientVersion, ClientVersion.class);
	}
	
	/* Use this endpoint to get detailed information about a single version of an Client.
	 * 
	 */
	public ClientVersion getClientVersion(String organizationId, String clientId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_VERION_PATH, "/${version}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, ClientVersion.class);
	}
	
	/* Use this endpoint to get audit activity information for a single version of the Client.
	 * 
	 */
	public ActivityList getClientVersionActivity(String organizationId, String clientId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_VERION_PATH, "/${version}", ACTIVITY_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, ActivityList.class);
	}
	
	/* Use this endpoint to get the client's current API Key. This call will fail if you do not have the proper permission to see the information.
	 * 
	 */
	public ApiKey getApiKey(String organizationId, String clientId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_VERION_PATH, "/${version}", API_KEY_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, ApiKey.class);
	}
	
	/* Use this endpoint to update the API Key for the given client. You can either provide your own custom (must be unique) API Key, 
	 * or you can send an empty request and apiman will generate a new API key for you. Note that if the client is already registered 
	 * with one or more Gateways, this call will fail (the API Key can only be modified if the client is not currently registered).
	 */
	public ApiKey updateApiKey(String organizationId, String clientId, String version, ApiKey apiKey) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_VERION_PATH, "/${version}", API_KEY_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		url = substitute(url, map);
		
		ResponseEntity<ApiKey> response = restTemplate.exchange(url, PUT, new HttpEntity<ApiKey>(apiKey, getHeaders()), ApiKey.class);
		return response != null ? response.getBody() : null;
	}
	
	/* Retrieves metrics/analytics information for a specific client. This will return request count data broken down by API. 
	 * It basically answers the question "which APIs is my client really using?".
	 */
	public ApiMetrics getClientUsageMetricsPerApi(String organizationId, String clientId, String version, Date fromDate, Date toDate) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_VERION_PATH, "/${metricsType}", FROM_AND_TO_DATES);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", clientId);
		map.put("version", version);
		map.put("metricsType", METRICS_TYPE.API_USAGE.getName());
		map.put("fromDate", GenericUtils.formatDate(fromDate != null ? fromDate : new Date()));
		map.put("toDate", GenericUtils.formatDate(toDate != null ? toDate : new Date()));
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, ApiMetrics.class);
	}
	
	/* Use this endpoint to change the order of Policies for an Client. When a Policy is added to the Client, it is added as the 
	 * last Policy in the list of Client Policies. Sometimes the order of Policies is important, so it is often useful to re-order 
	 * the Policies by invoking this endpoint. The body of the request should include all of the Policies for the Client, in the 
	 * new desired order. Note that only the IDs of each of the Policies is actually required in the request, at a minimum.
	 */
	public void reorderClientPolicies(String organizationId, String clientId, String version, ReOrderPolicies reOrderPolicies) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_VERION_PATH, "/${version}", REORDER_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		url = substitute(url, map);
		
		restTemplate.postForObject(url, reOrderPolicies, Void.class);
	}
}
