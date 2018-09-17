package org.apiman.client.resources.search;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class ApiCatalogueSearchClient extends ApiClient {

	/* Use this endpoint to search for available APIs within any configured API catalogs. If no API catalogs 
	 * are configured, this will always return zero results.
	 */
	public String searchForApisInApiCatalogue() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get a list of all namespaces available to be searched within. Not all platforms support 
	 * this functionality. If no namespaces are found, then the UI should simply suppress the namespace filter.
	 */
	public String listAllNamespacesInApiCatalogue() {
		
		return apimanUrl;
	}
}
