package org.apiman.client.resources;

import org.apiman.client.domain.permissions.UserPermissions;

public interface IPermissionsClient {

	UserPermissions getUserPermissions(String userId);

	UserPermissions getCurrentUserPermissions();

}
