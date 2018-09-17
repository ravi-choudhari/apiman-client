package org.apiman.client.resources.organization;

import org.apiman.client.ApiClient;
import org.apiman.client.resources.organization.planpolicy.OrganizationPlansPoliciesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationPlansClient extends ApiClient {

	private static final String ORGANIZATION_PLANS_PATH = ORGANIZATIONS_PATH + "/{organizationId}/plans";
	
	@Autowired
	private OrganizationPlansPoliciesClient plansPoliciesClient;
		
	public OrganizationPlansPoliciesClient getPlansPoliciesClient() {
		return plansPoliciesClient;
	}

	/* Use this endpoint to get a list of all Plans in the Organization.
	 * 
	 */
	public String listPlans() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to create a new Plan. Note that it is important to also create an initial version of the Plan (e.g. 1.0). 
	 * This can either be done by including the 'initialVersion' property in the request, or by immediately following up with a call 
	 * to "Create Plan Version". If the former is done, then a first Plan version will be created automatically by this endpoint.
	 */
	public String createPlan() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to retrieve information about a single Plan by ID. Note that this only returns information about the Plan, 
	 * not about any particular *version* of the Plan.
	 */
	public String getPlanById() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to update information about a Plan.
	 * 
	 */
	public String updatePlan() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to delete a plan. Only an unlocked plan may be deleted.
	 * 
	 */
	public String deletePlan() {
		
		return apimanUrl;
	}
	
	/* This endpoint returns audit activity information about the Plan.
	 * 
	 */
	public String getPlanActivity() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to list all of the versions of a Plan.
	 * 
	 */
	public String listPlanVersions() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to create a new version of the Plan.
	 * 
	 */
	public String createPlanVersion() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get detailed information about a single version of a Plan.
	 * 
	 */
	public String getPlanVersion() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get audit activity information for a single version of the Plan.
	 * 
	 */
	public String getPlanVersionActivity() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to change the order of Policies for a Plan. When a Policy is added to the Plan, it is added as the last 
	 * Policy in the list of Plan Policies. Sometimes the order of Policies is important, so it is often useful to re-order the 
	 * Policies by invoking this endpoint. The body of the request should include all of the Policies for the Plan, in the new 
	 * desired order. Note that only the IDs of each of the Policies is actually required in the request, at a minimum.
	 */
	public String reorderPlanPolicies() {
		
		return apimanUrl;
	}
}
