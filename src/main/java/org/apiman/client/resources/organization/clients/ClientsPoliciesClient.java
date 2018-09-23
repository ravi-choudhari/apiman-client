package org.apiman.client.resources.organization.clients;

import static org.apiman.client.GenericUtils.buildURL;
import static org.apiman.client.GenericUtils.encode;
import static org.apiman.client.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.ClientPolicy;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientsPoliciesClient extends AbstractApimanClient {

	private static final String ORGANIZATION_CLIENTS_POLICIES_PATH = ORGANIZATION_CLIENTS_PATH + "/{clientId}/versions/{version}/policies";
	
	/* Use this endpoint to list all of the Policies configured for the Client.
	 * 
	 */
	public List<ClientPolicy> listAllClientPolicies(String organizationId, String clientId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		url = substitute(url, map);
		
		ClientPolicy[] clientPolicies = restTemplate.getForObject(encode(url), ClientPolicy[].class);
		return clientPolicies != null ? Arrays.asList(clientPolicies) : null;
	}
	
	/* Use this endpoint to add a new Policy to the Client version.
	 * 
	 */
	public ClientPolicy addClientPolicy(String organizationId, String clientId, String version, ClientPolicy clientPolicy) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		url = substitute(url, map);
		
		return restTemplate.postForObject(encode(url), clientPolicy, ClientPolicy.class);
	}
	
	/* Use this endpoint to get information about a single Policy in the Client version.
	 * 
	 */
	public ClientPolicy getClientPolicy(String organizationId, String clientId, String version, String policyId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH, "/{policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map);
		
		return restTemplate.getForObject(encode(url), ClientPolicy.class);
	}
	
	/* Use this endpoint to update the meta-data or configuration of a single Client Policy.
	 * 
	 */
	public void updateClientPolicy(String organizationId, String clientId, String version, String policyId, ClientPolicy clientPolicy) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH, "/{policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map);
		
		restTemplate.exchange(encode(url), PUT, new HttpEntity<ClientPolicy>(clientPolicy, getHeaders()), Void.class);
	}
	
	/* Use this endpoint to remove a Policy from the Client.
	 * 
	 */
	public void removeClientPolicy(String organizationId, String clientId, String version, String policyId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_POLICIES_PATH, "/{policyId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		map.put("policyId", policyId);
		url = substitute(url, map);
		
		restTemplate.delete(encode(url));
	}
}
