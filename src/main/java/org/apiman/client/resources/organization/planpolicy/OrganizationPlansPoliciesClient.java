package org.apiman.client.resources.organization.planpolicy;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class OrganizationPlansPoliciesClient extends ApiClient {

	private static final String ORGANIZATION_CLIENTS_POLICIES_PATH = ORGANIZATIONS_PATH + "/{organizationId}/plans/{planId}/versions/{version}/policies";
	
	/* Use this endpoint to list all of the Policies configured for the Plan.
	 * 
	 */
	public String listAllPlanPolicies() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to add a new Policy to the Plan version.
	 * 
	 */
	public String addPlanPolicy() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get information about a single Policy in the Plan version.
	 * 
	 */
	public String getPlanPolicy() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to update the meta-data or configuration of a single Plan Policy.
	 * 
	 */
	public String updatePlanPolicy() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to remove a Policy from the Plan.
	 * 
	 */
	public String removePlanPolicy() {
		
		return apimanUrl;
	}
}
