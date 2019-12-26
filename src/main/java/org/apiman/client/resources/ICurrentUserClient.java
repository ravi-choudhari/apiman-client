package org.apiman.client.resources;

import java.util.List;

import org.apiman.client.domain.currentuser.CurrentUser;
import org.apiman.client.domain.summary.ApiSummary;
import org.apiman.client.domain.summary.ClientSummary;
import org.apiman.client.domain.summary.OrganizationSummary;
import org.apiman.client.domain.user.UpdateUser;

public interface ICurrentUserClient {

	List<OrganizationSummary> getApiOrganizations();

	List<ApiSummary> getCurrentUserApis();

	List<OrganizationSummary> getClientOrganizations();

	List<ClientSummary> getCurrentUserClients();

	CurrentUser getCurrentUserInformation();

	void updateCurrentUserInformation(UpdateUser userInformation);

	List<OrganizationSummary> getPlanOrganizations();

}
