package org.apiman.client.resources.organization.planpolicy;

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
public class OrganizationPlansPoliciesClientImpl extends AbstractApimanClient implements IOrganizationPlansPoliciesClient {

	private static final String ORGANIZATION_CLIENTS_POLICIES_PATH = ORGANIZATION_PLANS_PATH + "/${planId}/versions/${version}/policies";
	
	/* Use this endpoint to list all of the Policies configured for the Plan.
	 * 
	 */
	@Override
	public List<PolicySummary> listAllPlanPolicies(String organizationId, String planId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		map.put("version", version);
		url = substitute(url, map, true);
		
		PolicySummary[] planPolicies = restTemplate.getForObject(url, PolicySummary[].class);
		return planPolicies != null ? Arrays.asList(planPolicies) : null;
	}
	
	/* Use this endpoint to add a new Policy to the Plan version.
	 * 
	 */
	@Override
	public Policy addPlanPolicy(String organizationId, String planId, String version, NewPolicy planPolicy) {
		
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
	@Override
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
	@Override
	public void updatePlanPolicy(String organizationId, String planId, String version, String policyId, UpdatePolicy planPolicy) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH, "/${policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("planId", planId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map, true);
		
		restTemplate.exchange(url, PUT, new HttpEntity<UpdatePolicy>(planPolicy, getHeaders()), Void.class);
	}
	
	/* Use this endpoint to remove a Policy from the Plan.
	 * 
	 */
	@Override
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
