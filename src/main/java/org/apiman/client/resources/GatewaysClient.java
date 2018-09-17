package org.apiman.client.resources;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class GatewaysClient extends ApiClient {
	
	private static final String GATEWAYS_PATH = "/gateways";
	
	/* This endpoint returns a list of all the Gateways that have been configured.
	 * 
	 */
	public String listAllGateways() {
		
		return apimanUrl;
	}
	
	/* This endpoint is used to test the Gateway's settings prior to either creating or updating it. The information will 
	 * be used to attempt to create a link between the API Manager and the Gateway, by simply trying to ping the 
	 * Gateway's "status" endpoint.
	 */
	public String testGateway() {
		
		return apimanUrl;
	}
	
	/* This endpoint is called to create a new Gateway.
	 * 
	 */
	public String createGateway() {
		
		return apimanUrl;
	}
	
	/* Call this endpoint to get the details of a single configured Gateway.
	 * 
	 */
	public String getGatewayById() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to update an existing Gateway. Note that the name of the Gateway cannot be changed, as the name 
	 * is tied closely with the Gateway's ID. If you wish to rename the Gateway you must delete it and create a new one.
	 */
	public String updateGateway() {
		
		return apimanUrl;
	}
	
	/* This endpoint deletes a Gateway by its unique ID.
	 * 
	 */
	public String deleteGateway() {
		
		return apimanUrl;
	}
}
