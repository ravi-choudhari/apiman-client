package org.apiman.client.resources.organization.clients;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.ClientContract;
import org.springframework.stereotype.Component;

@Component
public class ClientsContractsClient extends AbstractApimanClient {

	private static final String ORGANIZATION_CLIENTS_CONTRACTS_PATH = ORGANIZATION_CLIENTS_PATH + "/${clientId}/versions/${version}/contracts";
	
	/* Use this endpoint to get a list of all Contracts for an Client.
	 * 
	 */
	public List<ClientContract> listAllContractsForClient(String organizationId, String clientId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_CONTRACTS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		url = substitute(url, map, true);
		
		ClientContract[] clientContracts = restTemplate.getForObject(url, ClientContract[].class);
		return clientContracts != null ? Arrays.asList(clientContracts) : null;
	}
	
	/* Use this endpoint to create a Contract between the Client and an API. In order to create a Contract, the caller must 
	 * specify the Organization, ID, and Version of the API. Additionally the caller must specify the ID of the Plan it 
	 * wished to use for the Contract with the API.
	 */
	public ClientContract creareApiContract(String organizationId, String clientId, String version, ClientContract apiContract) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_CONTRACTS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		url = substitute(url, map, true);
		
		return restTemplate.postForObject(url, apiContract, ClientContract.class);
	}
	
	/* Use this endpoint to break all contracts between this client and its APIs.
	 * 
	 */
	public void breakAllContracts(String organizationId, String clientId, String version) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_CONTRACTS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		url = substitute(url, map, true);
		
		restTemplate.delete(url);
	}
	
	/* Use this endpoint to retrieve detailed information about a single API Contract for an Client.
	 * 
	 */
	public ClientContract getApiContract(String organizationId, String clientId, String version, String contractId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_CONTRACTS_PATH, "/${contractId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		map.put("contractId", contractId);
		url = substitute(url, map, true);
		
		return restTemplate.getForObject(url, ClientContract.class);
	}
	
	/* Use this endpoint to break a Contract with an API.
	 * 
	 */
	public void breakContract(String organizationId, String clientId, String version, String contractId) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_CONTRACTS_PATH, "/${contractId}");
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		map.put("contractId", contractId);
		url = substitute(url, map, true);
		
		restTemplate.delete(url);
	}
}
