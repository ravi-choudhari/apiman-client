package org.apiman.client.resources;

import org.apiman.client.domain.search.SearchCriteria;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.domain.summary.ApiSummary;
import org.apiman.client.domain.summary.ClientSummary;
import org.apiman.client.domain.summary.OrganizationSummary;
import org.apiman.client.resources.search.IApiCatalogueSearchClient;

public interface ISearchClient {

	SearchResults<ApiSummary> searchForApis(SearchCriteria apisSearchQuery);

	SearchResults<ClientSummary> searchForClients(SearchCriteria clientsSearchQuery);

	SearchResults<OrganizationSummary> searchForOrganizations(SearchCriteria organizationsSearchQuery);

	IApiCatalogueSearchClient getApiCatalogueSearchClient();
}
