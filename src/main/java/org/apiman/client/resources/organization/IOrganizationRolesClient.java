package org.apiman.client.resources.organization;

import org.apiman.client.domain.role.GrantRoles;

public interface IOrganizationRolesClient {

	void grantMembership(String organizationId, GrantRoles grantMemberships);

	void revokeSingleMembership(String organizationId, String roleId, String userId);

}
