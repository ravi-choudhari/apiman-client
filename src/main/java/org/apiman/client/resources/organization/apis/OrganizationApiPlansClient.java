package org.apiman.client.resources.organization.apis;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class OrganizationApiPlansClient extends ApiClient {

	private static final String ORGANIZATION_APIS_PLANS_PATH = ORGANIZATIONS_PATH + "/{organizationId}/apis/{apiId}/versions/{version}/plans";
	
	/* Use this endpoint to list the Plans configured for the given API version.
	 * 
	 */
	public String listApiPlans() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get a Policy Chain for the specific API version. A Policy Chain is a useful summary to better 
	 * understand which Policies would be executed for a request to this API through a particular Plan offered by the API. 
	 * Often this information is interesting prior to create a Contract with the API.
	 */
	public String getApiPolicyChain() {
		
		return apimanUrl;
	}
}
