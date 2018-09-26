package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.Role;
import org.apiman.client.domain.search.SearchQuery;
import org.apiman.client.domain.search.SearchResult;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class RolesClient extends AbstractApimanClient {
	
	private static final String ROLES_PATH = "/roles";
	
	/* This endpoint lists all of the roles currently defined in apiman.
	 * 
	 */
	public List<Role> listAllRoles() {
		
		String url = buildURL(apimanUrl, ROLES_PATH);
		Role[] roles = restTemplate.getForObject(url, Role[].class);
		
		return roles != null ? Arrays.asList(roles) : null;
	}
	
	/* Use this endpoint to create a new apiman role. A role consists of a set of permissions granted to a user 
	 * when that user is given the role within the context of an organization.
	 */
	public Role createRole(Role role) {
		
		String url = buildURL(apimanUrl, ROLES_PATH);
		return restTemplate.postForObject(url, role, Role.class);
	}
	
	/* This endpoint provides a way to search for roles. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	public SearchResult searchForRoles(SearchQuery rolesSearchQuery) {
		
		String url = buildURL(apimanUrl, ROLES_PATH, SEARCH_PATH);
		return restTemplate.postForObject(url, rolesSearchQuery, SearchResult.class);
	}
	
	/* Use this endpoint to retrieve information about a single Role by its ID.
	 * 
	 */
	public Role getRoleById(String roleId) {
		
		String url = buildURL(apimanUrl, ROLES_PATH, "/${roleId}");
		Map<String, String> map = new HashMap<>();
		map.put("roleId", roleId);
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, Role.class);
	}

	/* Use this endpoint to update the information about an existing role. The role is identified by its ID.
	 * 
	 */
	public void updateRoleById(String roleId, Role role) {
		
		String url = buildURL(apimanUrl, ROLES_PATH, "/${roleId}");
		Map<String, String> map = new HashMap<>();
		map.put("roleId", roleId);
		url = substitute(url, map);
		restTemplate.exchange(url, PUT, new HttpEntity<Role>(role, getHeaders()), Void.class);
	}

	/* Use this endpoint to delete a role by its ID.
	 * 
	 */
	public void deleteRoleById(String roleId) {
		
		String url = buildURL(apimanUrl, ROLES_PATH, "/${roleId}");
		Map<String, String> map = new HashMap<>();
		map.put("roleId", roleId);
		url = substitute(url, map);
		
		restTemplate.delete(url);
	}
}
