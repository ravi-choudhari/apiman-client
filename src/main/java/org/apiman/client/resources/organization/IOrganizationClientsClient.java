package org.apiman.client.resources.organization;

import java.util.Date;
import java.util.List;

import org.apiman.client.domain.audit.AuditEntry;
import org.apiman.client.domain.clients.ApiKey;
import org.apiman.client.domain.clients.Client;
import org.apiman.client.domain.clients.ClientVersion;
import org.apiman.client.domain.clients.NewClient;
import org.apiman.client.domain.clients.NewClientVersion;
import org.apiman.client.domain.clients.UpdateClient;
import org.apiman.client.domain.policies.PolicyChain;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.domain.summary.ClientSummary;
import org.apiman.client.domain.summary.ClientVersionSummary;
import org.apiman.client.domain.summary.Usage;
import org.apiman.client.resources.organization.clients.IClientsApiRegistryClient;
import org.apiman.client.resources.organization.clients.IClientsContractsClient;
import org.apiman.client.resources.organization.clients.IClientsPoliciesClient;

public interface IOrganizationClientsClient {

	List<ClientSummary> listClients(String organizationId);

	Client createClient(String organizationId, NewClient client);

	Client getClientById(String organizationId, String clientId);

	void updateClient(String organizationId, String clientId, UpdateClient client);

	void deleteClient(String organizationId, String clientId);

	SearchResults<AuditEntry> getClientActivity(String organizationId, String clientId, int page, int count);

	List<ClientVersionSummary> listClientVersions(String organizationId, String clientId);

	ClientVersion createClientVersion(String organizationId, String clientId, NewClientVersion clientVersion);

	ClientVersion getClientVersion(String organizationId, String clientId, String version);

	SearchResults<AuditEntry> getClientVersionActivity(String organizationId, String clientId, String version, int page,
			int count);

	ApiKey getApiKey(String organizationId, String clientId, String version);

	ApiKey updateApiKey(String organizationId, String clientId, String version, ApiKey apiKey);

	Usage getClientUsageMetricsPerApi(String organizationId, String clientId, String version, Date fromDate,
			Date toDate);

	void reorderClientPolicies(String organizationId, String clientId, String version, PolicyChain reOrderPolicies);
	
	IClientsApiRegistryClient getClientsApiRegistryClient();
	
	IClientsContractsClient getClientsContractsClient();
	
	IClientsPoliciesClient getClientsPoliciesClient();
}
