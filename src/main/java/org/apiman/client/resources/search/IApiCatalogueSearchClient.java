package org.apiman.client.resources.search;

import java.util.List;

import org.apiman.client.domain.search.SearchCriteria;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.domain.summary.ApiNamespace;
import org.apiman.client.domain.summary.AvailableApi;

public interface IApiCatalogueSearchClient {

	SearchResults<AvailableApi> searchForApisInApiCatalogue(SearchCriteria apisSearchQuery);

	List<ApiNamespace> listAllNamespacesInApiCatalogue();

}
