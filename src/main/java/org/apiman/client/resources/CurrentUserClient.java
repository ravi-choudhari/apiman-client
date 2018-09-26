package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.List;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.Api;
import org.apiman.client.domain.Client;
import org.apiman.client.domain.Organization;
import org.apiman.client.domain.User;
import org.apiman.client.domain.UserInformation;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserClient extends AbstractApimanClient {
	
	private static final String CURRENT_USER_PATH = "/currentuser";
	
	/* This endpoint returns a list of all the organizations for which the current user has permission to edit APIs. 
	 * For example, when creating a new API, the user interface must ask the user to choose within which Organization to 
	 * create it. This endpoint lists the valid choices for the current user.
	 */
	public List<Organization> getApiOrganizations() {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, "/apiorgs");
		
		Organization[] organizations = restTemplate.getForObject(url, Organization[].class);
		return organizations != null ? Arrays.asList(organizations) : null;
	}
	
	/* Use this endpoint to list all of the APIs the current user has permission to edit. This includes all APIs from all 
	 * Organizations the user has API edit privileges for.
	 */
	public List<Api> getCurrentUserApis() {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, "/apis");
		Api[] apis = restTemplate.getForObject(url, Api[].class);
		
		return apis != null ? Arrays.asList(apis) : null;
	}
	
	/* This endpoint returns a list of all the organizations for which the current user has permission to edit clients. 
	 * For example, when creating a new Client, the user interface must ask the user to choose within which Organization to create it. 
	 * This endpoint lists the valid choices for the current user.
	 */
	public List<Organization> getClientOrganizations() {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, "/clientorgs");
		
		Organization[] organizations = restTemplate.getForObject(url, Organization[].class);
		return organizations != null ? Arrays.asList(organizations) : null;
	}
	
	/* Use this endpoint to list all of the Clients the current user has permission to edit. This includes all Clients from all 
	 * Organizations the user has client edit privileges for.
	 */
	public List<Client> getCurrentUserClients() {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, CLIENTS_PATH);
		
		Client[] clients = restTemplate.getForObject(url, Client[].class);
		return clients != null ? Arrays.asList(clients) : null;
	}
	
	/* Use this endpoint to get information about the currently authenticated user.
	 * 
	 */
	public User getCurrentUserInformation() {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, "/info");
		return restTemplate.getForObject(url, User.class);
	}
	
	/* This endpoint allows updating information about the authenticated user.
	 * 
	 */
	public void updateCurrentUserInformation(UserInformation userInformation) {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, "/info");
		restTemplate.exchange(url, PUT, new HttpEntity<UserInformation>(userInformation, getHeaders()), Void.class);
	}
	
	/* This endpoint returns a list of all the organizations for which the current user has permission to edit plans. 
	 * For example, when creating a new Plan, the user interface must ask the user to choose within which Organization 
	 * to create it. This endpoint lists the valid choices for the current user.
	 */
	public List<Organization> getPlanOrganizations() {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, "/planorgs");
		Organization[] organizations = restTemplate.getForObject(url, Organization[].class);
		
		return organizations != null ? Arrays.asList(organizations) : null;
	}
}
