package org.apiman.client.resources;

import org.apiman.client.ApiClient;
import org.apiman.client.resources.organization.OrganizationApisClient;
import org.apiman.client.resources.organization.OrganizationClientsClient;
import org.apiman.client.resources.organization.OrganizationMembersClient;
import org.apiman.client.resources.organization.OrganizationPlansClient;
import org.apiman.client.resources.organization.OrganizationRolesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationsClient extends ApiClient {
	
	@Autowired
	private OrganizationApisClient apisClient;
	@Autowired
	private OrganizationClientsClient clientsClient;
	@Autowired
	private OrganizationMembersClient membersClient;
	@Autowired
	private OrganizationPlansClient plansClient;
	@Autowired
	private OrganizationRolesClient rolesClient;
	
	public OrganizationApisClient getApisClient() {
		return apisClient;
	}
	public OrganizationClientsClient getClientsClient() {
		return clientsClient;
	}
	public OrganizationMembersClient getMembersClient() {
		return membersClient;
	}
	public OrganizationPlansClient getPlansClient() {
		return plansClient;
	}
	public OrganizationRolesClient getRolesClient() {
		return rolesClient;
	}

	/* Use this endpoint to create a new Organization.
	 * 
	 */
	public String createOrganization() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get information about a single Organization by its ID.
	 * 
	 */
	public String getOrganizationById() {
		
		return apimanUrl;
	}
	
	/* Updates meta-information about a single Organization.
	 * 
	 */
	public String updateOrganization() {
		
		return apimanUrl;
	}
	
	/* Delete an org
	 * 
	 */
	public String deleteOrganization() {
		
		return apimanUrl;
	}
	
	/* Returns audit activity information for a single Organization. The audit information that is returned represents 
	 * all of the activity associated with this Organization (i.e. an audit log for everything in the Organization).
	 */
	public String getOrganizationActivity() {
		
		return apimanUrl;
	}	
}
