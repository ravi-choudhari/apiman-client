package org.apiman.client.resources;

import org.apiman.client.ApiClient;
import org.apiman.client.resources.search.ApiCatalogueSearchClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchClient extends ApiClient {
	
	private static final String SEARCH_PATH = "/search";
	
	@Autowired
	private ApiCatalogueSearchClient apiCatalogueSearchClient;
	
	public ApiCatalogueSearchClient getApiCatalogueSearchClient() {
		return apiCatalogueSearchClient;
	}
	
	/* Use this endpoint to search for APIs. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	public String searchForApis() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to search for clients. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	public String searchForClients() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to search for organizations. The search criteria is provided in the body of the request, 
	 * including filters, order-by, and paging information.
	 */
	public String searchForOrganizations() {
		
		return apimanUrl;
	}
}
