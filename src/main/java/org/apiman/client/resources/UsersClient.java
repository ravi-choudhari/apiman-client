package org.apiman.client.resources;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class UsersClient extends ApiClient {
	
	private static final String USERS_PATH = "/users";
	
	/* Use this endpoint to search for users. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	public String searchForUsers() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get information about a specific user by the User ID.
	 */
	public String getUserById() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to update the information about a user. This will fail unless the authenticated user 
	 * is an admin or identical to the user being updated.
	 */
	public String updateUserById() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get information about the user's audit history. This returns audit entries 
	 * corresponding to each of the actions taken by the user. For example, when a user creates a new Organization, 
	 * an audit entry is recorded and would be included in the result of this endpoint.
	 */
	public String getUserActivity() {
		
		return apimanUrl;
	}
	
	/* This endpoint returns all APIs that the user has permission to edit.
	 * 
	 */
	public String listUserApis() {
		
		return apimanUrl;
	}
	
	/* This endpoint returns all clients that the user has permission to edit.
	 * 
	 */
	public String listUserClients() {
		
		return apimanUrl;
	}
	
	/* This endpoint returns the list of organizations that the user is a member of. 
	 * The user is a member of an organization if she has at least one role for the org.
	 */
	public String listUserOrganizations() {
		
		return apimanUrl;
	}
}
