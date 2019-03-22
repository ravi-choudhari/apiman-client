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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Component
@Getter(value = AccessLevel.PRIVATE)
@Setter(value = AccessLevel.PRIVATE)
// @Log4j
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
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getActionsClient().executeEntityAction(action);
	}

	public List<Organization> getApiOrganizations() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getCurrentUserClient().getApiOrganizations();
	}

	public List<Api> getCurrentUserApis() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getCurrentUserClient().getCurrentUserApis();
	}

	public List<Organization> getClientOrganizations() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getCurrentUserClient().getClientOrganizations();
	}

	public List<Client> getCurrentUserClients() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getCurrentUserClient().getCurrentUserClients();
	}

	public User getCurrentUserInformation() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getCurrentUserClient().getCurrentUserInformation();
	}

	public void updateCurrentUserInformation(UserInformation userInformation) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getCurrentUserClient().updateCurrentUserInformation(userInformation);
	}

	public List<Organization> getPlanOrganizations() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getCurrentUserClient().getPlanOrganizations();
	}

	public String downloadFile(String downloadId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getDownloadsClient().downloadFile(downloadId);
	}

	public List<Gateway> listAllGateways() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getGatewaysClient().listAllGateways();
	}

	public void testGateway(Gateway gateway) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getGatewaysClient().testGateway(gateway);
	}

	public Gateway createGateway(Gateway gateway) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getGatewaysClient().createGateway(gateway);
	}

	public Gateway getGatewayById(String gatewayId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getGatewaysClient().getGatewayById(gatewayId);
	}

	public void updateGateway(String gatewayId, Gateway gateway) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getGatewaysClient().updateGateway(gatewayId, gateway);
	}

	public void deleteGateway(String gatewayId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getGatewaysClient().deleteGateway(gatewayId);
	}

	public Organization createOrganization(Organization organization) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().createOrganization(organization);
	}

	public Organization getOrganizationById(String organizationId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getOrganizationById(organizationId);
	}

	public void updateOrganization(String organizationId, Organization organization) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().updateOrganization(organizationId, organization);
	}

	public void deleteOrganization(String organizationId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().deleteOrganization(organizationId);
	}

	public ActivityList getOrganizationActivity(String organizationId, int page, int count) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getOrganizationActivity(organizationId, page, count);
	}

	public List<Api> listApis(String organizationId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().listApis(organizationId);
	}

	public Api createApi(String organizationId, CreateApi createApi) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().createApi(organizationId, createApi);
	}

	public Api getApiById(String organizationId, String apiId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiById(organizationId, apiId);
	}

	public void updateApi(String organizationId, String apiId, Api api) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().updateApi(organizationId, apiId, api);
	}

	public void deleteApi(String organizationId, String apiId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().deleteApi(organizationId, apiId);
	}

	public ActivityList getApiActivity(String organizationId, String apiId, int page, int count) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiActivity(organizationId, apiId, page, count);
	}

	public List<ApiVersion> listApiVersions(String organizationId, String apiId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().listApiVersions(organizationId, apiId);
	}

	public CreateApiVersion createApiVersion(String organizationId, String apiId, CreateApiVersion createApiVersion) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().createApiVersion(organizationId, apiId, createApiVersion);
	}

	public CreateApiVersion getApiVersion(String organizationId, String apiId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiVersion(organizationId, apiId, version);
	}

	public CreateApiVersion updateApiVersion(String organizationId, String apiId, String version,
			CreateApiVersion createApiVersion) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().updateApiVersion(organizationId, apiId, version,
				createApiVersion);
	}

	public ActivityList getApiVersionActivity(String organizationId, String apiId, String version, int page,
			int count) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiVersionActivity(organizationId, apiId, version, page,
				count);
	}

	public List<ApiContract> listApiContracts(String organizationId, String apiId, String version, int page,
			int count) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().listApiContracts(organizationId, apiId, version, page, count);
	}

	public String getApiDefinition(String organizationId, String apiId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiDefinition(organizationId, apiId, version);
	}

	public void updateApiDefinition(String organizationId, String apiId, String version, String apiDefinition) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().updateApiDefinition(organizationId, apiId, version, apiDefinition);
	}

	public void updateApiDefinitionFromUrl(String organizationId, String apiId, String version,
			ApiDefinitionUrl apiDefinitionUrl) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().updateApiDefinitionFromUrl(organizationId, apiId, version,
				apiDefinitionUrl);
	}

	public void removeApiDefinition(String organizationId, String apiId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().removeApiDefinition(organizationId, apiId, version);
	}

	public Endpoint getApiEndpoint(String organizationId, String apiId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiEndpoint(organizationId, apiId, version);
	}

	public void reOrderApiPolicies(String organizationId, String apiId, String version,
			ReOrderPolicies reOrderPolicies) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().reOrderApiPolicies(organizationId, apiId, version, reOrderPolicies);
	}

	public ApiStatusList getApiVersionStatus(String organizationId, String apiId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiVersionStatus(organizationId, apiId, version);
	}

	public ApiMetrics getApiResponseStatisticsPerClient(String organizationId, String apiId, String version,
			Date fromDate, Date toDate) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiResponseStatisticsPerClient(organizationId, apiId, version, fromDate, toDate);
	}

	public ApiMetrics getApiUsageMetricsPerClient(String organizationId, String apiId, String version, Date fromDate,
			Date toDate) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiUsageMetricsPerClient(organizationId, apiId, version, fromDate, toDate);
	}

	public ApiMetrics getApiResponseStatisticsPerPlan(String organizationId, String apiId, String version,
			Date fromDate, Date toDate) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiResponseStatisticsPerPlan(organizationId, apiId, version, fromDate, toDate);
	}

	public ApiMetrics getApiUsageMetricsPerPlan(String organizationId, String apiId, String version, Date fromDate,
			Date toDate) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiUsageMetricsPerPlan(organizationId, apiId, version, fromDate, toDate);
	}

	public ApiMetrics getApiResponseStatistics(String organizationId, String apiId, String version, Date fromDate,
			Date toDate) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiResponseStatistics(organizationId, apiId, version, fromDate, toDate);
	}

	public ApiMetricsList getApiResponseStatisticsSummary(String organizationId, String apiId, String version,
			Date fromDate, Date toDate, String interval) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiResponseStatisticsSummary(organizationId, apiId, version, fromDate, toDate, interval);
	}

	public ApiMetricsList getApiUsageMetrics(String organizationId, String apiId, String version, Date fromDate,
			Date toDate, String interval) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiUsageMetrics(organizationId, apiId, version, fromDate, toDate, interval);
	}

	public List<Plan> listApiPlans(String organizationId, String apiId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiPlansClient().listApiPlans(organizationId,
				apiId, version);
	}

	public ApiPolicyList getApiPolicyChain(String organizationId, String apiId, String version, String planId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiPlansClient()
				.getApiPolicyChain(organizationId, apiId, version, planId);
	}

	public List<Policy> listAllApiPolicies(String organizationId, String apiId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiPoliciesClient()
				.listAllApiPolicies(organizationId, apiId, version);
	}

	public Policy addApiPolicy(String organizationId, String apiId, String version, Policy apiPolicy) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiPoliciesClient().addApiPolicy(organizationId,
				apiId, version, apiPolicy);
	}

	public Policy getApiPolicy(String organizationId, String apiId, String version, String policyId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiPoliciesClient().getApiPolicy(organizationId,
				apiId, version, policyId);
	}

	public void updateApiPolicy(String organizationId, String apiId, String version, String policyId,
			Policy apiPolicy) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().getOrganizationApiPoliciesClient().updateApiPolicy(organizationId,
				apiId, version, policyId, apiPolicy);
	}

	public void removeApiPolicy(String organizationId, String apiId, String version, String policyId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().getOrganizationApiPoliciesClient().removeApiPolicy(organizationId,
				apiId, version, policyId);
	}

	public List<Client> listClients(String organizationId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().listClients(organizationId);
	}

	public Client createClient(String organizationId, Client client) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().createClient(organizationId, client);
	}

	public Client getClientById(String organizationId, String clientId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientById(organizationId, clientId);
	}

	public void updateClient(String organizationId, String clientId, Client client) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().updateClient(organizationId, clientId, client);
	}

	public void deleteClient(String organizationId, String clientId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().deleteClient(organizationId, clientId);
	}

	public ActivityList getClientActivity(String organizationId, String clientId, int page, int count) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientActivity(organizationId, clientId, page, count);
	}

	public List<ClientVersion> listClientVersions(String organizationId, String clientId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().listClientVersions(organizationId, clientId);
	}

	public ClientVersion createClientVersion(String organizationId, String clientId, ClientVersion clientVersion) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().createClientVersion(organizationId, clientId, clientVersion);
	}

	public ClientVersion getClientVersion(String organizationId, String clientId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientVersion(organizationId, clientId, version);
	}

	public ActivityList getClientVersionActivity(String organizationId, String clientId, String version, int page, int count) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientVersionActivity(organizationId, clientId, version, page, count);
	}

	public ApiKey getApiKey(String organizationId, String clientId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getApiKey(organizationId, clientId, version);
	}

	public ApiKey updateApiKey(String organizationId, String clientId, String version, ApiKey apiKey) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().updateApiKey(organizationId, clientId, version, apiKey);
	}

	public ApiMetrics getClientUsageMetricsPerApi(String organizationId, String clientId, String version, Date fromDate,
			Date toDate) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientUsageMetricsPerApi(organizationId, clientId,
				version, fromDate, toDate);
	}

	public void reorderClientPolicies(String organizationId, String clientId, String version,
			ReOrderPolicies reOrderPolicies) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().reorderClientPolicies(organizationId, clientId, version,
				reOrderPolicies);
	}

	public String getJsonApiRegistry(String organizationId, String clientId, String version, String download) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsApiRegistryClient()
				.getJsonApiRegistry(organizationId, clientId, version, download);
	}

	public String getXmlApiRegistry(String organizationId, String clientId, String version, String download) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsApiRegistryClient()
				.getXmlApiRegistry(organizationId, clientId, version, download);
	}

	public List<ClientContract> listAllContractsForClient(String organizationId, String clientId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsContractsClient()
				.listAllContractsForClient(organizationId, clientId, version);
	}

	public ClientContract creareApiContract(String organizationId, String clientId, String version,
			ClientContract apiContract) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsContractsClient().creareApiContract(organizationId,
				clientId, version, apiContract);
	}

	public void breakAllContracts(String organizationId, String clientId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().getClientsContractsClient().breakAllContracts(organizationId,
				clientId, version);
	}

	public ClientContract getApiContract(String organizationId, String clientId, String version, String contractId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsContractsClient().getApiContract(organizationId,
				clientId, version, contractId);
	}

	public void breakContract(String organizationId, String clientId, String version, String contractId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().getClientsContractsClient().breakContract(organizationId, clientId,
				version, contractId);
	}

	public List<ClientPolicy> listAllClientPolicies(String organizationId, String clientId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsPoliciesClient()
				.listAllClientPolicies(organizationId, clientId, version);
	}

	public ClientPolicy addClientPolicy(String organizationId, String clientId, String version,
			ClientPolicy clientPolicy) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsPoliciesClient().addClientPolicy(organizationId,
				clientId, version, clientPolicy);
	}

	public ClientPolicy getClientPolicy(String organizationId, String clientId, String version, String policyId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsPoliciesClient().getClientPolicy(organizationId,
				clientId, version, policyId);
	}

	public void updateClientPolicy(String organizationId, String clientId, String version, String policyId,
			ClientPolicy clientPolicy) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().getClientsPoliciesClient().updateClientPolicy(organizationId,
				clientId, version, policyId, clientPolicy);
	}

	public void removeClientPolicy(String organizationId, String clientId, String version, String policyId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().getClientsPoliciesClient().removeClientPolicy(organizationId,
				clientId, version, policyId);
	}

	public List<OrganizationMember> listOrganizationMembers(String organizationId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getMembersClient().listOrganizationMembers(organizationId);
	}

	public void revokeAllMemberships(String organizationId, String userId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getMembersClient().revokeAllMemberships(organizationId, userId);
	}

	public List<OrganizationPlan> listPlans(String organizationId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().listPlans(organizationId);
	}

	public OrganizationPlan createPlan(String organizationId, OrganizationPlan organizationPlan) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().createPlan(organizationId, organizationPlan);
	}

	public OrganizationPlan getPlanById(String organizationId, String planId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlanById(organizationId, planId);
	}

	public void updatePlan(String organizationId, String planId, OrganizationPlan organizationPlan) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getPlansClient().updatePlan(organizationId, planId, organizationPlan);
	}

	public void deletePlan(String organizationId, String planId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getPlansClient().deletePlan(organizationId, planId);
	}

	public ActivityList getPlanActivity(String organizationId, String planId, int page, int count) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlanActivity(organizationId, planId, page, count);
	}

	public List<PlanVersion> listPlanVersions(String organizationId, String planId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().listPlanVersions(organizationId, planId);
	}

	public PlanVersion createPlanVersion(String organizationId, String planId, PlanVersion planVersion) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().createPlanVersion(organizationId, planId, planVersion);
	}

	public PlanVersion getPlanVersion(String organizationId, String planId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlanVersion(organizationId, planId, version);
	}

	public ActivityList getPlanVersionActivity(String organizationId, String planId, String version, int page,
			int count) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlanVersionActivity(organizationId, planId, version, page,
				count);
	}

	public void reorderPlanPolicies(String organizationId, String planId, String version,
			ReOrderPolicies reOrderPolicies) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getPlansClient().reorderPlanPolicies(organizationId, planId, version, reOrderPolicies);
	}

	public List<Policy> listAllPlanPolicies(String organizationId, String planId, String version) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlansPoliciesClient().listAllPlanPolicies(organizationId,
				planId, version);
	}

	public Policy addPlanPolicy(String organizationId, String planId, String version, Policy planPolicy) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlansPoliciesClient().addPlanPolicy(organizationId, planId,
				version, planPolicy);
	}

	public Policy getPlanPolicy(String organizationId, String planId, String version, String policyId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlansPoliciesClient().getPlanPolicy(organizationId, planId,
				version, policyId);
	}

	public void updatePlanPolicy(String organizationId, String planId, String version, String policyId,
			Policy planPolicy) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getPlansClient().getPlansPoliciesClient().updatePlanPolicy(organizationId, planId,
				version, policyId, planPolicy);
	}

	public void removePlanPolicy(String organizationId, String planId, String version, String policyId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getPlansClient().getPlansPoliciesClient().removePlanPolicy(organizationId, planId,
				version, policyId);
	}

	public void grantMembership(String organizationId, GrantMemberships grantMemberships) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getRolesClient().grantMembership(organizationId, grantMemberships);
	}

	public void revokeSingleMembership(String organizationId, String roleId, String userId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getRolesClient().revokeSingleMembership(organizationId, roleId, userId);
	}

	public PermissionsList getCurrentUserPermissions() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPermissionsClient().getCurrentUserPermissions();
	}

	public PermissionsList getUserPermissions(String userId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPermissionsClient().getUserPermissions(userId);
	}

	public List<Plugin> listAllPlugins() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().listAllPlugins();
	}

	public Plugin addPlugin(Plugin plugin) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().addPlugin(plugin);
	}

	public List<Plugin> listAvailablePlugins() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().listAvailablePlugins();
	}

	public Plugin getPluginById(String pluginId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().getPluginById(pluginId);
	}

	public void deletePluginById(String pluginId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getPluginsClient().deletePluginById(pluginId);
	}

	public List<PolicyDefinition> getPluginPolicyDefinitions(String pluginId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().getPluginsPolicyDefsClient().getPluginPolicyDefinitions(pluginId);
	}

	public String getPluginPolicyForm(String pluginId, String policyDefId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().getPluginsPolicyDefsClient().getPluginPolicyForm(pluginId, policyDefId);
	}

	public List<PolicyDefinition> listPolicyDefinitions() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPolicyDefsClient().listPolicyDefinitions();
	}

	public PolicyDefinition addPolicyDefinition(PolicyDefinition policyDefinition) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPolicyDefsClient().addPolicyDefinition(policyDefinition);
	}

	public PolicyDefinition getPolicyDefinitionById(String policyDefinitionId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPolicyDefsClient().getPolicyDefinitionById(policyDefinitionId);
	}

	public void updatePolicyDefinition(String policyDefinitionId, PolicyDefinition policyDefinition) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getPolicyDefsClient().updatePolicyDefinition(policyDefinitionId, policyDefinition);
	}

	public void deletePolicyDefinition(String policyDefinitionId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getPolicyDefsClient().deletePolicyDefinition(policyDefinitionId);
	}

	public List<Role> listAllRoles() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getRolesClient().listAllRoles();
	}

	public Role createRole(Role role) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getRolesClient().createRole(role);
	}

	public SearchResult searchForRoles(SearchQuery rolesSearchQuery) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getRolesClient().searchForRoles(rolesSearchQuery);
	}

	public Role getRoleById(String roleId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getRolesClient().getRoleById(roleId);
	}

	public void updateRoleById(String roleId, Role role) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getRolesClient().updateRoleById(roleId, role);
	}

	public void deleteRoleById(String roleId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getRolesClient().deleteRoleById(roleId);
	}

	public SearchResult searchForApis(SearchQuery apisSearchQuery) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getSearchClient().searchForApis(apisSearchQuery);
	}

	public SearchResult searchForClients(SearchQuery clientsSearchQuery) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getSearchClient().searchForClients(clientsSearchQuery);
	}

	public SearchResult searchForOrganizations(SearchQuery organizationsSearchQuery) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getSearchClient().searchForOrganizations(organizationsSearchQuery);
	}

	public SearchResult searchForApisInApiCatalogue(SearchQuery apisSearchQuery) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getSearchClient().getApiCatalogueSearchClient().searchForApisInApiCatalogue(apisSearchQuery);
	}

	public List<Namespace> listAllNamespacesInApiCatalogue() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getSearchClient().getApiCatalogueSearchClient().listAllNamespacesInApiCatalogue();
	}

	public void exportData(String download) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getSystemClient().exportData(download);
	}

	public void importData() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getSystemClient().importData();
	}

	public SystemStatus getSystemStatus() {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getSystemClient().getSystemStatus();
	}

	public SearchResult searchForUsers(SearchQuery userSearchQuery) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getUsersClient().searchForUsers(userSearchQuery);
	}

	public User getUserById(String userId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getUsersClient().getUserById(userId);
	}

	public void updateUserById(String userId, User user) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		getUsersClient().updateUserById(userId, user);
	}

	public ActivityList getUserActivity(String userId, int page, int count) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getUsersClient().getUserActivity(userId, page, count);
	}

	public List<Api> listUserApis(String userId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getUsersClient().listUserApis(userId);
	}

	public List<Client> listUserClients(String userId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getUsersClient().listUserClients(userId);
	}

	public List<Organization> listUserOrganizations(String userId) {
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getUsersClient().listUserOrganizations(userId);
	}
}
