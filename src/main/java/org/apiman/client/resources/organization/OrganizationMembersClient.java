package org.apiman.client.resources.organization;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.OrganizationMember;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMembersClient extends AbstractApimanClient {

	private static final String ORGANIZATION_MEMBERS_PATH = ORGANIZATIONS_PATH + "/${organizationId}/members";
	
	/* Lists all members of the organization.
	 * 
	 */
	public List<OrganizationMember> listOrganizationMembers(String organizationId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_MEMBERS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map, true);
		
		OrganizationMember[] organizationMembers = restTemplate.getForObject(url, OrganizationMember[].class);
		return organizationMembers != null ? Arrays.asList(organizationMembers) : null;
	}
	
	/* Revoke all of a user's role memberships from the org.
	 * 
	 */
	public void revokeAllMemberships(String organizationId, String userId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_MEMBERS_PATH, "/${userId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("userId", userId);
		url = substitute(url, map, true);
		
		restTemplate.delete(url);
	}
}
