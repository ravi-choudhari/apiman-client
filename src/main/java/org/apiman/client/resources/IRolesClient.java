package org.apiman.client.resources;

import java.util.List;

import org.apiman.client.domain.role.NewRole;
import org.apiman.client.domain.role.Role;
import org.apiman.client.domain.role.UpdateRole;
import org.apiman.client.domain.search.SearchCriteria;
import org.apiman.client.domain.search.SearchResults;

public interface IRolesClient {

	Role createRole(NewRole role);

	void updateRoleById(String roleId, UpdateRole role);

	void deleteRoleById(String roleId);

	List<Role> listAllRoles();

	SearchResults<Role> searchForRoles(SearchCriteria rolesSearchQuery);

	Role getRoleById(String roleId);

}
