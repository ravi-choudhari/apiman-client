package org.apiman.client.resources.plugins;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.policydefinition.PolicyDefinition;
import org.apiman.client.domain.summary.PolicyDefinitionSummary;
import org.springframework.stereotype.Component;

@Component
public class PluginsPolicyDefsClientImpl extends AbstractApimanClient implements IPluginsPolicyDefsClient {

	private static final String PLUGIN_POLICY_DEFS_PATH = PLUGINS_PATH + "/${pluginId}/policyDefs";
	private static final String PLUGIN_POLICY_DEFS_FORM_PATH = PLUGIN_POLICY_DEFS_PATH + "/${policyDefId}/form";
	
	/* Use this endpoint to get a list of all policy definitions contributed by the plugin.
	 * 
	 */
	@Override
	public List<PolicyDefinitionSummary> getPluginPolicyDefinitions(Long pluginId) {
		
		String url = buildURL(apimanUrl, PLUGIN_POLICY_DEFS_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("pluginId", String.valueOf(pluginId.longValue()));
		url = substitute(url, map, true);
		
		PolicyDefinitionSummary[] policyDefinitions = restTemplate.getForObject(url, PolicyDefinitionSummary[].class);
		return policyDefinitions != null ? Arrays.asList(policyDefinitions) : null;
	}
	
	/* Use this endpoint to retrieve the form associated with a particular policy definition. Plugins may contribute 
	 * policy definitions to apiman. Part of that contribution *may* include a form for the UI to display when configuring 
	 * an instance of the policy. This endpoint returns this form.
	 */
	@Override
	public String getPluginPolicyForm(Long pluginId, String policyDefId) {
		
		String url = buildURL(apimanUrl, PLUGIN_POLICY_DEFS_FORM_PATH);
		Map<String, String> map = new HashMap<>();
		map.put("pluginId", String.valueOf(pluginId.longValue()));
		map.put("policyDefId", policyDefId);
		url = substitute(url, map, true);
		
		return restTemplate.getForObject(url, String.class);
	}
}
