package org.apiman.client;

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

@Component
public class ApimanRestServicesClient {

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
	
	public String executeEntityAction() {
		
		return actionsClient.executeEntityAction();
	}
	
	public String getApiOrganizations() {
		
		return currentUserClient.getApiOrganizations();
	}
	
	public String getCurrentUserApis() {
		
		return currentUserClient.getCurrentUserApis();
	}
	
	public String getClientOrganizations() {
		
		return currentUserClient.getClientOrganizations();
	}
	
	public String getCurrentUserClients() {
		
		return currentUserClient.getCurrentUserClients();
	}
	
	public String getCurrentUserInformation() {
		
		return currentUserClient.getCurrentUserInformation();
	}
	
	public String updateCurrentUserInformation() {
		
		return currentUserClient.updateCurrentUserInformation();
	}
	
	public String getPlanOrganizations() {
		
		return currentUserClient.getPlanOrganizations();
	}
	
	public String downloadFile() {
		
		return downloadsClient.downloadFile();
	}
	
	public String listAllGateways() {
		
		return gatewaysClient.listAllGateways();
	}
	
	public String testGateway() {
		
		return gatewaysClient.testGateway();
	}
	
	public String createGateway() {
		
		return gatewaysClient.createGateway();
	}

	public String getGatewayById() {
		
		return gatewaysClient.getGatewayById();
	}
	
	public String updateGateway() {
		
		return gatewaysClient.updateGateway();
	}
	
	public String deleteGateway() {
		
		return gatewaysClient.deleteGateway();
	}
	
	public String createOrganization() {
		
		return organizationsClient.createOrganization();
	}
	
	public String getOrganizationById() {
		
		return organizationsClient.getOrganizationById();
	}
	
	public String updateOrganization() {
		
		return organizationsClient.updateOrganization();
	}
	
	public String deleteOrganization() {
		
		return organizationsClient.deleteOrganization();
	}
	
	public String getOrganizationActivity() {
		
		return organizationsClient.getOrganizationActivity();
	}	
	
	public String listApis() {
		
		return organizationsClient.getApisClient().listApis();
	}
	
	public String createApi() {
		
		return organizationsClient.getApisClient().createApi();
	}
	
	public String getApiById() {
		
		return organizationsClient.getApisClient().getApiById();
	}
	
	public String updateApi() {
		
		return organizationsClient.getApisClient().updateApi();
	}
	
	public String deleteApi() {
		
		return organizationsClient.getApisClient().deleteApi();
	}
	
	public String getApiActivity() {
		
		return organizationsClient.getApisClient().getApiActivity();
	}
	
	public String listApiVersions() {
		
		return organizationsClient.getApisClient().listApiVersions();
	}
	
	public String createApiVersion() {
		
		return organizationsClient.getApisClient().createApiVersion();
	}
	
	public String getApiVersion() {
		
		return organizationsClient.getApisClient().getApiVersion();
	}
	
	public String updateApiVersion() {
		
		return organizationsClient.getApisClient().updateApiVersion();
	}
	
	public String getApiVersionActivity() {
		
		return organizationsClient.getApisClient().getApiVersionActivity();
	}
	
	public String listApiContracts() {
		
		return organizationsClient.getApisClient().listApiContracts();
	}
	
	public String getApiDefinition() {
		
		return organizationsClient.getApisClient().getApiDefinition();
	}
	
	public String updateApiDefinition() {
		
		return organizationsClient.getApisClient().updateApiDefinition();
	}
	
	public String updateApiDefinitionFromUrl() {
		
		return organizationsClient.getApisClient().updateApiDefinitionFromUrl();
	}
	
	public String removeApiDefinition() {
		
		return organizationsClient.getApisClient().removeApiDefinition();
	}
	
	public String getApiEndpoint() {
		
		return organizationsClient.getApisClient().getApiEndpoint();
	}
	
	public String reorderApiPolicies() {
		
		return organizationsClient.getApisClient().reorderApiPolicies();
	}

	public String getApiVersionStatus() {
		
		return organizationsClient.getApisClient().getApiVersionStatus();
	}
	
	public String getApiResponseStatisticsPerClient() {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiResponseStatisticsPerClient();
	}
	
	public String getApiUsageMetricsPerClient() {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiUsageMetricsPerClient();
	}
	
	public String getApiResponseStatisticsPerPlan() {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiResponseStatisticsPerPlan();
	}
	
	public String getApiUsageMetricsPerPlan() {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiUsageMetricsPerPlan();
	}
	
	public String getApiResponseStatistics() {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiResponseStatistics();
	}
	
	public String getApiResponseStatisticsSummary() {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiResponseStatisticsSummary();
	}
	
	public String getApiUsageMetrics() {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiUsageMetrics();
	}
	
	public String listApiPlans() {
		
		return organizationsClient.getApisClient().getOrganizationApiPlansClient().listApiPlans();
	}
	
	public String getApiPolicyChain() {
		
		return organizationsClient.getApisClient().getOrganizationApiPlansClient().getApiPolicyChain();
	}
	
	public String listAllApiPolicies() {
		
		return organizationsClient.getApisClient().getOrganizationApiPoliciesClient().listAllApiPolicies();
	}
	
	public String addApiPolicy() {
		
		return organizationsClient.getApisClient().getOrganizationApiPoliciesClient().addApiPolicy();
	}
	
	public String getApiPolicy() {
		
		return organizationsClient.getApisClient().getOrganizationApiPoliciesClient().getApiPolicy();
	}
	
	public String updateApiPolicy() {
		
		return organizationsClient.getApisClient().getOrganizationApiPoliciesClient().updateApiPolicy();
	}
	
	public String removeApiPolicy() {
		
		return organizationsClient.getApisClient().getOrganizationApiPoliciesClient().removeApiPolicy();
	}
	
	public String listClients() {
		
		return organizationsClient.getClientsClient().listClients();
	}
	
	public String createClient() {
		
		return organizationsClient.getClientsClient().createClient();
	}
	
	public String getClientById() {
		
		return organizationsClient.getClientsClient().getClientById();
	}
	
	public String updateClient() {
		
		return organizationsClient.getClientsClient().updateClient();
	}
	
	public String deleteClient() {
		
		return organizationsClient.getClientsClient().deleteClient();
	}
	
	public String getClientActivity() {
		
		return organizationsClient.getClientsClient().getClientActivity();
	}
	
	public String listClientVersions() {
		
		return organizationsClient.getClientsClient().listClientVersions();
	}
	
	public String createClientVersion() {
		
		return organizationsClient.getClientsClient().createClientVersion();
	}
	
	public String getClientVersion() {
		
		return organizationsClient.getClientsClient().getClientVersion();
	}
	
	public String getClientVersionActivity() {
		
		return organizationsClient.getClientsClient().getClientVersionActivity();
	}
	
	public String getApiKey() {
		
		return organizationsClient.getClientsClient().getApiKey();
	}
	
	public String updateApiKey() {
		
		return organizationsClient.getClientsClient().updateApiKey();
	}
	
	public String getClientUsageMetricsPerApi() {
		
		return organizationsClient.getClientsClient().getClientUsageMetricsPerApi();
	}
	
	public String reorderClientPolicies() {
		
		return organizationsClient.getClientsClient().reorderClientPolicies();
	}
	
	public String getJsonApiRegistry() {
		
		return organizationsClient.getClientsClient().getClientsApiRegistryClient().getJsonApiRegistry();
	}
	
	public String getXmlApiRegistry() {
		
		return organizationsClient.getClientsClient().getClientsApiRegistryClient().getXmlApiRegistry();
	}
	
	public String listAllContractsForClient() {
		
		return organizationsClient.getClientsClient().getClientsContractsClient().listAllContractsForClient();
	}
	
	public String creareApiContract() {
		
		return organizationsClient.getClientsClient().getClientsContractsClient().creareApiContract();
	}
	
	public String breakAllContracts() {
		
		return organizationsClient.getClientsClient().getClientsContractsClient().breakAllContracts();
	}
	
	public String getApiContract() {
		
		return organizationsClient.getClientsClient().getClientsContractsClient().getApiContract();
	}
	
	public String breakContract() {
		
		return organizationsClient.getClientsClient().getClientsContractsClient().breakContract();
	}
	
	public String listAllClientPolicies() {
		
		return organizationsClient.getClientsClient().getClientsPoliciesClient().listAllClientPolicies();
	}
	
	public String addClientPolicy() {
		
		return organizationsClient.getClientsClient().getClientsPoliciesClient().addClientPolicy();
	}
	
	public String getClientPolicy() {
		
		return organizationsClient.getClientsClient().getClientsPoliciesClient().getClientPolicy();
	}
	
	public String updateClientPolicy() {
		
		return organizationsClient.getClientsClient().getClientsPoliciesClient().updateClientPolicy();
	}
	
	public String removeClientPolicy() {
		
		return organizationsClient.getClientsClient().getClientsPoliciesClient().removeClientPolicy();
	}
	
	public String listOrganizationMembers() {
		
		return organizationsClient.getMembersClient().listOrganizationMembers();
	}
	
	public String revokeAllMemberships() {
		
		return organizationsClient.getMembersClient().revokeAllMemberships();
	}
	
	public String listPlans() {
		
		return organizationsClient.getPlansClient().listPlans();
	}
	
	public String createPlan() {
		
		return organizationsClient.getPlansClient().createPlan();
	}
	
	public String getPlanById() {
		
		return organizationsClient.getPlansClient().getPlanById();
	}
	
	public String updatePlan() {
		
		return organizationsClient.getPlansClient().updatePlan();
	}
	
	public String deletePlan() {
		
		return organizationsClient.getPlansClient().deletePlan();
	}
	
	public String getPlanActivity() {
		
		return organizationsClient.getPlansClient().getPlanActivity();
	}
	
	public String listPlanVersions() {
		
		return organizationsClient.getPlansClient().listPlanVersions();
	}
	
	public String createPlanVersion() {
		
		return organizationsClient.getPlansClient().createPlanVersion();
	}
	
	public String getPlanVersion() {
		
		return organizationsClient.getPlansClient().getPlanVersion();
	}
	
	public String getPlanVersionActivity() {
		
		return organizationsClient.getPlansClient().getPlanVersionActivity();
	}
	
	public String reorderPlanPolicies() {
		
		return organizationsClient.getPlansClient().reorderPlanPolicies();
	}
	
	public String listAllPlanPolicies() {
		
		return organizationsClient.getPlansClient().getPlansPoliciesClient().listAllPlanPolicies();
	}
	
	public String addPlanPolicy() {
		
		return organizationsClient.getPlansClient().getPlansPoliciesClient().addPlanPolicy();
	}
	
	public String getPlanPolicy() {
		
		return organizationsClient.getPlansClient().getPlansPoliciesClient().getPlanPolicy();
	}
	
	public String updatePlanPolicy() {
		
		return organizationsClient.getPlansClient().getPlansPoliciesClient().updatePlanPolicy();
	}
	
	public String removePlanPolicy() {
		
		return organizationsClient.getPlansClient().getPlansPoliciesClient().removePlanPolicy();
	}

	public String grantMembership() {
		
		return organizationsClient.getRolesClient().grantMembership();
	}
	
	public String revokeSingleMembership() {
		
		return organizationsClient.getRolesClient().revokeSingleMembership();
	}
	
	public String getCurrentUserPermissions() {
		
		return permissionsClient.getCurrentUserPermissions();
	}
	
	public String getUserPermissions() {
		
		return permissionsClient.getUserPermissions();
	}
	
	public String listAllPlugins() {
		
		return pluginsClient.listAllPlugins();
	}
	
	public String addPlugin() {
		
		return pluginsClient.addPlugin();
	}
	
	public String listAvailablePlugins() {
		
		return pluginsClient.listAvailablePlugins();
	}
		
	public String getPluginById() {
		
		return pluginsClient.getPluginById();
	}
	
	public String deletePluginById() {
		
		return pluginsClient.deletePluginById();
	}
	
	public String getPluginPolicyDefinitions() {
		
		return pluginsClient.getPluginsPolicyDefsClient().getPluginPolicyDefinitions();
	}
	
	public String getPluginPolicyForm() {
		
		return pluginsClient.getPluginsPolicyDefsClient().getPluginPolicyForm();
	}
	
	public String listPolicyDefinitions() {
		
		return policyDefsClient.listPolicyDefinitions();
	}
	
	public String addPolicyDefinition() {
		
		return policyDefsClient.addPolicyDefinition();
	}
	
	public String getPolicyDefinitionById() {
		
		return policyDefsClient.getPolicyDefinitionById();
	}

	public String updatePolicyDefinition() {
		
		return policyDefsClient.updatePolicyDefinition();
	}

	public String deletePolicyDefinition() {
		
		return policyDefsClient.deletePolicyDefinition();
	}

	public String listAllRoles() {
		
		return rolesClient.listAllRoles();
	}
	
	public String createRole() {
		
		return rolesClient.createRole();
	}
	
	public String searchForRoles() {
		
		return rolesClient.searchForRoles();
	}
	
	public String getRoleById() {
		
		return rolesClient.getRoleById();
	}

	public String updateRoleById() {
		
		return rolesClient.updateRoleById();
	}

	public String deleteRoleById() {
		
		return rolesClient.deleteRoleById();
	}
	
	public String searchForApis() {
		
		return searchClient.searchForApis();
	}
	
	public String searchForClients() {
		
		return searchClient.searchForClients();
	}
	
	public String searchForOrganizations() {
		
		return searchClient.searchForOrganizations();
	}
	
	public String searchForApisInApiCatalogue() {
		
		return searchClient.getApiCatalogueSearchClient().searchForApisInApiCatalogue();
	}
	
	public String listAllNamespacesInApiCatalogue() {
		
		return searchClient.getApiCatalogueSearchClient().listAllNamespacesInApiCatalogue();
	}

	public String exportData() {
		
		return systemClient.exportData();
	}
	
	public String importData() {
		
		return systemClient.importData();
	}
	
	public String getSystemStatus() {
		
		return systemClient.getSystemStatus();
	}
	
	public String searchForUsers() {
		
		return usersClient.searchForUsers();
	}
	
	public String getUserById() {
		
		return usersClient.getUserById();
	}
	
	public String updateUserById() {
		
		return usersClient.updateUserById();
	}
	
	public String getUserActivity() {
		
		return usersClient.getUserActivity();
	}
	
	public String listUserApis() {
		
		return usersClient.listUserApis();
	}
	
	public String listUserClients() {
		
		return usersClient.listUserClients();
	}
	
	public String listUserOrganizations() {
		
		return usersClient.listUserOrganizations();
	}
}
