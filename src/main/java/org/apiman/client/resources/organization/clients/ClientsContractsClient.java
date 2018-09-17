package org.apiman.client.resources.organization.clients;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class ClientsContractsClient extends ApiClient {

	private static final String ORGANIZATION_CLIENTS_CONTRACTS_PATH = ORGANIZATIONS_PATH + "/{organizationId}/clients/{clientId}/versions/{version}/contracts";
	
	/* Use this endpoint to get a list of all Contracts for an Client.
	 * 
	 */
	public String listAllContractsForClient() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to create a Contract between the Client and an API. In order to create a Contract, the caller must 
	 * specify the Organization, ID, and Version of the API. Additionally the caller must specify the ID of the Plan it 
	 * wished to use for the Contract with the API.
	 */
	public String creareApiContract() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to break all contracts between this client and its APIs.
	 * 
	 */
	public String breakAllContracts() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to retrieve detailed information about a single API Contract for an Client.
	 * 
	 */
	public String getApiContract() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to break a Contract with an API.
	 * 
	 */
	public String breakContract() {
		
		return apimanUrl;
	}
}
