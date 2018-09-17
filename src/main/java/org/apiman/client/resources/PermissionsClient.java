package org.apiman.client.resources;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class PermissionsClient extends ApiClient {
	
	private static final String PERMISSIONS_PATH = "/permissions";
	
	/* This endpoint returns all of the permissions assigned to the currently authenticated user.
	 * 
	 */
	public String getCurrentUserPermissions() {
		
		return apimanUrl;
	}
	
	/* This endpoint returns all of the permissions assigned to a specific user.
	 * 
	 */
	public String getUserPermissions() {
		
		return apimanUrl;
	}
}
