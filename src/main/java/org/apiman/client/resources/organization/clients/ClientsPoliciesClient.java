package org.apiman.client.resources.organization.clients;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class ClientsPoliciesClient extends ApiClient {

	private static final String ORGANIZATION_CLIENTS_POLICIES_PATH = ORGANIZATIONS_PATH + "/{organizationId}/clients/{clientId}/versions/{version}/policies";
	
	/* Use this endpoint to list all of the Policies configured for the Client.
	 * 
	 */
	public String listAllClientPolicies() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to add a new Policy to the Client version.
	 * 
	 */
	public String addClientPolicy() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get information about a single Policy in the Client version.
	 * 
	 */
	public String getClientPolicy() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to update the meta-data or configuration of a single Client Policy.
	 * 
	 */
	public String updateClientPolicy() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to remove a Policy from the Client.
	 * 
	 */
	public String removeClientPolicy() {
		
		return apimanUrl;
	}
}
