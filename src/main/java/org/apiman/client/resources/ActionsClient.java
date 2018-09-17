package org.apiman.client.resources;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class ActionsClient extends ApiClient {
	
	private static final String ACTIONS_PATH = "/actions";
		
	/* Call this endpoint in order to execute actions for apiman entities such as Plans, APIs, or Clients. 
	 * The type of the action must be included in the request payload.
	 */
	public String executeEntityAction() {
		
		return apimanUrl;
	}
}
