package org.apiman.client.resources.organization.apis;

import static org.apiman.client.GenericUtils.buildURL;
import static org.apiman.client.GenericUtils.substitute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.ApiPolicyList;
import org.apiman.client.domain.Plan;
import org.springframework.stereotype.Component;

@Component
public class OrganizationApiPlansClient extends AbstractApimanClient {

	private static final String ORGANIZATION_APIS_PLANS_PATH = ORGANIZATIONS_PATH + "/${organizationId}/apis/${apiId}/versions/${version}/plans";
	
	/* Use this endpoint to list the Plans configured for the given API version.
	 * 
	 */
	public List<Plan> listApiPlans(String organizationId, String apiId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_APIS_PLANS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		url = substitute(url, map);
		
		Plan[] plans = restTemplate.getForObject(url, Plan[].class);
		return plans != null ? Arrays.asList(plans) : null;
	}
	
	/* Use this endpoint to get a Policy Chain for the specific API version. A Policy Chain is a useful summary to better 
	 * understand which Policies would be executed for a request to this API through a particular Plan offered by the API. 
	 * Often this information is interesting prior to create a Contract with the API.
	 */
	public ApiPolicyList getApiPolicyChain(String organizationId, String apiId, String version, String planId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_APIS_PLANS_PATH + "/${planId}", POLICY_CHAIN_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("apiId", apiId);
		map.put("version", version);
		map.put("planId", planId);
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, ApiPolicyList.class);
	}
}
