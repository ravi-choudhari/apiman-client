package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.Action;
import org.springframework.stereotype.Component;

@Component
public class ActionsClient extends AbstractApimanClient {
	
	private static final String ACTIONS_PATH = "/actions";
		
	/* Call this endpoint in order to execute actions for apiman entities such as Plans, APIs, or Clients. 
	 * The type of the action must be included in the request payload.
	 */
	public void executeEntityAction(Action action) {
		
		String url = buildURL(apimanUrl, ACTIONS_PATH);
		restTemplate.postForObject(url, action, Void.class);
	}
}
