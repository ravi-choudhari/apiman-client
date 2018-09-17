package org.apiman.client.resources;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class RolesClient extends ApiClient {
	
	private static final String ROLES_PATH = "/roles";
	
	/* This endpoint lists all of the roles currently defined in apiman.
	 * 
	 */
	public String listAllRoles() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to create a new apiman role. A role consists of a set of permissions granted to a user 
	 * when that user is given the role within the context of an organization.
	 */
	public String createRole() {
		
		return apimanUrl;
	}
	
	/* This endpoint provides a way to search for roles. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	public String searchForRoles() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to retrieve information about a single Role by its ID.
	 * 
	 */
	public String getRoleById() {
		
		return apimanUrl;
	}

	/* Use this endpoint to update the information about an existing role. The role is identified by its ID.
	 * 
	 */
	public String updateRoleById() {
		
		return apimanUrl;
	}

	/* Use this endpoint to delete a role by its ID.
	 * 
	 */
	public String deleteRoleById() {
		
		return apimanUrl;
	}
}
