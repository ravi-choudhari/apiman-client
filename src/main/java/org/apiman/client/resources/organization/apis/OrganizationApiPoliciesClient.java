package org.apiman.client.resources.organization.apis;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.policies.NewPolicy;
import org.apiman.client.domain.policies.Policy;
import org.apiman.client.domain.policies.UpdatePolicy;
import org.apiman.client.domain.summary.PolicySummary;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class OrganizationApiPoliciesClient extends AbstractApimanClient {

	private static final String ORGANIZATION_APIS_POLICIES_PATH = ORGANIZATIONS_PATH + "/${organizationId}/apis/${apiId}/versions/${version}/policies";
	
	/* Use this endpoint to list all of the Policies configured for the API.
	 * 
	 */
	public List<PolicySummary> listAllApiPolicies(String organizationId, String apiId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_APIS_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map, true);
		
		PolicySummary[] apiPolicies = restTemplate.getForObject(url, PolicySummary[].class);
		return apiPolicies != null ? Arrays.asList(apiPolicies) : null;
	}
	
	/* Use this endpoint to add a new Policy to the API version.
	 * 
	 */
	public Policy addApiPolicy(String organizationId, String apiId, String version, NewPolicy apiPolicy) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_APIS_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map, true);
		
		return restTemplate.postForObject(url, apiPolicy, Policy.class);
	}
	
	/* Use this endpoint to get information about a single Policy in the API version.
	 * 
	 */
	public Policy getApiPolicy(String organizationId, String apiId, String version, String policyId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_APIS_POLICIES_PATH, "/${policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map, true);
		
		return restTemplate.getForObject(url, Policy.class);
	}
	
	/* Use this endpoint to update the meta-data or configuration of a single API Policy.
	 * 
	 */
	public void updateApiPolicy(String organizationId, String apiId, String version, String policyId, UpdatePolicy apiPolicy) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_APIS_POLICIES_PATH, "/${policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map, true);
		
		restTemplate.exchange(url, PUT, new HttpEntity<UpdatePolicy>(apiPolicy, getHeaders()), Void.class);
	}
	
	/* Use this endpoint to remove a Policy from the API.
	 * 
	 */
	public void removeApiPolicy(String organizationId, String apiId, String version, String policyId) {

		String url = buildURL(apimanUrl, ORGANIZATION_APIS_POLICIES_PATH, "/${policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map, true);
		
		restTemplate.delete(url);
	}
}
