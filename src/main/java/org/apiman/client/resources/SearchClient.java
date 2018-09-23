package org.apiman.client.resources;

import static org.apiman.client.GenericUtils.buildURL;
import static org.apiman.client.GenericUtils.encode;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.search.SearchQuery;
import org.apiman.client.domain.search.SearchResult;
import org.apiman.client.resources.search.ApiCatalogueSearchClient;
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
public class SearchClient extends AbstractApimanClient {
	
	@Autowired
	private ApiCatalogueSearchClient apiCatalogueSearchClient;
	
	/* Use this endpoint to search for APIs. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	public SearchResult searchForApis(SearchQuery apisSearchQuery) {
		
		String url = buildURL(apimanUrl, SEARCH_PATH, APIS_PATH);
		return restTemplate.postForObject(encode(url), apisSearchQuery, SearchResult.class);
	}
	
	/* Use this endpoint to search for clients. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	public SearchResult searchForClients(SearchQuery clientsSearchQuery) {
		
		String url = buildURL(apimanUrl, SEARCH_PATH, CLIENTS_PATH);
		return restTemplate.postForObject(encode(url), clientsSearchQuery, SearchResult.class);
	}
	
	/* Use this endpoint to search for organizations. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	public SearchResult searchForOrganizations(SearchQuery organizationsSearchQuery) {
		
		String url = buildURL(apimanUrl, SEARCH_PATH, ORGANIZATIONS_PATH);
		return restTemplate.postForObject(encode(url), organizationsSearchQuery, SearchResult.class);
	}
}
