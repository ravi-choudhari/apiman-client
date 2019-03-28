package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.HashMap;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.audit.AuditEntry;
import org.apiman.client.domain.organization.NewOrganization;
import org.apiman.client.domain.organization.Organization;
import org.apiman.client.domain.organization.UpdateOrganization;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.resources.organization.OrganizationApisClient;
import org.apiman.client.resources.organization.OrganizationClientsClient;
import org.apiman.client.resources.organization.OrganizationMembersClient;
import org.apiman.client.resources.organization.OrganizationPlansClient;
import org.apiman.client.resources.organization.OrganizationRolesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=false)
@Component
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PRIVATE)
public class OrganizationsClient extends AbstractApimanClient {
	
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
	
	/* Use this endpoint to create a new Organization.
	 * 
	 */
	public Organization createOrganization(NewOrganization organization) {
		
		String url = buildURL(apimanUrl, ORGANIZATIONS_PATH);
		return restTemplate.postForObject(url, organization, Organization.class);
	}
	
	/* Use this endpoint to get information about a single Organization by its ID.
	 * 
	 */
	public Organization getOrganizationById(String organizationId) {
		
		String url = buildURL(apimanUrl, ORGANIZATIONS_PATH, "/${organizationId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map, true);
		
		return restTemplate.getForObject(url, Organization.class);
	}
	
	/* Updates meta-information about a single Organization.
	 * 
	 */
	public void updateOrganization(String organizationId, UpdateOrganization organization) {
		
		String url = buildURL(apimanUrl, ORGANIZATIONS_PATH, "/${organizationId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map, true);
		
		restTemplate.exchange(url, PUT, new HttpEntity<UpdateOrganization>(organization, getHeaders()), Void.class);
	}
	
	/* Delete an org
	 * 
	 */
	public void deleteOrganization(String organizationId) {
		
		String url = buildURL(apimanUrl, ORGANIZATIONS_PATH, "/${organizationId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		url = substitute(url, map, true);
		
		restTemplate.delete(url);
	}
	
	/* Returns audit activity information for a single Organization. The audit information that is returned represents 
	 * all of the activity associated with this Organization (i.e. an audit log for everything in the Organization).
	 */
	public SearchResults<AuditEntry> getOrganizationActivity(String organizationId, int page, int count) {
		
		String url = buildURL(apimanUrl, ORGANIZATIONS_PATH, "/${organizationId}", ACTIVITY_PATH, PAGE_NUMBER_AND_COUNT);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("pageNumber", String.valueOf(page != 0 ? page : DEFAULT_VALUES.PAGE_NUMBER.getValue()));
		map.put("countPerPage", String.valueOf(count != 0 ? count : DEFAULT_VALUES.COUNT_PER_PAGE.getValue()));
		url = substitute(url, map, true);
		
		return restTemplate.getForObject(url, SearchResults.class);
	}	
}
