package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.Gateway;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;

@Component
public class GatewaysClient extends AbstractApimanClient {
	
	private static final String GATEWAYS_PATH = "/gateways";
	
	/* This endpoint returns a list of all the Gateways that have been configured.
	 * 
	 */
	public List<Gateway> listAllGateways() {
		
		String url = buildURL(apimanUrl, GATEWAYS_PATH);
		Gateway[] gateways = restTemplate.getForObject(url, Gateway[].class);
		
		return gateways != null ? Arrays.asList(gateways) : null;
	}
	
	/* This endpoint is used to test the Gateway's settings prior to either creating or updating it. The information will 
	 * be used to attempt to create a link between the API Manager and the Gateway, by simply trying to ping the 
	 * Gateway's "status" endpoint.
	 */
	public void testGateway(Gateway gateway) {
		
		String url = buildURL(apimanUrl, GATEWAYS_PATH);
		restTemplate.exchange(url, PUT, new HttpEntity<Gateway>(gateway, getHeaders()), Void.class);
	}
	
	/* This endpoint is called to create a new Gateway.
	 * 
	 */
	public Gateway createGateway(Gateway gateway) {
		
		String url = buildURL(apimanUrl, GATEWAYS_PATH);
		return restTemplate.postForObject(url, gateway, Gateway.class);
	}
	
	/* Call this endpoint to get the details of a single configured Gateway.
	 * 
	 */
	public Gateway getGatewayById(String gatewayId) {
		
		String url = buildURL(apimanUrl, GATEWAYS_PATH, "/${gatewayId}");
		Map<String, String> map = new HashMap<>();
		map.put("gatewayId", gatewayId);
		url = substitute(url, map);
		
		return restTemplate.getForObject(url, Gateway.class);
	}
	
	/* Use this endpoint to update an existing Gateway. Note that the name of the Gateway cannot be changed, as the name 
	 * is tied closely with the Gateway's ID. If you wish to rename the Gateway you must delete it and create a new one.
	 */
	public void updateGateway(String gatewayId, Gateway gateway) {
		
		String url = buildURL(apimanUrl, GATEWAYS_PATH, "/${gatewayId}");
		Map<String, String> map = new HashMap<>();
		map.put("gatewayId", gatewayId);
		url = substitute(url, map);
		
		restTemplate.exchange(url, PUT, new HttpEntity<Gateway>(gateway, getHeaders()), Void.class);
	}
	
	/* This endpoint deletes a Gateway by its unique ID.
	 * 
	 */
	public void deleteGateway(String gatewayId) {
		
		String url = buildURL(apimanUrl, GATEWAYS_PATH, "/${gatewayId}");
		Map<String, String> map = new HashMap<>();
		map.put("gatewayId", gatewayId);
		url = substitute(url, map);
		
		restTemplate.delete(url);
	}
}
