package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.policydefinition.PolicyDefinition;
import org.apiman.client.domain.policydefinition.UpdatePolicyDefinition;
import org.apiman.client.domain.summary.PolicyDefinitionSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PolicyDefsClientImpl extends AbstractApimanClient implements IPolicyDefsClient {
	
	@Autowired
	@Qualifier("redhatApimanAdminRestClient")
	protected RestTemplate adminTemplate;
	
	private static final String POLICY_DEFS_PATH = "/policyDefs";
	
	/* This endpoint returns a list of all policy definitions that have been added to apiman.
	 * 
	 */
	@Override
	public List<PolicyDefinitionSummary> listPolicyDefinitions() {
		
		String url = buildURL(apimanUrl, POLICY_DEFS_PATH);
		PolicyDefinitionSummary[] policyDefinitions = restTemplate.getForObject(url, PolicyDefinitionSummary[].class);
		
		return policyDefinitions != null ? Arrays.asList(policyDefinitions) : null;
	}
	
	/* Use this endpoint to add a policy definition to apiman. The policy definition can optionall include the 'id' property. 
	 * If no 'id' is supplied, one will be generated based on the name.
	 */
	@Override
	public PolicyDefinition addPolicyDefinition(PolicyDefinition policyDefinition) {
		
		String url = buildURL(apimanUrl, POLICY_DEFS_PATH);
		return adminTemplate.postForObject(url, policyDefinition, PolicyDefinition.class);
	}
	
	/* Use this endpoint to get a single policy definition by its ID.
	 * 
	 */
	@Override
	public PolicyDefinition getPolicyDefinitionById(String policyDefinitionId) {
		
		String url = buildURL(apimanUrl, POLICY_DEFS_PATH, "/${policyDefinitionId}");
		Map<String, String> map = new HashMap<>();
		map.put("policyDefinitionId", policyDefinitionId);
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, PolicyDefinition.class);
	}

	/* Update the meta information about a policy definition.
	 * 
	 */
	@Override
	public void updatePolicyDefinition(String policyDefinitionId, UpdatePolicyDefinition policyDefinition) {
		
		String url = buildURL(apimanUrl, POLICY_DEFS_PATH, "/${policyDefinitionId}");
		Map<String, String> map = new HashMap<>();
		map.put("policyDefinitionId", policyDefinitionId);
		url = substitute(url, map, false);
		
		adminTemplate.exchange(url, PUT, new HttpEntity<UpdatePolicyDefinition>(policyDefinition, getHeaders()), Void.class);
	}

	/* Use this endpoint to delete a policy definition by its ID. If the policy definition was added automatically from 
	 * an installed plugin, this will fail. The only way to remove such policy definitions is to remove the plugin.
	 */
	@Override
	public void deletePolicyDefinition(String policyDefinitionId) {
		
		String url = buildURL(apimanUrl, POLICY_DEFS_PATH, "/${policyDefinitionId}");
		Map<String, String> map = new HashMap<>();
		map.put("policyDefinitionId", policyDefinitionId);
		url = substitute(url, map, false);
		
		adminTemplate.delete(url);
	}

}
