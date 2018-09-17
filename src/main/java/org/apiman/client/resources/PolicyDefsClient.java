package org.apiman.client.resources;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class PolicyDefsClient extends ApiClient {
	
	private static final String POLICY_DEFS_PATH = "/policyDefs";
	
	/* This endpoint returns a list of all policy definitions that have been added to apiman.
	 * 
	 */
	public String listPolicyDefinitions() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to add a policy definition to apiman. The policy definition can optionall include the 'id' property. 
	 * If no 'id' is supplied, one will be generated based on the name.
	 */
	public String addPolicyDefinition() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get a single policy definition by its ID.
	 * 
	 */
	public String getPolicyDefinitionById() {
		
		return apimanUrl;
	}

	/* Update the meta information about a policy definition.
	 * 
	 */
	public String updatePolicyDefinition() {
		
		return apimanUrl;
	}

	/* Use this endpoint to delete a policy definition by its ID. If the policy definition was added automatically from 
	 * an installed plugin, this will fail. The only way to remove such policy definitions is to remove the plugin.
	 */
	public String deletePolicyDefinition() {
		
		return apimanUrl;
	}

}
