package org.apiman.client.resources.organization.apis;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class OrganizationApiPoliciesClient extends ApiClient {

	private static final String ORGANIZATION_APIS_POLICIES_PATH = ORGANIZATIONS_PATH + "/{organizationId}/apis/{apiId}/versions/{version}/policies";
	
	/* Use this endpoint to list all of the Policies configured for the API.
	 * 
	 */
	public String listAllApiPolicies() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to add a new Policy to the API version.
	 * 
	 */
	public String addApiPolicy() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get information about a single Policy in the API version.
	 * 
	 */
	public String getApiPolicy() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to update the meta-data or configuration of a single API Policy.
	 * 
	 */
	public String updateApiPolicy() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to remove a Policy from the API.
	 * 
	 */
	public String removeApiPolicy() {
		
		return apimanUrl;
	}
}
