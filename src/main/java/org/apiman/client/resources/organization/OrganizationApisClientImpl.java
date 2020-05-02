package org.apiman.client.resources.organization;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.apis.Api;
import org.apiman.client.domain.apis.ApiVersion;
import org.apiman.client.domain.apis.ApiVersionStatus;
import org.apiman.client.domain.apis.NewApi;
import org.apiman.client.domain.apis.NewApiDefinition;
import org.apiman.client.domain.apis.NewApiVersion;
import org.apiman.client.domain.apis.UpdateApi;
import org.apiman.client.domain.apis.UpdateApiVersion;
import org.apiman.client.domain.audit.AuditEntry;
import org.apiman.client.domain.policies.PolicyChain;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.domain.summary.ApiSummary;
import org.apiman.client.domain.summary.ApiVersionEndpointSummary;
import org.apiman.client.domain.summary.ApiVersionSummary;
import org.apiman.client.domain.summary.ContractSummary;
import org.apiman.client.resources.organization.apis.IOrganizationApiMetricsClient;
import org.apiman.client.resources.organization.apis.IOrganizationApiPlansClient;
import org.apiman.client.resources.organization.apis.IOrganizationApiPoliciesClient;
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
public class OrganizationApisClientImpl extends AbstractApimanClient implements IOrganizationApisClient {

	private static final String ORGANIZATION_API_PATH = ORGANIZATIONS_PATH + "/${organizationId}/apis";
	
	@Autowired
	private IOrganizationApiMetricsClient organizationApiMetricsClient;
	@Autowired
	private IOrganizationApiPlansClient organizationApiPlansClient;
	@Autowired
	private IOrganizationApiPoliciesClient organizationApiPoliciesClient;
	
	/* Use this endpoint to get a list of all APIs in the Organization.
	 * 
	 */
	@Override
	public List<ApiSummary> listApis(String organizationId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map, false);
		
		ApiSummary[] apis = restTemplate.getForObject(url, ApiSummary[].class);
		return apis != null ? Arrays.asList(apis) : null;
	}
	
	/* Use this endpoint to create a new API. Note that it is important to also create an initial version of the API (e.g. 1.0). 
	 * This can either be done by including the 'initialVersion' property in the request, or by immediately following up with a 
	 * call to "Create API Version". If the former is done, then a first API version will be created automatically by this endpoint.
	 */
	@Override
	public Api createApi(String organizationId, NewApi createApi) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map, false);
		
		return restTemplate.postForObject(url, createApi, Api.class);
	}
	
	/* Use this endpoint to retrieve information about a single API by ID. Note that this only returns information about the API, 
	 * not about any particular *version* of the API.
	 */
	@Override
	public Api getApiById(String organizationId, String apiId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, Api.class);
	}
	
	/* Use this endpoint to update information about an API.
	 * 
	 */
	@Override
	public void updateApi(String organizationId, String apiId, UpdateApi api) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		url = substitute(url, map, false);
		
		restTemplate.exchange(url, PUT, new HttpEntity<UpdateApi>(api, getHeaders()), Void.class);
	}
	
	/* Use this endpoint to delete an API. There are multiple restrictions on this capability. Specifically, the API must not 
	 * have any published versions. If you try to delete an API with one or more published versions, it will fail with 
	 * an EntityStillActiveException error.
	 */
	@Override
	public void deleteApi(String organizationId, String apiId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		url = substitute(url, map, false);
		
		restTemplate.delete(url);
	}
	
	/* This endpoint returns audit activity information about the API.
	 * 
	 */
	@Override
	public SearchResults<AuditEntry> getApiActivity(String organizationId, String apiId, int page, int count) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", ACTIVITY_PATH, PAGE_NUMBER_AND_COUNT);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("pageNumber", String.valueOf(page != 0 ? page : DEFAULT_VALUES.PAGE_NUMBER.getValue()));
		map.put("countPerPage", String.valueOf(count != 0 ? count : DEFAULT_VALUES.COUNT_PER_PAGE.getValue()));
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, SearchResults.class);
	}
	
	/* Use this endpoint to list all of the versions of an API.
	 * 
	 */
	@Override
	public List<ApiVersionSummary> listApiVersions(String organizationId, String apiId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		url = substitute(url, map, false);
		
		ApiVersionSummary[] apiVersions = restTemplate.getForObject(url, ApiVersionSummary[].class);
		return apiVersions != null ? Arrays.asList(apiVersions) : null;
	}
	
	/* Use this endpoint to create a new version of the API.
	 * 
	 */
	@Override
	public ApiVersion createApiVersion(String organizationId, String apiId, NewApiVersion createApiVersion) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		url = substitute(url, map, false);
		
		return restTemplate.postForObject(url, createApiVersion, ApiVersion.class);
	}
	
	/* Use this endpoint to get detailed information about a single version of an API.
	 * 
	 */
	@Override
	public ApiVersion getApiVersion(String organizationId, String apiId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH, "/${version}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, ApiVersion.class);
	}
	
	/* Use this endpoint to update information about a single version of an API.
	 * 
	 */
	@Override
	public ApiVersion updateApiVersion(String organizationId, String apiId, String version, UpdateApiVersion createApiVersion) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH, "/${version}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map, false);
		
		ResponseEntity<ApiVersion> response = restTemplate.exchange(url, PUT, new HttpEntity<UpdateApiVersion>(createApiVersion, getHeaders()), ApiVersion.class);
		return response != null ? response.getBody() : null;
	}
	
	/* Use this endpoint to get audit activity information for a single version of the API.
	 * 
	 */
	@Override
	public SearchResults<AuditEntry> getApiVersionActivity(String organizationId, String apiId, String version, int page, int count) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH, "/${version}", ACTIVITY_PATH, PAGE_NUMBER_AND_COUNT);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		map.put("pageNumber", String.valueOf(page != 0 ? page : DEFAULT_VALUES.PAGE_NUMBER.getValue()));
		map.put("countPerPage", String.valueOf(count != 0 ? count : DEFAULT_VALUES.COUNT_PER_PAGE.getValue()));
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, SearchResults.class);
	}
	
	/* Use this endpoint to get a list of all Contracts created with this API. This will return Contracts created by 
	 * between any Client and through any Plan.
	 */
	@Override
	public List<ContractSummary> listApiContracts(String organizationId, String apiId, String version, int page, int count) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH, "/${version}", CONTRACTS_PATH, PAGE_NUMBER_AND_COUNT);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		map.put("pageNumber", String.valueOf(page != 0 ? page : DEFAULT_VALUES.PAGE_NUMBER.getValue()));
		map.put("countPerPage", String.valueOf(count != 0 ? count : DEFAULT_VALUES.COUNT_PER_PAGE.getValue()));
		url = substitute(url, map, false);
		
		ContractSummary[] apiContracts = restTemplate.getForObject(url, ContractSummary[].class);
		return apiContracts != null ? Arrays.asList(apiContracts) : null;
	}
	
	/* Use this endpoint to retrieve the API's definition document. An API definition document can be several different 
	 * types, depending on the API type and technology used to define the API. For example, this endpoint might return a 
	 * WSDL document, or a Swagger JSON document.
	 */
	@Override
	public String getApiDefinition(String organizationId, String apiId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH, "/${version}", DEFINITION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, String.class);
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
	@Override
	public void updateApiDefinition(String organizationId, String apiId, String version, String apiDefinition) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH, "/${version}", DEFINITION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map, false);
		
		restTemplate.exchange(url, PUT, new HttpEntity<String>(apiDefinition, getHeaders()), Void.class);
	}
	
	/* Use this endpoint to update the API's definition document by providing a URL (reference) to the definition. 
	 * This is an alternative to providing the full API definition document via a PUT to the same endpoint. This endpoint 
	 * can be used to either add a definition if one does not already exist, as well as update/replace an existing definition. 
	 * Note that apiman will not store the definition reference, but instead will download the API definition document and store it. 
	 * Additionally, the the API's "Definition Type" field will be updated.
	 */
	@Override
	public void updateApiDefinitionFromUrl(String organizationId, String apiId, String version, NewApiDefinition apiDefinition) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH, "/${version}", DEFINITION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map, false);
		
		restTemplate.postForObject(url, apiDefinition, Void.class);
	}
	
	/* Use this endpoint to delete an API's definition document. When this is done, the "definitionType" field on the API will be set to None.
	 * 
	 */
	@Override
	public void removeApiDefinition(String organizationId, String apiId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH, "/${version}", DEFINITION_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map, false);
		
		restTemplate.delete(url);
	}
	
	/* Use this endpoint to get information about the Managed API's gateway endpoint. In other words, this returns the actual 
	 * live endpoint on the API Gateway - the endpoint that a client should use when invoking the API.
	 */
	@Override
	public ApiVersionEndpointSummary getApiEndpoint(String organizationId, String apiId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH, "/${version}", ENDPOINT_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, ApiVersionEndpointSummary.class);
	}
	
	/* Use this endpoint to change the order of Policies for an API. When a Policy is added to the API, it is added as the last 
	 * Policy in the list of API Policies. Sometimes the order of Policies is important, so it is often useful to re-order the 
	 * Policies by invoking this endpoint. The body of the request should include all of the Policies for the API, in the new 
	 * desired order. Note that only the IDs of each of the Policies is actually required in the request, at a minimum.
	 */
	@Override
	public void reOrderApiPolicies(String organizationId, String apiId, String version, PolicyChain reOrderPolicies) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH, "/${version}", REORDER_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map, false);
		
		restTemplate.postForObject(url, reOrderPolicies, Void.class);
	}

	/* Use this endpoint to get detailed status information for a single version of an API. This is useful to figure out why 
	 * an API is not yet in the 'Ready' state (which is required before it can be published to a Gateway).
	 */
	@Override
	public ApiVersionStatus getApiVersionStatus(String organizationId, String apiId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_API_PATH, "/${apiId}", VERSION_PATH, "/${version}", STATUS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, ApiVersionStatus.class);
	}		
}
