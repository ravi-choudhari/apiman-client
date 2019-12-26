package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.search.SearchCriteria;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.domain.summary.ApiSummary;
import org.apiman.client.domain.summary.ClientSummary;
import org.apiman.client.domain.summary.OrganizationSummary;
import org.apiman.client.resources.search.IApiCatalogueSearchClient;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SearchClientImpl extends AbstractApimanClient implements ISearchClient {
	
	@Autowired
	private IApiCatalogueSearchClient apiCatalogueSearchClient;
	
	/* Use this endpoint to search for APIs. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	@Override
	public SearchResults<ApiSummary> searchForApis(SearchCriteria apisSearchQuery) {
		
		String url = buildURL(apimanUrl, SEARCH_PATH, APIS_PATH);
		return restTemplate.postForObject(url, apisSearchQuery, SearchResults.class);
	}
	
	/* Use this endpoint to search for clients. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	@Override
	public SearchResults<ClientSummary> searchForClients(SearchCriteria clientsSearchQuery) {
		
		String url = buildURL(apimanUrl, SEARCH_PATH, CLIENTS_PATH);
		return restTemplate.postForObject(url, clientsSearchQuery, SearchResults.class);
	}
	
	/* Use this endpoint to search for organizations. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	@Override
	public SearchResults<OrganizationSummary> searchForOrganizations(SearchCriteria organizationsSearchQuery) {
		
		String url = buildURL(apimanUrl, SEARCH_PATH, ORGANIZATIONS_PATH);
		return restTemplate.postForObject(url, organizationsSearchQuery, SearchResults.class);
	}
}
