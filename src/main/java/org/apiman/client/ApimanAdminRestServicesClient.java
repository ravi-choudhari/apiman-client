package org.apiman.client;

import java.util.Date;
import java.util.List;

import org.apiman.client.domain.Action;
import org.apiman.client.domain.ActivityList;
import org.apiman.client.domain.Api;
import org.apiman.client.domain.ApiContract;
import org.apiman.client.domain.ApiDefinitionUrl;
import org.apiman.client.domain.ApiKey;
import org.apiman.client.domain.ApiMetrics;
import org.apiman.client.domain.ApiMetricsList;
import org.apiman.client.domain.ApiPolicyList;
import org.apiman.client.domain.ApiStatusList;
import org.apiman.client.domain.ApiVersion;
import org.apiman.client.domain.Client;
import org.apiman.client.domain.ClientContract;
import org.apiman.client.domain.ClientPolicy;
import org.apiman.client.domain.ClientVersion;
import org.apiman.client.domain.CreateApi;
import org.apiman.client.domain.CreateApiVersion;
import org.apiman.client.domain.Endpoint;
import org.apiman.client.domain.Gateway;
import org.apiman.client.domain.GrantMemberships;
import org.apiman.client.domain.Namespace;
import org.apiman.client.domain.Organization;
import org.apiman.client.domain.OrganizationMember;
import org.apiman.client.domain.OrganizationPlan;
import org.apiman.client.domain.PermissionsList;
import org.apiman.client.domain.Plan;
import org.apiman.client.domain.PlanVersion;
import org.apiman.client.domain.Plugin;
import org.apiman.client.domain.Policy;
import org.apiman.client.domain.PolicyDefinition;
import org.apiman.client.domain.ReOrderPolicies;
import org.apiman.client.domain.Role;
import org.apiman.client.domain.SystemStatus;
import org.apiman.client.domain.User;
import org.apiman.client.domain.UserInformation;
import org.apiman.client.domain.search.SearchQuery;
import org.apiman.client.domain.search.SearchResult;
import org.apiman.client.resources.ActionsClient;
import org.apiman.client.resources.CurrentUserClient;
import org.apiman.client.resources.DownloadsClient;
import org.apiman.client.resources.GatewaysClient;
import org.apiman.client.resources.OrganizationsClient;
import org.apiman.client.resources.PermissionsClient;
import org.apiman.client.resources.PluginsClient;
import org.apiman.client.resources.PolicyDefsClient;
import org.apiman.client.resources.RolesClient;
import org.apiman.client.resources.SearchClient;
import org.apiman.client.resources.SystemClient;
import org.apiman.client.resources.UsersClient;
import org.springframework.beans.factory.annotation.Autowired;
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
@Getter(value = AccessLevel.PRIVATE)
@Setter(value = AccessLevel.PRIVATE)
@Slf4j
public class ApimanAdminRestServicesClient {

	@Autowired
	private ActionsClient actionsClient;
	@Autowired
	private CurrentUserClient currentUserClient;
	@Autowired
	private DownloadsClient downloadsClient;
	@Autowired
	private GatewaysClient gatewaysClient;
	@Autowired
	private OrganizationsClient organizationsClient;
	@Autowired
	private PermissionsClient permissionsClient;
	@Autowired
	private PluginsClient pluginsClient;
	@Autowired
	private PolicyDefsClient policyDefsClient;
	@Autowired
	private RolesClient rolesClient;
	@Autowired
	private SearchClient searchClient;
	@Autowired
	private SystemClient systemClient;
	@Autowired
	private UsersClient usersClient;

	public void testGateway(Gateway gateway) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getGatewaysClient().testGateway(gateway);
	}

	public Gateway createGateway(Gateway gateway) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getGatewaysClient().createGateway(gateway);
	}

	public void updateGateway(String gatewayId, Gateway gateway) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getGatewaysClient().updateGateway(gatewayId, gateway);
	}

	public void deleteGateway(String gatewayId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getGatewaysClient().deleteGateway(gatewayId);
	}

	public PermissionsList getUserPermissions(String userId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPermissionsClient().getUserPermissions(userId);
	}

	public Plugin addPlugin(Plugin plugin) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().addPlugin(plugin);
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

	public void updatePolicyDefinition(String policyDefinitionId, PolicyDefinition policyDefinition) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getPolicyDefsClient().updatePolicyDefinition(policyDefinitionId, policyDefinition);
	}

	public void deletePolicyDefinition(String policyDefinitionId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getPolicyDefsClient().deletePolicyDefinition(policyDefinitionId);
	}

	public Role createRole(Role role) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getRolesClient().createRole(role);
	}

	public void updateRoleById(String roleId, Role role) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getRolesClient().updateRoleById(roleId, role);
	}

	public void deleteRoleById(String roleId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getRolesClient().deleteRoleById(roleId);
	}
}
