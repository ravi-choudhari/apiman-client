package org.apiman.client;

import java.util.List;

import org.apiman.client.conditions.ApimanAdminCondition;
import org.apiman.client.domain.gateway.Gateway;
import org.apiman.client.domain.gateway.NewGateway;
import org.apiman.client.domain.gateway.UpdateGateway;
import org.apiman.client.domain.permissions.UserPermissions;
import org.apiman.client.domain.plugin.NewPlugin;
import org.apiman.client.domain.plugin.Plugin;
import org.apiman.client.domain.plugin.PluginSummary;
import org.apiman.client.domain.policydefinition.PolicyDefinition;
import org.apiman.client.domain.policydefinition.UpdatePolicyDefinition;
import org.apiman.client.domain.role.NewRole;
import org.apiman.client.domain.role.Role;
import org.apiman.client.domain.role.UpdateRole;
import org.apiman.client.domain.summary.GatewayTestResult;
import org.apiman.client.resources.IGatewaysClient;
import org.apiman.client.resources.IPermissionsClient;
import org.apiman.client.resources.IPluginsClient;
import org.apiman.client.resources.IPolicyDefsClient;
import org.apiman.client.resources.IRolesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Component
@Conditional(ApimanAdminCondition.class)
@Getter(value = AccessLevel.PRIVATE)
@Setter(value = AccessLevel.PRIVATE)
@Slf4j
public class ApimanAdminRestServicesClient {

	@Autowired
	private IGatewaysClient gatewaysClient;
	@Autowired
	private IPermissionsClient permissionsClient;
	@Autowired
	private IPluginsClient pluginsClient;
	@Autowired
	private IPolicyDefsClient policyDefsClient;
	@Autowired
	private IRolesClient rolesClient;

	public GatewayTestResult testGateway(NewGateway gateway) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getGatewaysClient().testGateway(gateway);
	}

	public Gateway createGateway(NewGateway gateway) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getGatewaysClient().createGateway(gateway);
	}

	public void updateGateway(String gatewayId, UpdateGateway gateway) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getGatewaysClient().updateGateway(gatewayId, gateway);
	}

	public void deleteGateway(String gatewayId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getGatewaysClient().deleteGateway(gatewayId);
	}

	public UserPermissions getUserPermissions(String userId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPermissionsClient().getUserPermissions(userId);
	}

	public Plugin addPlugin(NewPlugin plugin) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().addPlugin(plugin);
	}

	public List<PluginSummary> listAvailablePlugins() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().listAvailablePlugins();
	}
	
	public Plugin getPluginById(String pluginId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().getPluginById(pluginId);
	}

	public void deletePluginById(String pluginId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getPluginsClient().deletePluginById(pluginId);
	}

	public PolicyDefinition addPolicyDefinition(PolicyDefinition policyDefinition) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPolicyDefsClient().addPolicyDefinition(policyDefinition);
	}

	public void updatePolicyDefinition(String policyDefinitionId, UpdatePolicyDefinition policyDefinition) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getPolicyDefsClient().updatePolicyDefinition(policyDefinitionId, policyDefinition);
	}

	public void deletePolicyDefinition(String policyDefinitionId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getPolicyDefsClient().deletePolicyDefinition(policyDefinitionId);
	}

	public Role createRole(NewRole role) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getRolesClient().createRole(role);
	}

	public void updateRoleById(String roleId, UpdateRole role) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getRolesClient().updateRoleById(roleId, role);
	}

	public void deleteRoleById(String roleId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getRolesClient().deleteRoleById(roleId);
	}
	
}
