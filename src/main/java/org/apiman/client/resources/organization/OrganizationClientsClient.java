package org.apiman.client.resources.organization;

import org.apiman.client.ApiClient;
import org.apiman.client.resources.organization.clients.ClientsApiRegistryClient;
import org.apiman.client.resources.organization.clients.ClientsContractsClient;
import org.apiman.client.resources.organization.clients.ClientsPoliciesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationClientsClient extends ApiClient {

	private static final String ORGANIZATION_CLIENTS_PATH = ORGANIZATIONS_PATH + "/{organizationId}/clients";
	
	@Autowired
	private ClientsApiRegistryClient clientsApiRegistryClient;
	@Autowired
	private ClientsContractsClient clientsContractsClient;
	@Autowired
	private ClientsPoliciesClient clientsPoliciesClient;
	
	public ClientsApiRegistryClient getClientsApiRegistryClient() {
		return clientsApiRegistryClient;
	}
	public ClientsContractsClient getClientsContractsClient() {
		return clientsContractsClient;
	}
	public ClientsPoliciesClient getClientsPoliciesClient() {
		return clientsPoliciesClient;
	}
	
	/* Use this endpoint to get a list of all Clients in the Organization.
	 * 
	 */
	public String listClients() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to create a new Client. Note that it is important to also create an initial version of the Client (e.g. 1.0). 
	 * This can either be done by including the 'initialVersion' property in the request, or by immediately following up with a call 
	 * to "Create Client Version". If the former is done, then a first Client version will be created automatically by this endpoint.
	 */
	public String createClient() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to retrieve information about a single Client by ID. Note that this only returns information about the Client, 
	 * not about any particular *version* of the Client.
	 */
	public String getClientById() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to update information about an Client.
	 * 
	 */
	public String updateClient() {
		
		return apimanUrl;
	}
	
	/* Delete a ClientApp
	 * 
	 */
	public String deleteClient() {
		
		return apimanUrl;
	}
	
	/* This endpoint returns audit activity information about the Client.
	 * 
	 */
	public String getClientActivity() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to list all of the versions of an Client.
	 * 
	 */
	public String listClientVersions() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to create a new version of the Client.
	 * 
	 */
	public String createClientVersion() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get detailed information about a single version of an Client.
	 * 
	 */
	public String getClientVersion() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get audit activity information for a single version of the Client.
	 * 
	 */
	public String getClientVersionActivity() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get the client's current API Key. This call will fail if you do not have the proper permission to see the information.
	 * 
	 */
	public String getApiKey() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to update the API Key for the given client. You can either provide your own custom (must be unique) API Key, 
	 * or you can send an empty request and apiman will generate a new API key for you. Note that if the client is already registered 
	 * with one or more Gateways, this call will fail (the API Key can only be modified if the client is not currently registered).
	 */
	public String updateApiKey() {
		
		return apimanUrl;
	}
	
	/* Retrieves metrics/analytics information for a specific client. This will return request count data broken down by API. 
	 * It basically answers the question "which APIs is my client really using?".
	 */
	public String getClientUsageMetricsPerApi() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to change the order of Policies for an Client. When a Policy is added to the Client, it is added as the 
	 * last Policy in the list of Client Policies. Sometimes the order of Policies is important, so it is often useful to re-order 
	 * the Policies by invoking this endpoint. The body of the request should include all of the Policies for the Client, in the 
	 * new desired order. Note that only the IDs of each of the Policies is actually required in the request, at a minimum.
	 */
	public String reorderClientPolicies() {
		
		return apimanUrl;
	}
}
