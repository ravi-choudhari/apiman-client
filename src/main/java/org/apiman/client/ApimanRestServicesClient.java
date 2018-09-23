package org.apiman.client;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Component
@Getter(value = AccessLevel.PRIVATE)
@Setter(value = AccessLevel.PRIVATE)
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
	
	public void executeEntityAction(Action action) {
		
		actionsClient.executeEntityAction(action);
	}
	
	public List<Organization> getApiOrganizations() {
		
		return currentUserClient.getApiOrganizations();
	}
	
	public List<Api> getCurrentUserApis() {
		
		return currentUserClient.getCurrentUserApis();
	}
	
	public List<Organization> getClientOrganizations() {
		
		return currentUserClient.getClientOrganizations();
	}
	
	public List<Client> getCurrentUserClients() {
		
		return currentUserClient.getCurrentUserClients();
	}
	
	public User getCurrentUserInformation() {
		
		return currentUserClient.getCurrentUserInformation();
	}
	
	public void updateCurrentUserInformation(UserInformation userInformation) {
		
		currentUserClient.updateCurrentUserInformation(userInformation);
	}
	
	public List<Organization> getPlanOrganizations() {
		
		return currentUserClient.getPlanOrganizations();
	}
	
	public String downloadFile(String downloadId) {
		
		return downloadsClient.downloadFile(downloadId);
	}
	
	public List<Gateway> listAllGateways() {
		
		return gatewaysClient.listAllGateways();
	}
	
	public void testGateway(Gateway gateway) {
		
		gatewaysClient.testGateway(gateway);
	}
	
	public Gateway createGateway(Gateway gateway) {
		
		return gatewaysClient.createGateway(gateway);
	}

	public Gateway getGatewayById(String gatewayId) {
		
		return gatewaysClient.getGatewayById(gatewayId);
	}
	
	public void updateGateway(String gatewayId, Gateway gateway) {
		
		gatewaysClient.updateGateway(gatewayId, gateway);
	}
	
	public void deleteGateway(String gatewayId) {
		
		gatewaysClient.deleteGateway(gatewayId);
	}
	
	public Organization createOrganization(Organization organization) {
		
		return organizationsClient.createOrganization(organization);
	}
	
	public Organization getOrganizationById(String organizationId) {
		
		return organizationsClient.getOrganizationById(organizationId);
	}
	
	public void updateOrganization(String organizationId, Organization organization) {
		
		organizationsClient.updateOrganization(organizationId, organization);
	}
	
	public void deleteOrganization(String organizationId) {
		
		organizationsClient.deleteOrganization(organizationId);
	}
	
	public ActivityList getOrganizationActivity(String organizationId, int page, int count) {
		
		return organizationsClient.getOrganizationActivity(organizationId, page, count);
	}	
	
	public List<Api> listApis(String organizationId) {
		
		return organizationsClient.getApisClient().listApis(organizationId);
	}
	
	public Api createApi(String organizationId, CreateApi createApi) {
		
		return organizationsClient.getApisClient().createApi(organizationId, createApi);
	}
	
	public Api getApiById(String organizationId, String apiId) {
		
		return organizationsClient.getApisClient().getApiById(organizationId, apiId);
	}
	
	public void updateApi(String organizationId, String apiId, Api api) {
		
		organizationsClient.getApisClient().updateApi(organizationId, apiId, api);
	}
	
	public void deleteApi(String organizationId, String apiId) {
		
		organizationsClient.getApisClient().deleteApi(organizationId, apiId);
	}
	
	public ActivityList getApiActivity(String organizationId, String apiId, int page, int count) {
		
		return organizationsClient.getApisClient().getApiActivity(organizationId, apiId, page, count);
	}
	
	public List<ApiVersion> listApiVersions(String organizationId, String apiId) {
		
		return organizationsClient.getApisClient().listApiVersions(organizationId, apiId);
	}
	
	public CreateApiVersion createApiVersion(String organizationId, String apiId, CreateApiVersion createApiVersion) {
		
		return organizationsClient.getApisClient().createApiVersion(organizationId, apiId, createApiVersion);
	}
	
	public CreateApiVersion getApiVersion(String organizationId, String apiId, String version) {
		
		return organizationsClient.getApisClient().getApiVersion(organizationId, apiId, version);
	}
	
	public CreateApiVersion updateApiVersion(String organizationId, String apiId, String version, CreateApiVersion createApiVersion) {
		
		return organizationsClient.getApisClient().updateApiVersion(organizationId, apiId, version, createApiVersion);
	}
	
	public ActivityList getApiVersionActivity(String organizationId, String apiId, String version, int page, int count) {
		
		return organizationsClient.getApisClient().getApiVersionActivity(organizationId, apiId, version, page, count);
	}
	
	public List<ApiContract> listApiContracts(String organizationId, String apiId, String version, int page, int count) {
		
		return organizationsClient.getApisClient().listApiContracts(organizationId, apiId, version, page, count);
	}
	
	public String getApiDefinition(String organizationId, String apiId, String version) {
		
		return organizationsClient.getApisClient().getApiDefinition(organizationId, apiId, version);
	}
	
	public void updateApiDefinition(String organizationId, String apiId, String version, String apiDefinition) {
		
		organizationsClient.getApisClient().updateApiDefinition(organizationId, apiId, version, apiDefinition);
	}
	
	public void updateApiDefinitionFromUrl(String organizationId, String apiId, String version, ApiDefinitionUrl apiDefinitionUrl) {
		
		organizationsClient.getApisClient().updateApiDefinitionFromUrl(organizationId, apiId, version, apiDefinitionUrl);
	}
	
	public void removeApiDefinition(String organizationId, String apiId, String version) {
		
		organizationsClient.getApisClient().removeApiDefinition(organizationId, apiId, version);
	}
	
	public Endpoint getApiEndpoint(String organizationId, String apiId, String version) {
		
		return organizationsClient.getApisClient().getApiEndpoint(organizationId, apiId, version);
	}
	
	public void reOrderApiPolicies(String organizationId, String apiId, String version, ReOrderPolicies reOrderPolicies) {
		
		organizationsClient.getApisClient().reOrderApiPolicies(organizationId, apiId, version, reOrderPolicies);
	}

	public ApiStatusList getApiVersionStatus(String organizationId, String apiId, String version) {
		
		return organizationsClient.getApisClient().getApiVersionStatus(organizationId, apiId, version);
	}
	
	public ApiMetrics getApiResponseStatisticsPerClient(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiResponseStatisticsPerClient(organizationId, apiId, version, fromDate, toDate);
	}
	
	public ApiMetrics getApiUsageMetricsPerClient(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiUsageMetricsPerClient(organizationId, apiId, version, fromDate, toDate);
	}
	
	public ApiMetrics getApiResponseStatisticsPerPlan(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiResponseStatisticsPerPlan(organizationId, apiId, version, fromDate, toDate);
	}
	
	public ApiMetrics getApiUsageMetricsPerPlan(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiUsageMetricsPerPlan(organizationId, apiId, version, fromDate, toDate);
	}
	
	public ApiMetrics getApiResponseStatistics(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiResponseStatistics(organizationId, apiId, version, fromDate, toDate);
	}
	
	public ApiMetricsList getApiResponseStatisticsSummary(String organizationId, String apiId, String version, String fromDate, String toDate, String interval) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiResponseStatisticsSummary(organizationId, apiId, version, fromDate, toDate, interval);
	}
	
	public ApiMetricsList getApiUsageMetrics(String organizationId, String apiId, String version, String fromDate, String toDate, String interval) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiUsageMetrics(organizationId, apiId, version, fromDate, toDate, interval);
	}
	
	public List<Plan> listApiPlans(String organizationId, String apiId, String version) {
		
		return organizationsClient.getApisClient().getOrganizationApiPlansClient().listApiPlans(organizationId, apiId, version);
	}
	
	public ApiPolicyList getApiPolicyChain(String organizationId, String apiId, String version, String planId) {
		
		return organizationsClient.getApisClient().getOrganizationApiPlansClient().getApiPolicyChain(organizationId, apiId, version, planId);
	}
	
	public List<Policy> listAllApiPolicies(String organizationId, String apiId, String version) {
		
		return organizationsClient.getApisClient().getOrganizationApiPoliciesClient().listAllApiPolicies(organizationId, apiId, version);
	}
	
	public Policy addApiPolicy(String organizationId, String apiId, String version, Policy apiPolicy) {
		
		return organizationsClient.getApisClient().getOrganizationApiPoliciesClient().addApiPolicy(organizationId, apiId, version, apiPolicy);
	}
	
	public Policy getApiPolicy(String organizationId, String apiId, String version, String policyId) {
		
		return organizationsClient.getApisClient().getOrganizationApiPoliciesClient().getApiPolicy(organizationId, apiId, version, policyId);
	}
	
	public void updateApiPolicy(String organizationId, String apiId, String version, String policyId, Policy apiPolicy) {
		
		organizationsClient.getApisClient().getOrganizationApiPoliciesClient().updateApiPolicy(organizationId, apiId, version, policyId, apiPolicy);
	}
	
	public void removeApiPolicy(String organizationId, String apiId, String version, String policyId) {
		
		organizationsClient.getApisClient().getOrganizationApiPoliciesClient().removeApiPolicy(organizationId, apiId, version, policyId);
	}
	
	public List<Client> listClients(String organizationId) {
		
		return organizationsClient.getClientsClient().listClients(organizationId);
	}
	
	public Client createClient(String organizationId, Client client) {
		
		return organizationsClient.getClientsClient().createClient(organizationId, client);
	}
	
	public Client getClientById(String organizationId, String clientId) {
		
		return organizationsClient.getClientsClient().getClientById(organizationId, clientId);
	}
	
	public void updateClient(String organizationId, String clientId, Client client) {
		
		organizationsClient.getClientsClient().updateClient(organizationId, clientId, client);
	}
	
	public void deleteClient(String organizationId, String clientId) {
		
		organizationsClient.getClientsClient().deleteClient(organizationId, clientId);
	}
	
	public ActivityList getClientActivity(String organizationId, String clientId, int page, int count) {
		
		return organizationsClient.getClientsClient().getClientActivity(organizationId, clientId, page, count);
	}
	
	public List<ClientVersion> listClientVersions(String organizationId, String clientId) {
		
		return organizationsClient.getClientsClient().listClientVersions(organizationId, clientId);
	}
	
	public ClientVersion createClientVersion(String organizationId, String clientId, ClientVersion clientVersion) {
		
		return organizationsClient.getClientsClient().createClientVersion(organizationId, clientId, clientVersion);
	}
	
	public ClientVersion getClientVersion(String organizationId, String clientId, String version) {
		
		return organizationsClient.getClientsClient().getClientVersion(organizationId, clientId, version);
	}
	
	public ActivityList getClientVersionActivity(String organizationId, String clientId, String version) {
		
		return organizationsClient.getClientsClient().getClientVersionActivity(organizationId, clientId, version);
	}
	
	public ApiKey getApiKey(String organizationId, String clientId, String version) {
		
		return organizationsClient.getClientsClient().getApiKey(organizationId, clientId, version);
	}
	
	public ApiKey updateApiKey(String organizationId, String clientId, String version, ApiKey apiKey) {
		
		return organizationsClient.getClientsClient().updateApiKey(organizationId, clientId, version, apiKey);
	}
	
	public String getClientUsageMetricsPerApi() {
		
		return organizationsClient.getClientsClient().getClientUsageMetricsPerApi();
	}
	
	public void reorderClientPolicies(String organizationId, String clientId, String version, ReOrderPolicies reOrderPolicies) {
		
		organizationsClient.getClientsClient().reorderClientPolicies(organizationId, clientId, version, reOrderPolicies);
	}
	
	public String getJsonApiRegistry(String organizationId, String clientId, String version, String download) {
		
		return organizationsClient.getClientsClient().getClientsApiRegistryClient().getJsonApiRegistry(organizationId, clientId, version, download);
	}
	
	public String getXmlApiRegistry(String organizationId, String clientId, String version, String download) {
		
		return organizationsClient.getClientsClient().getClientsApiRegistryClient().getXmlApiRegistry(organizationId, clientId, version, download);
	}
	
	public List<ClientContract> listAllContractsForClient(String organizationId, String clientId, String version) {
		
		return organizationsClient.getClientsClient().getClientsContractsClient().listAllContractsForClient(organizationId, clientId, version);
	}
	
	public ClientContract creareApiContract(String organizationId, String clientId, String version, ClientContract apiContract) {
		
		return organizationsClient.getClientsClient().getClientsContractsClient().creareApiContract(organizationId, clientId, version, apiContract);
	}
	
	public void breakAllContracts(String organizationId, String clientId, String version) {
		
		organizationsClient.getClientsClient().getClientsContractsClient().breakAllContracts(organizationId, clientId, version);
	}
	
	public ClientContract getApiContract(String organizationId, String clientId, String version, String contractId) {
		
		return organizationsClient.getClientsClient().getClientsContractsClient().getApiContract(organizationId, clientId, version, contractId);
	}
	
	public void breakContract(String organizationId, String clientId, String version, String contractId) {
		
		organizationsClient.getClientsClient().getClientsContractsClient().breakContract(organizationId, clientId, version, contractId);
	}
	
	public List<ClientPolicy> listAllClientPolicies(String organizationId, String clientId, String version) {
		
		return organizationsClient.getClientsClient().getClientsPoliciesClient().listAllClientPolicies(organizationId, clientId, version);
	}
	
	public ClientPolicy addClientPolicy(String organizationId, String clientId, String version, ClientPolicy clientPolicy) {
		
		return organizationsClient.getClientsClient().getClientsPoliciesClient().addClientPolicy(organizationId, clientId, version, clientPolicy);
	}
	
	public ClientPolicy getClientPolicy(String organizationId, String clientId, String version, String policyId) {
		
		return organizationsClient.getClientsClient().getClientsPoliciesClient().getClientPolicy(organizationId, clientId, version, policyId);
	}
	
	public void updateClientPolicy(String organizationId, String clientId, String version, String policyId, ClientPolicy clientPolicy) {
		
		organizationsClient.getClientsClient().getClientsPoliciesClient().updateClientPolicy(organizationId, clientId, version, policyId, clientPolicy);
	}
	
	public void removeClientPolicy(String organizationId, String clientId, String version, String policyId) {
		
		organizationsClient.getClientsClient().getClientsPoliciesClient().removeClientPolicy(organizationId, clientId, version, policyId);
	}
	
	public List<OrganizationMember> listOrganizationMembers(String organizationId) {
		
		return organizationsClient.getMembersClient().listOrganizationMembers(organizationId);
	}
	
	public void revokeAllMemberships(String organizationId, String userId) {
		
		organizationsClient.getMembersClient().revokeAllMemberships(organizationId, userId);
	}
	
	public List<OrganizationPlan> listPlans(String organizationId) {
		
		return organizationsClient.getPlansClient().listPlans(organizationId);
	}
	
	public OrganizationPlan createPlan(String organizationId, OrganizationPlan organizationPlan) {
		
		return organizationsClient.getPlansClient().createPlan(organizationId, organizationPlan);
	}
	
	public OrganizationPlan getPlanById(String organizationId, String planId) {
		
		return organizationsClient.getPlansClient().getPlanById(organizationId, planId);
	}
	
	public void updatePlan(String organizationId, String planId, OrganizationPlan organizationPlan) {
		
		organizationsClient.getPlansClient().updatePlan(organizationId, planId, organizationPlan);
	}
	
	public void deletePlan(String organizationId, String planId) {
		
		organizationsClient.getPlansClient().deletePlan(organizationId, planId);
	}
	
	public ActivityList getPlanActivity(String organizationId, String planId, int page, int count) {
		
		return organizationsClient.getPlansClient().getPlanActivity(organizationId, planId, page, count);
	}
	
	public List<PlanVersion> listPlanVersions(String organizationId, String planId) {
		
		return organizationsClient.getPlansClient().listPlanVersions(organizationId, planId);
	}
	
	public PlanVersion createPlanVersion(String organizationId, String planId, PlanVersion planVersion) {
		
		return organizationsClient.getPlansClient().createPlanVersion(organizationId, planId, planVersion);
	}
	
	public PlanVersion getPlanVersion(String organizationId, String planId, String version) {
		
		return organizationsClient.getPlansClient().getPlanVersion(organizationId, planId, version);
	}
	
	public ActivityList getPlanVersionActivity(String organizationId, String planId, String version, int page, int count) {
		
		return organizationsClient.getPlansClient().getPlanVersionActivity(organizationId, planId, version, page, count);
	}
	
	public void reorderPlanPolicies(String organizationId, String planId, String version, ReOrderPolicies reOrderPolicies) {
		
		organizationsClient.getPlansClient().reorderPlanPolicies(organizationId, planId, version, reOrderPolicies);
	}
	
	public List<Policy> listAllPlanPolicies(String organizationId, String planId, String version) {
		
		return organizationsClient.getPlansClient().getPlansPoliciesClient().listAllPlanPolicies(organizationId, planId, version);
	}
	
	public Policy addPlanPolicy(String organizationId, String planId, String version, Policy planPolicy) {
		
		return organizationsClient.getPlansClient().getPlansPoliciesClient().addPlanPolicy(organizationId, planId, version, planPolicy);
	}
	
	public Policy getPlanPolicy(String organizationId, String planId, String version, String policyId) {
		
		return organizationsClient.getPlansClient().getPlansPoliciesClient().getPlanPolicy(organizationId, planId, version, policyId);
	}
	
	public void updatePlanPolicy(String organizationId, String planId, String version, String policyId, Policy planPolicy) {
		
		organizationsClient.getPlansClient().getPlansPoliciesClient().updatePlanPolicy(organizationId, planId, version, policyId, planPolicy);
	}
	
	public void removePlanPolicy(String organizationId, String planId, String version, String policyId) {
		
		organizationsClient.getPlansClient().getPlansPoliciesClient().removePlanPolicy(organizationId, planId, version, policyId);
	}

	public void grantMembership(String organizationId, GrantMemberships grantMemberships) {
		
		organizationsClient.getRolesClient().grantMembership(organizationId, grantMemberships);
	}
	
	public void revokeSingleMembership(String organizationId, String roleId, String userId) {
		
		organizationsClient.getRolesClient().revokeSingleMembership(organizationId, roleId, userId);
	}
	
	public PermissionsList getCurrentUserPermissions() {
		
		return permissionsClient.getCurrentUserPermissions();
	}
	
	public PermissionsList getUserPermissions(String userId) {
		
		return permissionsClient.getUserPermissions(userId);
	}
	
	public List<Plugin> listAllPlugins() {
		
		return pluginsClient.listAllPlugins();
	}
	
	public Plugin addPlugin(Plugin plugin) {
		
		return pluginsClient.addPlugin(plugin);
	}
	
	public List<Plugin> listAvailablePlugins() {
		
		return pluginsClient.listAvailablePlugins();
	}
		
	public Plugin getPluginById(String pluginId) {
		
		return pluginsClient.getPluginById(pluginId);
	}
	
	public void deletePluginById(String pluginId) {
		
		pluginsClient.deletePluginById(pluginId);
	}
	
	public List<PolicyDefinition> getPluginPolicyDefinitions(String pluginId) {
		
		return pluginsClient.getPluginsPolicyDefsClient().getPluginPolicyDefinitions(pluginId);
	}
	
	public String getPluginPolicyForm(String pluginId, String policyDefId) {
		
		return pluginsClient.getPluginsPolicyDefsClient().getPluginPolicyForm(pluginId, policyDefId);
	}
	
	public List<PolicyDefinition> listPolicyDefinitions() {
		
		return policyDefsClient.listPolicyDefinitions();
	}
	
	public PolicyDefinition addPolicyDefinition(PolicyDefinition policyDefinition) {
		
		return policyDefsClient.addPolicyDefinition(policyDefinition);
	}
	
	public PolicyDefinition getPolicyDefinitionById(String policyDefinitionId) {
		
		return policyDefsClient.getPolicyDefinitionById(policyDefinitionId);
	}

	public void updatePolicyDefinition(String policyDefinitionId, PolicyDefinition policyDefinition) {
		
		policyDefsClient.updatePolicyDefinition(policyDefinitionId, policyDefinition);
	}

	public void deletePolicyDefinition(String policyDefinitionId) {
		
		policyDefsClient.deletePolicyDefinition(policyDefinitionId);
	}

	public List<Role> listAllRoles() {
		
		return rolesClient.listAllRoles();
	}
	
	public Role createRole(Role role) {
		
		return rolesClient.createRole(role);
	}
	
	public SearchResult searchForRoles(SearchQuery rolesSearchQuery) {
		
		return rolesClient.searchForRoles(rolesSearchQuery);
	}
	
	public Role getRoleById(String roleId) {
		
		return rolesClient.getRoleById(roleId);
	}

	public void updateRoleById(String roleId, Role role) {
		
		rolesClient.updateRoleById(roleId, role);
	}

	public void deleteRoleById(String roleId) {
		
		rolesClient.deleteRoleById(roleId);
	}
	
	public SearchResult searchForApis(SearchQuery apisSearchQuery) {
		
		return searchClient.searchForApis(apisSearchQuery);
	}
	
	public SearchResult searchForClients(SearchQuery clientsSearchQuery) {
		
		return searchClient.searchForClients(clientsSearchQuery);
	}
	
	public SearchResult searchForOrganizations(SearchQuery organizationsSearchQuery) {
		
		return searchClient.searchForOrganizations(organizationsSearchQuery);
	}
	
	public SearchResult searchForApisInApiCatalogue() {
		
		return searchClient.getApiCatalogueSearchClient().searchForApisInApiCatalogue();
	}
	
	public List<Namespace> listAllNamespacesInApiCatalogue() {
		
		return searchClient.getApiCatalogueSearchClient().listAllNamespacesInApiCatalogue();
	}

	public void exportData(String download) {
		
		systemClient.exportData(download);
	}
	
	public void importData() {
		
		systemClient.importData();
	}
	
	public SystemStatus getSystemStatus() {
		
		return systemClient.getSystemStatus();
	}
	
	public SearchResult searchForUsers(SearchQuery userSearchQuery) {
		
		return usersClient.searchForUsers(userSearchQuery);
	}
	
	public User getUserById(String userId) {
		
		return usersClient.getUserById(userId);
	}
	
	public void updateUserById(String userId, User user) {
		
		usersClient.updateUserById(userId, user);
	}
	
	public ActivityList getUserActivity(String userId, int page, int count) {
		
		return usersClient.getUserActivity(userId, page, count);
	}
	
	public List<Api> listUserApis(String userId) {
		
		return usersClient.listUserApis(userId);
	}
	
	public List<Client> listUserClients(String userId) {
		
		return usersClient.listUserClients(userId);
	}
	
	public List<Organization> listUserOrganizations(String userId) {
		
		return usersClient.listUserOrganizations(userId);
	}
}
