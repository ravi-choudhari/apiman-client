package org.apiman.client.resources.plugins;

import java.util.List;

import org.apiman.client.domain.summary.PolicyDefinitionSummary;

public interface IPluginsPolicyDefsClient {

	List<PolicyDefinitionSummary> getPluginPolicyDefinitions(String pluginId);

	String getPluginPolicyForm(String pluginId, String policyDefId);

}
