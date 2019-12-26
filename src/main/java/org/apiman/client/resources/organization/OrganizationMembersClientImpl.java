package org.apiman.client.resources.organization;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.members.Member;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMembersClientImpl extends AbstractApimanClient implements IOrganizationMembersClient {

	private static final String ORGANIZATION_MEMBERS_PATH = ORGANIZATIONS_PATH + "/${organizationId}/members";
	
	/* Lists all members of the organization.
	 * 
	 */
	@Override
	public List<Member> listOrganizationMembers(String organizationId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_MEMBERS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map, true);
		
		Member[] organizationMembers = restTemplate.getForObject(url, Member[].class);
		return organizationMembers != null ? Arrays.asList(organizationMembers) : null;
	}
	
	/* Revoke all of a user's role memberships from the org.
	 * 
	 */
	@Override
	public void revokeAllMemberships(String organizationId, String userId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_MEMBERS_PATH, "/${userId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("userId", userId);
		url = substitute(url, map, true);
		
		restTemplate.delete(url);
	}
}
