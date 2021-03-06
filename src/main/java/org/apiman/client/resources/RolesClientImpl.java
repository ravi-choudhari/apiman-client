package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.role.NewRole;
import org.apiman.client.domain.role.Role;
import org.apiman.client.domain.role.UpdateRole;
import org.apiman.client.domain.search.SearchCriteria;
import org.apiman.client.domain.search.SearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RolesClientImpl extends AbstractApimanClient implements IRolesClient {
	
	@Autowired
	@Qualifier("redhatApimanAdminRestClient")
	protected RestTemplate adminTemplate;
	
	private static final String ROLES_PATH = "/roles";
	
	/* This endpoint lists all of the roles currently defined in apiman.
	 * 
	 */
	@Override
	public List<Role> listAllRoles() {
		
		String url = buildURL(apimanUrl, ROLES_PATH);
		Role[] roles = restTemplate.getForObject(url, Role[].class);
		
		return roles != null ? Arrays.asList(roles) : null;
	}
	
	/* Use this endpoint to create a new apiman role. A role consists of a set of permissions granted to a user 
	 * when that user is given the role within the context of an organization.
	 */
	@Override
	public Role createRole(NewRole role) {
		
		String url = buildURL(apimanUrl, ROLES_PATH);
		return adminTemplate.postForObject(url, role, Role.class);
	}
	
	/* This endpoint provides a way to search for roles. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	@Override
	public SearchResults<Role> searchForRoles(SearchCriteria rolesSearchQuery) {
		
		String url = buildURL(apimanUrl, ROLES_PATH, SEARCH_PATH);
		return restTemplate.postForObject(url, rolesSearchQuery, SearchResults.class);
	}
	
	/* Use this endpoint to retrieve information about a single Role by its ID.
	 * 
	 */
	@Override
	public Role getRoleById(String roleId) {
		
		String url = buildURL(apimanUrl, ROLES_PATH, "/${roleId}");
		Map<String, String> map = new HashMap<>();
		map.put("roleId", roleId);
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, Role.class);
	}

	/* Use this endpoint to update the information about an existing role. The role is identified by its ID.
	 * 
	 */
	@Override
	public void updateRoleById(String roleId, UpdateRole role) {
		
		String url = buildURL(apimanUrl, ROLES_PATH, "/${roleId}");
		Map<String, String> map = new HashMap<>();
		map.put("roleId", roleId);
		url = substitute(url, map, false);
		
		adminTemplate.exchange(url, PUT, new HttpEntity<UpdateRole>(role, getHeaders()), Void.class);
	}

	/* Use this endpoint to delete a role by its ID.
	 * 
	 */
	@Override
	public void deleteRoleById(String roleId) {
		
		String url = buildURL(apimanUrl, ROLES_PATH, "/${roleId}");
		Map<String, String> map = new HashMap<>();
		map.put("roleId", roleId);
		url = substitute(url, map, false);
		
		adminTemplate.delete(url);
	}
}
