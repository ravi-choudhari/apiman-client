package org.apiman.client.resources.organization.planpolicy;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.Policy;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class OrganizationPlansPoliciesClient extends AbstractApimanClient {

	private static final String ORGANIZATION_CLIENTS_POLICIES_PATH = ORGANIZATION_PLANS_PATH + "/${planId}/versions/${version}/policies";
	
	/* Use this endpoint to list all of the Policies configured for the Plan.
	 * 
	 */
	public List<Policy> listAllPlanPolicies(String organizationId, String planId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		map.put("version", version);
		url = substitute(url, map, true);
		
		Policy[] planPolicies = restTemplate.getForObject(url, Policy[].class);
		return planPolicies != null ? Arrays.asList(planPolicies) : null;
	}
	
	/* Use this endpoint to add a new Policy to the Plan version.
	 * 
	 */
	public Policy addPlanPolicy(String organizationId, String planId, String version, Policy planPolicy) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		map.put("version", version);
		url = substitute(url, map, true);
		
		return restTemplate.postForObject(url, planPolicy, Policy.class);
	}
	
	/* Use this endpoint to get information about a single Policy in the Plan version.
	 * 
	 */
	public Policy getPlanPolicy(String organizationId, String planId, String version, String policyId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH, "/${policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map, true);
		
		return restTemplate.getForObject(url, Policy.class);
	}
	
	/* Use this endpoint to update the meta-data or configuration of a single Plan Policy.
	 * 
	 */
	public void updatePlanPolicy(String organizationId, String planId, String version, String policyId, Policy planPolicy) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH, "/${policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map, true);
		
		restTemplate.exchange(url, PUT, new HttpEntity<Policy>(planPolicy, getHeaders()), Void.class);
	}
	
	/* Use this endpoint to remove a Policy from the Plan.
	 * 
	 */
	public void removePlanPolicy(String organizationId, String planId, String version, String policyId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH, "/${policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map, true);
		
		restTemplate.delete(url);
	}
}
