package org.apiman.client.resources.plugins;

import org.apiman.client.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class PluginsPolicyDefsClient extends ApiClient {

	
	/* Use this endpoint to get a list of all policy definitions contributed by the plugin.
	 * 
	 */
	public String getPluginPolicyDefinitions() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to retrieve the form associated with a particular policy definition. Plugins may contribute 
	 * policy definitions to apiman. Part of that contribution *may* include a form for the UI to display when configuring 
	 * an instance of the policy. This endpoint returns this form.
	 */
	public String getPluginPolicyForm() {
		
		return apimanUrl;
	}
}
