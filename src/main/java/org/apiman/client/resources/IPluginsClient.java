package org.apiman.client.resources;

import java.util.List;

import org.apiman.client.domain.plugin.NewPlugin;
import org.apiman.client.domain.plugin.Plugin;
import org.apiman.client.domain.plugin.PluginSummary;
import org.apiman.client.resources.plugins.IPluginsPolicyDefsClient;

public interface IPluginsClient {

	Plugin addPlugin(NewPlugin plugin);

	Plugin getPluginById(Long pluginId);

	void deletePluginById(Long pluginId);

	List<PluginSummary> listAllPlugins();

	List<PluginSummary> listAvailablePlugins();

	IPluginsPolicyDefsClient getPluginsPolicyDefsClient();
}
