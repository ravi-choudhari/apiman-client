package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.List;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.currentuser.CurrentUser;
import org.apiman.client.domain.summary.ApiSummary;
import org.apiman.client.domain.summary.ClientSummary;
import org.apiman.client.domain.summary.OrganizationSummary;
import org.apiman.client.domain.user.UpdateUser;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserClient extends AbstractApimanClient {
	
	private static final String CURRENT_USER_PATH = "/currentuser";
	
	/* This endpoint returns a list of all the organizations for which the current user has permission to edit APIs. 
	 * For example, when creating a new API, the user interface must ask the user to choose within which Organization to 
	 * create it. This endpoint lists the valid choices for the current user.
	 */
	public List<OrganizationSummary> getApiOrganizations() {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, "/apiorgs");
		
		OrganizationSummary[] organizations = restTemplate.getForObject(url, OrganizationSummary[].class);
		return organizations != null ? Arrays.asList(organizations) : null;
	}
	
	/* Use this endpoint to list all of the APIs the current user has permission to edit. This includes all APIs from all 
	 * Organizations the user has API edit privileges for.
	 */
	public List<ApiSummary> getCurrentUserApis() {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, "/apis");
		ApiSummary[] apis = restTemplate.getForObject(url, ApiSummary[].class);
		
		return apis != null ? Arrays.asList(apis) : null;
	}
	
	/* This endpoint returns a list of all the organizations for which the current user has permission to edit clients. 
	 * For example, when creating a new Client, the user interface must ask the user to choose within which Organization to create it. 
	 * This endpoint lists the valid choices for the current user.
	 */
	public List<OrganizationSummary> getClientOrganizations() {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, "/clientorgs");
		
		OrganizationSummary[] organizations = restTemplate.getForObject(url, OrganizationSummary[].class);
		return organizations != null ? Arrays.asList(organizations) : null;
	}
	
	/* Use this endpoint to list all of the Clients the current user has permission to edit. This includes all Clients from all 
	 * Organizations the user has client edit privileges for.
	 */
	public List<ClientSummary> getCurrentUserClients() {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, CLIENTS_PATH);
		
		ClientSummary[] clients = restTemplate.getForObject(url, ClientSummary[].class);
		return clients != null ? Arrays.asList(clients) : null;
	}
	
	/* Use this endpoint to get information about the currently authenticated user.
	 * 
	 */
	public CurrentUser getCurrentUserInformation() {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, "/info");
		return restTemplate.getForObject(url, CurrentUser.class);
	}
	
	/* This endpoint allows updating information about the authenticated user.
	 * 
	 */
	public void updateCurrentUserInformation(UpdateUser userInformation) {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, "/info");
		restTemplate.exchange(url, PUT, new HttpEntity<UpdateUser>(userInformation, getHeaders()), Void.class);
	}
	
	/* This endpoint returns a list of all the organizations for which the current user has permission to edit plans. 
	 * For example, when creating a new Plan, the user interface must ask the user to choose within which Organization 
	 * to create it. This endpoint lists the valid choices for the current user.
	 */
	public List<OrganizationSummary> getPlanOrganizations() {
		
		String url = buildURL(apimanUrl, CURRENT_USER_PATH, "/planorgs");
		OrganizationSummary[] organizations = restTemplate.getForObject(url, OrganizationSummary[].class);
		
		return organizations != null ? Arrays.asList(organizations) : null;
	}
}
