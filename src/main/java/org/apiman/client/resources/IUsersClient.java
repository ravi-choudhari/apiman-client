package org.apiman.client.resources;

import java.util.List;

import org.apiman.client.domain.audit.AuditEntry;
import org.apiman.client.domain.search.SearchCriteria;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.domain.summary.ApiSummary;
import org.apiman.client.domain.summary.ClientSummary;
import org.apiman.client.domain.summary.OrganizationSummary;
import org.apiman.client.domain.user.UpdateUser;
import org.apiman.client.domain.user.User;

public interface IUsersClient {

	SearchResults<User> searchForUsers(SearchCriteria userSearchQuery);

	User getUserById(String userId);

	void updateUserById(String userId, UpdateUser user);

	SearchResults<AuditEntry> getUserActivity(String userId, int page, int count);

	List<ApiSummary> listUserApis(String userId);

	List<ClientSummary> listUserClients(String userId);

	List<OrganizationSummary> listUserOrganizations(String userId);

}
