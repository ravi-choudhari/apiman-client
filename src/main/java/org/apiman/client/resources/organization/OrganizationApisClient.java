package org.apiman.client.resources.organization;

import static org.apiman.client.GenericUtils.buildURL;
import static org.apiman.client.GenericUtils.encode;
import static org.apiman.client.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.ActivityList;
import org.apiman.client.domain.Api;
import org.apiman.client.domain.ApiContract;
import org.apiman.client.domain.ApiDefinitionUrl;
import org.apiman.client.domain.ApiStatusList;
import org.apiman.client.domain.ApiVersion;
import org.apiman.client.domain.CreateApi;
import org.apiman.client.domain.CreateApiVersion;
import org.apiman.client.domain.Endpoint;
import org.apiman.client.domain.ReOrderPolicies;
import org.apiman.client.resources.organization.apis.OrganizationApiMetricsClient;
import org.apiman.client.resources.organization.apis.OrganizationApiPlansClient;
import org.apiman.client.resources.organization.apis.OrganizationApiPoliciesClient;
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
public class OrganizationApisClient extends AbstractApimanClient {

	private static final String ORGANIZATION_API_PATH = ORGANIZATIONS_PATH + "/{organizationId}/apis";
	
	@Autowired
	private OrganizationApiMetricsClient organizationApiMetricsClient;
	@Autowired
	private OrganizationApiPlansClient organizationApiPlansClient;
	@Autowired
	private OrganizationApiPoliciesClient organizationApiPoliciesClient;
	
	/* Use this endpoint to get a list of all APIs in the Organization.
	 * 
	 */
	public List<Api> listApis(String organizationId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map);
		
		Api[] apis = restTemplate.getForObject(encode(url), Api[].class);
		return apis != null ? Arrays.asList(apis) : null;
	}
	
	/* Use this endpoint to create a new API. Note that it is important to also create an initial version of the API (e.g. 1.0). 
	 * This can either be done by including the 'initialVersion' property in the request, or by immediately following up with a 
	 * call to "Create API Version". If the former is done, then a first API version will be created automatically by this endpoint.
	 */
	public Api createApi(String organizationId, CreateApi createApi) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map);
		
		return restTemplate.postForObject(encode(url), createApi, Api.class);
	}
	
	/* Use this endpoint to retrieve information about a single API by ID. Note that this only returns information about the API, 
	 * not about any particular *version* of the API.
	 */
	public Api getApiById(String organizationId, String apiId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		url = substitute(url, map);
		
		return restTemplate.getForObject(encode(url), Api.class);
	}
	
	/* Use this endpoint to update information about an API.
	 * 
	 */
	public void updateApi(String organizationId, String apiId, Api api) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		url = substitute(url, map);
		
		restTemplate.exchange(encode(url), PUT, new HttpEntity<Api>(api, getHeaders()), Void.class);
	}
	
	/* Use this endpoint to delete an API. There are multiple restrictions on this capability. Specifically, the API must not 
	 * have any published versions. If you try to delete an API with one or more published versions, it will fail with 
	 * an EntityStillActiveException error.
	 */
	public void deleteApi(String organizationId, String apiId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		url = substitute(url, map);
		
		restTemplate.delete(encode(url));
	}
	
	/* This endpoint returns audit activity information about the API.
	 * 
	 */
	public ActivityList getApiActivity(String organizationId, String apiId, int page, int count) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}", ACTIVITY_PATH, PAGE_NUMBER_AND_COUNT);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("pageNumber", String.valueOf(page));
		map.put("countPerPage", String.valueOf(count));
		url = substitute(url, map);
		
		return restTemplate.getForObject(encode(url), ActivityList.class);
	}
	
	/* Use this endpoint to list all of the versions of an API.
	 * 
	 */
	public List<ApiVersion> listApiVersions(String organizationId, String apiId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}/versions");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		url = substitute(url, map);
		
		ApiVersion[] apiVersions = restTemplate.getForObject(encode(url), ApiVersion[].class);
		return apiVersions != null ? Arrays.asList(apiVersions) : null;
	}
	
	/* Use this endpoint to create a new version of the API.
	 * 
	 */
	public CreateApiVersion createApiVersion(String organizationId, String apiId, CreateApiVersion createApiVersion) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}/versions");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		url = substitute(url, map);
		
		return restTemplate.postForObject(encode(url), createApiVersion, CreateApiVersion.class);
	}
	
	/* Use this endpoint to get detailed information about a single version of an API.
	 * 
	 */
	public CreateApiVersion getApiVersion(String organizationId, String apiId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}", VERSION_PATH, "/{version}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map);
		
		return restTemplate.getForObject(encode(url), CreateApiVersion.class);
	}
	
	/* Use this endpoint to update information about a single version of an API.
	 * 
	 */
	public CreateApiVersion updateApiVersion(String organizationId, String apiId, String version, CreateApiVersion createApiVersion) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}", VERSION_PATH, "/{version}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map);
		
		ResponseEntity<CreateApiVersion> response = restTemplate.exchange(encode(url), PUT, new HttpEntity<CreateApiVersion>(createApiVersion, getHeaders()), CreateApiVersion.class);
		return response != null ? response.getBody() : null;
	}
	
	/* Use this endpoint to get audit activity information for a single version of the API.
	 * 
	 */
	public ActivityList getApiVersionActivity(String organizationId, String apiId, String version, int page, int count) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}", VERSION_PATH, "/{version}", ACTIVITY_PATH, PAGE_NUMBER_AND_COUNT);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		map.put("pageNumber", String.valueOf(page));
		map.put("countPerPage", String.valueOf(count));
		url = substitute(url, map);
		
		return restTemplate.getForObject(encode(url), ActivityList.class);
	}
	
	/* Use this endpoint to get a list of all Contracts created with this API. This will return Contracts created by 
	 * between any Client and through any Plan.
	 */
	public List<ApiContract> listApiContracts(String organizationId, String apiId, String version, int page, int count) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}", VERSION_PATH, "/{version}", CONTRACTS_PATH, PAGE_NUMBER_AND_COUNT);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		map.put("pageNumber", String.valueOf(page));
		map.put("countPerPage", String.valueOf(count));
		url = substitute(url, map);
		
		ApiContract[] apiContracts = restTemplate.getForObject(encode(url), ApiContract[].class);
		return apiContracts != null ? Arrays.asList(apiContracts) : null;
	}
	
	/* Use this endpoint to retrieve the API's definition document. An API definition document can be several different 
	 * types, depending on the API type and technology used to define the API. For example, this endpoint might return a 
	 * WSDL document, or a Swagger JSON document.
	 */
	public String getApiDefinition(String organizationId, String apiId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}", VERSION_PATH, "/{version}", DEFINITION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map);
		
		return restTemplate.getForObject(encode(url), String.class);
	}
	
	/* Use this endpoint to update the API's definition document. An API definition will vary depending on the type of API, 
	 * and the type of definition used. For example, it might be a Swagger document or a WSDL file. To use this endpoint, 
	 * simply PUT the updated API Definition document in its entirety, making sure to set the Content-Type appropriately 
	 * for the type of definition document. The content will be stored and the API's "Definition Type" field will be updated. 
	 * Whenever an API's definition is updated, the "definitionType" property of that API is automatically set based on the 
	 * request Content-Type. There is no other way to set the API's definition type property. The following is a map 
	 * of Content-Type to API definition type.
	 * Content Type	API Definition Type
	 * application/json	SwaggerJSON
	 * application/x-yaml	SwaggerYAML
	 * application/wsdl+xml	WSDL
	 */
	public void updateApiDefinition(String organizationId, String apiId, String version, String apiDefinition) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}", VERSION_PATH, "/{version}", DEFINITION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map);
		
		restTemplate.exchange(encode(url), PUT, new HttpEntity<String>(apiDefinition, getHeaders()), CreateApiVersion.class);
	}
	
	/* Use this endpoint to update the API's definition document by providing a URL (reference) to the definition. 
	 * This is an alternative to providing the full API definition document via a PUT to the same endpoint. This endpoint 
	 * can be used to either add a definition if one does not already exist, as well as update/replace an existing definition. 
	 * Note that apiman will not store the definition reference, but instead will download the API definition document and store it. 
	 * Additionally, the the API's "Definition Type" field will be updated.
	 */
	public void updateApiDefinitionFromUrl(String organizationId, String apiId, String version, ApiDefinitionUrl apiDefinitionUrl) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}", VERSION_PATH, "/{version}", DEFINITION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map);
		
		restTemplate.postForObject(encode(url), apiDefinitionUrl, Void.class);
	}
	
	/* Use this endpoint to delete an API's definition document. When this is done, the "definitionType" field on the API will be set to None.
	 * 
	 */
	public void removeApiDefinition(String organizationId, String apiId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}", VERSION_PATH, "/{version}", DEFINITION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map);
		
		restTemplate.delete(encode(url));
	}
	
	/* Use this endpoint to get information about the Managed API's gateway endpoint. In other words, this returns the actual 
	 * live endpoint on the API Gateway - the endpoint that a client should use when invoking the API.
	 */
	public Endpoint getApiEndpoint(String organizationId, String apiId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}", VERSION_PATH, "/{version}", ENDPOINT_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map);
		
		return restTemplate.getForObject(encode(url), Endpoint.class);
	}
	
	/* Use this endpoint to change the order of Policies for an API. When a Policy is added to the API, it is added as the last 
	 * Policy in the list of API Policies. Sometimes the order of Policies is important, so it is often useful to re-order the 
	 * Policies by invoking this endpoint. The body of the request should include all of the Policies for the API, in the new 
	 * desired order. Note that only the IDs of each of the Policies is actually required in the request, at a minimum.
	 */
	public void reOrderApiPolicies(String organizationId, String apiId, String version, ReOrderPolicies reOrderPolicies) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}", VERSION_PATH, "/{version}", REORDER_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map);
		
		restTemplate.postForObject(encode(url), reOrderPolicies, Void.class);
	}

	/* Use this endpoint to get detailed status information for a single version of an API. This is useful to figure out why 
	 * an API is not yet in the 'Ready' state (which is required before it can be published to a Gateway).
	 */
	public ApiStatusList getApiVersionStatus(String organizationId, String apiId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/{apiId}", VERSION_PATH, "/{version}", STATUS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map);
		
		return restTemplate.getForObject(encode(url), ApiStatusList.class);
	}		
}
