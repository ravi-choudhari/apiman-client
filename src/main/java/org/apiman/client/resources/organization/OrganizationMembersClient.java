package org.apiman.client.resources.organization;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMembersClient extends ApiClient {

	private static final String ORGANIZATION_MEMBERS_PATH = ORGANIZATIONS_PATH + "/{organizationId}/members";
	
	/* Lists all members of the organization.
	 * 
	 */
	public String listOrganizationMembers() {
		
		return apimanUrl;
	}
	
	/* Revoke all of a user's role memberships from the org.
	 * 
	 */
	public String revokeAllMemberships() {
		
		return apimanUrl;
	}
}
