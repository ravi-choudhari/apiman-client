package org.apiman.client.resources.organization.clients;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;

import java.util.HashMap;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.springframework.stereotype.Component;

@Component
public class ClientsApiRegistryClientImpl extends AbstractApimanClient implements IClientsApiRegistryClient {

	private static final String ORGANIZATION_CLIENTS_API_REGISTRY_PATH = ORGANIZATION_CLIENTS_PATH + "/${clientId}/versions/${version}/apiregistry";
	
	/* Use this endpoint to get registry style information about all APIs that this Client consumes. This is a useful endpoint to 
	 * invoke in order to retrieve a summary of every API consumed by the client. The information returned by this endpoint 
	 * could potentially be included directly in a client as a way to lookup endpoint information for the APIs it wishes to consume. 
	 * This variant of the API Registry is formatted as JSON data. Note that, optionally, you can generate a temporary download link 
	 * instead of getting the registry file directly. To do this, simply pass download=true as a query parameter. The result will 
	 * then be a JSON object with information about the temporary download link. The ID of the download can then be used when making 
	 * a call to the /downloads/${downloadId} endpoint to fetch the actual content.
	 */
	@Override
	public String getJsonApiRegistry(String organizationId, String clientId, String version, String download) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_API_REGISTRY_PATH, "/json", DOWNLOAD);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		map.put("download", download);
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, String.class);
	}
	
	/* Use this endpoint to get registry style information about all APIs that this Client consumes. This is a useful endpoint to 
	 * invoke in order to retrieve a summary of every API consumed by the client. The information returned by this endpoint 
	 * could potentially be included directly in a client as a way to lookup endpoint information for the APIs it wishes to consume. 
	 * This variant of the API Registry is formatted as XML data. Note that, optionally, you can generate a temporary download link 
	 * instead of getting the registry file directly. To do this, simply pass download=true as a query parameter. The result will 
	 * then be a JSON object with information about the temporary download link. The ID of the download can then be used when making 
	 * a call to the /downloads/${downloadId} endpoint to fetch the actual content.
	 */
	@Override
	public String getXmlApiRegistry(String organizationId, String clientId, String version, String download) {
		
		String url = buildURL(apimanUrl, ORGANIZATION_CLIENTS_API_REGISTRY_PATH, "/xml", DOWNLOAD);
		Map<String, String> map = new HashMap<>();
		map.put("organizationId", organizationId);
		map.put("clientId", clientId);
		map.put("version", version);
		map.put("download", download);
		url = substitute(url, map, false);
		
		return restTemplate.getForObject(url, String.class);
	}
}
