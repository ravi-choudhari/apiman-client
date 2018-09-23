package org.apiman.client.resources.organization;

import static org.apiman.client.GenericUtils.buildURL;
import static org.apiman.client.GenericUtils.encode;
import static org.apiman.client.GenericUtils.substitute;

import java.util.HashMap;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.GrantMemberships;
import org.springframework.stereotype.Component;

@Component
public class OrganizationRolesClient extends AbstractApimanClient {

	private static final String ORGANIZATION_ROLES_PATH = ORGANIZATIONS_PATH + "/{organizationId}/roles";
	
	/* Grant membership in a role to a user.
	 * 
	 */
	public void grantMembership(String organizationId, GrantMemberships grantMemberships) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_ROLES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map);
		
		restTemplate.postForObject(encode(url), grantMemberships, Void.class);
	}
	
	/* Revoke membership in a role.
	 * 
	 */
	public void revokeSingleMembership(String organizationId, String roleId, String userId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_ROLES_PATH, "/{roleId}", "/{userId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("roleId", roleId);
		map.put("userId", userId);
		url = substitute(url, map);
		
		restTemplate.delete(encode(url));
	}
}
