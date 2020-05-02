package org.apiman.client.resources.organization;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;

import java.util.HashMap;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.role.GrantRoles;
import org.springframework.stereotype.Component;

@Component
public class OrganizationRolesClientImpl extends AbstractApimanClient implements IOrganizationRolesClient {

	private static final String ORGANIZATION_ROLES_PATH = ORGANIZATIONS_PATH + "/${organizationId}/roles";
	
	/* Grant membership in a role to a user.
	 * 
	 */
	@Override
	public void grantMembership(String organizationId, GrantRoles grantMemberships) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_ROLES_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map, false);
		
		restTemplate.postForObject(url, grantMemberships, Void.class);
	}
	
	/* Revoke membership in a role.
	 * 
	 */
	@Override
	public void revokeSingleMembership(String organizationId, String roleId, String userId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_ROLES_PATH, "/${roleId}", "/${userId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("roleId", roleId);
		map.put("userId", userId);
		url = substitute(url, map, false);
		
		restTemplate.delete(url);
	}
}
