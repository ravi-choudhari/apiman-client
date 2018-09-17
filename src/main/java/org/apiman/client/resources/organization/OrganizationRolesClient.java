package org.apiman.client.resources.organization;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class OrganizationRolesClient extends ApiClient {

	private static final String ORGANIZATION_ROLES_PATH = ORGANIZATIONS_PATH + "/{organizationId}/roles";
	
	/* Grant membership in a role to a user.
	 * 
	 */
	public String grantMembership() {
		
		return apimanUrl;
	}
	
	/* Revoke membership in a role.
	 * 
	 */
	public String revokeSingleMembership() {
		
		return apimanUrl;
	}
}
