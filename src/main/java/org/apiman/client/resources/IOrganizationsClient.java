package org.apiman.client.resources;

import org.apiman.client.domain.audit.AuditEntry;
import org.apiman.client.domain.organization.NewOrganization;
import org.apiman.client.domain.organization.Organization;
import org.apiman.client.domain.organization.UpdateOrganization;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.resources.organization.IOrganizationApisClient;
import org.apiman.client.resources.organization.IOrganizationClientsClient;
import org.apiman.client.resources.organization.IOrganizationMembersClient;
import org.apiman.client.resources.organization.IOrganizationPlansClient;
import org.apiman.client.resources.organization.IOrganizationRolesClient;

public interface IOrganizationsClient {

	Organization createOrganization(NewOrganization organization);

	Organization getOrganizationById(String organizationId);

	void updateOrganization(String organizationId, UpdateOrganization organization);

	void deleteOrganization(String organizationId);

	SearchResults<AuditEntry> getOrganizationActivity(String organizationId, int page, int count);

	IOrganizationApisClient getApisClient();
	
	IOrganizationClientsClient getClientsClient();
	
	IOrganizationMembersClient getMembersClient();
	
	IOrganizationPlansClient getPlansClient();
	
	IOrganizationRolesClient getRolesClient();
}
