package org.apiman.client.resources.organization.clients;

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
public class ClientsPoliciesClientImpl extends AbstractApimanClient implements IClientsPoliciesClient{

	private static final String ORGANIZATION_CLIENTS_POLICIES_PATH = ORGANIZATION_CLIENTS_PATH + "/${clientId}/versions/${version}/policies";
	
	/* Use this endpoint to list all of the Policies configured for the Client.
	 * 
	 */
	@Override
	public List<PolicySummary> listAllClientPolicies(String organizationId, String clientId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		url = substitute(url, map, true);
		
		PolicySummary[] clientPolicies = restTemplate.getForObject(url, PolicySummary[].class);
		return clientPolicies != null ? Arrays.asList(clientPolicies) : null;
	}
	
	/* Use this endpoint to add a new Policy to the Client version.
	 * 
	 */
	@Override
	public Policy addClientPolicy(String organizationId, String clientId, String version, NewPolicy clientPolicy) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		url = substitute(url, map, true);
		
		return restTemplate.postForObject(url, clientPolicy, Policy.class);
	}
	
	/* Use this endpoint to get information about a single Policy in the Client version.
	 * 
	 */
	@Override
	public Policy getClientPolicy(String organizationId, String clientId, String version, String policyId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH, "/${policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map, true);
		
		return restTemplate.getForObject(url, Policy.class);
	}
	
	/* Use this endpoint to update the meta-data or configuration of a single Client Policy.
	 * 
	 */
	@Override
	public void updateClientPolicy(String organizationId, String clientId, String version, String policyId, UpdatePolicy clientPolicy) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH, "/${policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map, true);
		
		restTemplate.exchange(url, PUT, new HttpEntity<UpdatePolicy>(clientPolicy, getHeaders()), Void.class);
	}
	
	/* Use this endpoint to remove a Policy from the Client.
	 * 
	 */
	@Override
	public void removeClientPolicy(String organizationId, String clientId, String version, String policyId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH, "/${policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map, true);
		
		restTemplate.delete(url);
	}
}
