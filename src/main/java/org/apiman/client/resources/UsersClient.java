package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.audit.AuditEntry;
import org.apiman.client.domain.search.SearchCriteria;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.domain.summary.ApiSummary;
import org.apiman.client.domain.summary.ClientSummary;
import org.apiman.client.domain.summary.OrganizationSummary;
import org.apiman.client.domain.user.UpdateUser;
import org.apiman.client.domain.user.User;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class UsersClient extends AbstractApimanClient {
	
	private static final String USERS_PATH = "/users";
	
	/* Use this endpoint to search for users. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	public SearchResults<User> searchForUsers(SearchCriteria userSearchQuery) {
		
		String url = buildURL(apimanUrl, USERS_PATH, SEARCH_PATH);
		return restTemplate.postForObject(url, userSearchQuery, SearchResults.class);
	}
	
	/* Use this endpoint to get information about a specific user by the User ID.
	 */
	public User getUserById(String userId) {
		
		String url = buildURL(apimanUrl, USERS_PATH, "/${userId}");
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		url = substitute(url, map, true);
		
		return restTemplate.getForObject(url, User.class);
	}
	
	/* Use this endpoint to update the information about a user. This will fail unless the authenticated user 
	 * is an admin or identical to the user being updated.
	 */
	public void updateUserById(String userId, UpdateUser user) {
		
		String url = buildURL(apimanUrl, USERS_PATH, "/${userId}");
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		url = substitute(url, map, true);
		
		restTemplate.exchange(url, PUT, new HttpEntity<UpdateUser>(user, getHeaders()), Void.class);
	}
	
	/* Use this endpoint to get information about the user's audit history. This returns audit entries 
	 * corresponding to each of the actions taken by the user. For example, when a user creates a new Organization, 
	 * an audit entry is recorded and would be included in the result of this endpoint.
	 */
	public SearchResults<AuditEntry> getUserActivity(String userId, int page, int count) {
		
		String url = buildURL(apimanUrl, USERS_PATH, "/${userId}", ACTIVITY_PATH, PAGE_NUMBER_AND_COUNT);
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("pageNumber", String.valueOf(page != 0 ? page : DEFAULT_VALUES.PAGE_NUMBER.getValue()));
		map.put("countPerPage", String.valueOf(count != 0 ? count : DEFAULT_VALUES.COUNT_PER_PAGE.getValue()));
		url = substitute(url, map, true);
		
		return restTemplate.getForObject(url, SearchResults.class);
	}
	
	/* This endpoint returns all APIs that the user has permission to edit.
	 * 
	 */
	public List<ApiSummary> listUserApis(String userId) {
		
		String url = buildURL(apimanUrl, USERS_PATH, "/${userId}", APIS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		url = substitute(url, map, true);
		
		ApiSummary[] apis = restTemplate.getForObject(url, ApiSummary[].class);
		return apis != null ? Arrays.asList(apis) : null;
	}
	
	/* This endpoint returns all clients that the user has permission to edit.
	 * 
	 */
	public List<ClientSummary> listUserClients(String userId) {
		
		String url = buildURL(apimanUrl, USERS_PATH, "/${userId}", CLIENTS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		url = substitute(url, map, true);
		
		ClientSummary[] clients = restTemplate.getForObject(url, ClientSummary[].class);
		return clients != null ? Arrays.asList(clients) : null;
	}
	
	/* This endpoint returns the list of organizations that the user is a member of. 
	 * The user is a member of an organization if she has at least one role for the org.
	 */
	public List<OrganizationSummary> listUserOrganizations(String userId) {
		
		String url = buildURL(apimanUrl, USERS_PATH, "/${userId}", ORGANIZATIONS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		url = substitute(url, map, true);
		
		OrganizationSummary[] organizations = restTemplate.getForObject(url, OrganizationSummary[].class);
		return organizations != null ? Arrays.asList(organizations) : null;
	}
}
