package org.apiman.client.resources;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserClient extends ApiClient {
	
	private static final String CURRENT_USER_PATH = "/currentuser";
	
	/* This endpoint returns a list of all the organizations for which the current user has permission to edit APIs. 
	 * For example, when creating a new API, the user interface must ask the user to choose within which Organization to 
	 * create it. This endpoint lists the valid choices for the current user.
	 */
	public String getApiOrganizations() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to list all of the APIs the current user has permission to edit. This includes all APIs from all 
	 * Organizations the user has API edit privileges for.
	 */
	public String getCurrentUserApis() {
		
		return apimanUrl;
	}
	
	/* This endpoint returns a list of all the organizations for which the current user has permission to edit clients. 
	 * For example, when creating a new Client, the user interface must ask the user to choose within which Organization to create it. 
	 * This endpoint lists the valid choices for the current user.
	 */
	public String getClientOrganizations() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to list all of the Clients the current user has permission to edit. This includes all Clients from all 
	 * Organizations the user has client edit privileges for.
	 */
	public String getCurrentUserClients() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get information about the currently authenticated user.
	 * 
	 */
	public String getCurrentUserInformation() {
		
		return apimanUrl;
	}
	
	/* This endpoint allows updating information about the authenticated user.
	 * 
	 */
	public String updateCurrentUserInformation() {
		
		return apimanUrl;
	}
	
	/* This endpoint returns a list of all the organizations for which the current user has permission to edit plans. 
	 * For example, when creating a new Plan, the user interface must ask the user to choose within which Organization 
	 * to create it. This endpoint lists the valid choices for the current user.
	 */
	public String getPlanOrganizations() {
		
		return apimanUrl;
	}
}
