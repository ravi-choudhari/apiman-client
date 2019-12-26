package org.apiman.client.resources.organization;

import java.util.List;

import org.apiman.client.domain.members.Member;

public interface IOrganizationMembersClient {

	List<Member> listOrganizationMembers(String organizationId);

	void revokeAllMemberships(String organizationId, String userId);

}
