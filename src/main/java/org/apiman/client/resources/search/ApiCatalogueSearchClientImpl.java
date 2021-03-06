package org.apiman.client.resources.search;

import static org.apiman.client.util.GenericUtils.buildURL;

import java.util.Arrays;
import java.util.List;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.search.SearchCriteria;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.domain.summary.ApiNamespace;
import org.apiman.client.domain.summary.AvailableApi;
import org.springframework.stereotype.Component;

@Component
public class ApiCatalogueSearchClientImpl extends AbstractApimanClient implements IApiCatalogueSearchClient {

	private static final String API_CATALOGUE_PATH = "/apiCatalog";
	private static final String NAMESPACES_PATH = "/namespaces";
	private static final String ENTRIES_PATH = "/entries";
	
	/* Use this endpoint to search for available APIs within any configured API catalogs. If no API catalogs 
	 * are configured, this will always return zero results.
	 */
	@Override
	public SearchResults<AvailableApi> searchForApisInApiCatalogue(SearchCriteria apisSearchQuery) {
		
		String url = buildURL(apimanUrl, SEARCH_PATH, API_CATALOGUE_PATH, ENTRIES_PATH);
		return restTemplate.postForObject(url, apisSearchQuery, SearchResults.class);
	}
	
	/* Use this endpoint to get a list of all namespaces available to be searched within. Not all platforms support 
	 * this functionality. If no namespaces are found, then the UI should simply suppress the namespace filter.
	 */
	@Override
	public List<ApiNamespace> listAllNamespacesInApiCatalogue() {
		
		String url = buildURL(apimanUrl, SEARCH_PATH, API_CATALOGUE_PATH, NAMESPACES_PATH);
		
		ApiNamespace[] namespaces = restTemplate.getForObject(url, ApiNamespace[].class);
		return namespaces != null ? Arrays.asList(namespaces) : null;
	}
}
