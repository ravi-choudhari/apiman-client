package org.apiman.client;

import java.util.Date;
import java.util.List;

import org.apiman.client.conditions.ApimanCondition;
import org.apiman.client.domain.action.Action;
import org.apiman.client.domain.apis.Api;
import org.apiman.client.domain.apis.ApiVersion;
import org.apiman.client.domain.apis.ApiVersionStatus;
import org.apiman.client.domain.apis.NewApi;
import org.apiman.client.domain.apis.NewApiDefinition;
import org.apiman.client.domain.apis.NewApiVersion;
import org.apiman.client.domain.apis.UpdateApi;
import org.apiman.client.domain.apis.UpdateApiVersion;
import org.apiman.client.domain.audit.AuditEntry;
import org.apiman.client.domain.clients.ApiKey;
import org.apiman.client.domain.clients.Client;
import org.apiman.client.domain.clients.ClientVersion;
import org.apiman.client.domain.clients.NewClient;
import org.apiman.client.domain.clients.NewClientVersion;
import org.apiman.client.domain.clients.UpdateClient;
import org.apiman.client.domain.contracts.Contract;
import org.apiman.client.domain.contracts.NewContract;
import org.apiman.client.domain.currentuser.CurrentUser;
import org.apiman.client.domain.gateway.Gateway;
import org.apiman.client.domain.members.Member;
import org.apiman.client.domain.organization.NewOrganization;
import org.apiman.client.domain.organization.Organization;
import org.apiman.client.domain.organization.UpdateOrganization;
import org.apiman.client.domain.permissions.UserPermissions;
import org.apiman.client.domain.plan.NewPlan;
import org.apiman.client.domain.plan.NewPlanVersion;
import org.apiman.client.domain.plan.Plan;
import org.apiman.client.domain.plan.PlanVersion;
import org.apiman.client.domain.plan.UpdatePlan;
import org.apiman.client.domain.plugin.PluginSummary;
import org.apiman.client.domain.policies.NewPolicy;
import org.apiman.client.domain.policies.Policy;
import org.apiman.client.domain.policies.PolicyChain;
import org.apiman.client.domain.policies.UpdatePolicy;
import org.apiman.client.domain.policydefinition.PolicyDefinition;
import org.apiman.client.domain.role.GrantRoles;
import org.apiman.client.domain.role.Role;
import org.apiman.client.domain.search.SearchCriteria;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.domain.summary.ApiNamespace;
import org.apiman.client.domain.summary.ApiPlanSummary;
import org.apiman.client.domain.summary.ApiSummary;
import org.apiman.client.domain.summary.ApiVersionEndpointSummary;
import org.apiman.client.domain.summary.ApiVersionSummary;
import org.apiman.client.domain.summary.AvailableApi;
import org.apiman.client.domain.summary.ClientSummary;
import org.apiman.client.domain.summary.ClientVersionSummary;
import org.apiman.client.domain.summary.ContractSummary;
import org.apiman.client.domain.summary.GatewaySummary;
import org.apiman.client.domain.summary.OrganizationSummary;
import org.apiman.client.domain.summary.PlanSummary;
import org.apiman.client.domain.summary.PlanVersionSummary;
import org.apiman.client.domain.summary.PolicyDefinitionSummary;
import org.apiman.client.domain.summary.PolicySummary;
import org.apiman.client.domain.summary.ResponseStats;
import org.apiman.client.domain.summary.ResponseStatsHistogram;
import org.apiman.client.domain.summary.ResponseStatsSummary;
import org.apiman.client.domain.summary.Usage;
import org.apiman.client.domain.summary.UsageHistogram;
import org.apiman.client.domain.system.SystemStatus;
import org.apiman.client.domain.user.UpdateUser;
import org.apiman.client.domain.user.User;
import org.apiman.client.resources.IActionsClient;
import org.apiman.client.resources.ICurrentUserClient;
import org.apiman.client.resources.IDownloadsClient;
import org.apiman.client.resources.IGatewaysClient;
import org.apiman.client.resources.IOrganizationsClient;
import org.apiman.client.resources.IPermissionsClient;
import org.apiman.client.resources.IPluginsClient;
import org.apiman.client.resources.IPolicyDefsClient;
import org.apiman.client.resources.IRolesClient;
import org.apiman.client.resources.ISearchClient;
import org.apiman.client.resources.ISystemClient;
import org.apiman.client.resources.IUsersClient;
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
@Conditional(ApimanCondition.class)
@Getter(value = AccessLevel.PRIVATE)
@Setter(value = AccessLevel.PRIVATE)
@Slf4j
public class ApimanRestServicesClient {

	@Autowired
	private IActionsClient actionsClient;
	@Autowired
	private ICurrentUserClient currentUserClient;
	@Autowired
	private IDownloadsClient downloadsClient;
	@Autowired
	private IGatewaysClient gatewaysClient;
	@Autowired
	private IOrganizationsClient organizationsClient;
	@Autowired
	private IPermissionsClient permissionsClient;
	@Autowired
	private IPluginsClient pluginsClient;
	@Autowired
	private IPolicyDefsClient policyDefsClient;
	@Autowired
	private IRolesClient rolesClient;
	@Autowired
	private ISearchClient searchClient;
	@Autowired
	private IUsersClient usersClient;
	@Autowired
	private ISystemClient systemClient;

	public void executeEntityAction(Action action) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getActionsClient().executeEntityAction(action);
	}

	public List<OrganizationSummary> getApiOrganizations() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getCurrentUserClient().getApiOrganizations();
	}

	public List<ApiSummary> getCurrentUserApis() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getCurrentUserClient().getCurrentUserApis();
	}

	public List<OrganizationSummary> getClientOrganizations() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getCurrentUserClient().getClientOrganizations();
	}

	public List<ClientSummary> getCurrentUserClients() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getCurrentUserClient().getCurrentUserClients();
	}

	public CurrentUser getCurrentUserInformation() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getCurrentUserClient().getCurrentUserInformation();
	}

	public void updateCurrentUserInformation(UpdateUser userInformation) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getCurrentUserClient().updateCurrentUserInformation(userInformation);
	}

	public List<OrganizationSummary> getPlanOrganizations() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getCurrentUserClient().getPlanOrganizations();
	}

	public String downloadFile(String downloadId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getDownloadsClient().downloadFile(downloadId);
	}

	public List<GatewaySummary> listAllGateways() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getGatewaysClient().listAllGateways();
	}

	public Gateway getGatewayById(String gatewayId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getGatewaysClient().getGatewayById(gatewayId);
	}

	public Organization createOrganization(NewOrganization organization) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().createOrganization(organization);
	}

	public Organization getOrganizationById(String organizationId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getOrganizationById(organizationId);
	}

	public void updateOrganization(String organizationId, UpdateOrganization organization) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().updateOrganization(organizationId, organization);
	}

	public void deleteOrganization(String organizationId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().deleteOrganization(organizationId);
	}

	public SearchResults<AuditEntry> getOrganizationActivity(String organizationId, int page, int count) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getOrganizationActivity(organizationId, page, count);
	}

	public List<ApiSummary> listApis(String organizationId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().listApis(organizationId);
	}

	public Api createApi(String organizationId, NewApi createApi) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().createApi(organizationId, createApi);
	}

	public Api getApiById(String organizationId, String apiId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiById(organizationId, apiId);
	}

	public void updateApi(String organizationId, String apiId, UpdateApi api) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().updateApi(organizationId, apiId, api);
	}

	public void deleteApi(String organizationId, String apiId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().deleteApi(organizationId, apiId);
	}

	public SearchResults<AuditEntry> getApiActivity(String organizationId, String apiId, int page, int count) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiActivity(organizationId, apiId, page, count);
	}

	public List<ApiVersionSummary> listApiVersions(String organizationId, String apiId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().listApiVersions(organizationId, apiId);
	}

	public ApiVersion createApiVersion(String organizationId, String apiId, NewApiVersion createApiVersion) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().createApiVersion(organizationId, apiId, createApiVersion);
	}

	public ApiVersion getApiVersion(String organizationId, String apiId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiVersion(organizationId, apiId, version);
	}

	public ApiVersion updateApiVersion(String organizationId, String apiId, String version,
			UpdateApiVersion createApiVersion) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().updateApiVersion(organizationId, apiId, version,
				createApiVersion);
	}

	public SearchResults<AuditEntry> getApiVersionActivity(String organizationId, String apiId, String version,
			int page, int count) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiVersionActivity(organizationId, apiId, version, page,
				count);
	}

	public List<ContractSummary> listApiContracts(String organizationId, String apiId, String version, int page,
			int count) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().listApiContracts(organizationId, apiId, version, page, count);
	}

	public String getApiDefinition(String organizationId, String apiId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiDefinition(organizationId, apiId, version);
	}

	public void updateApiDefinition(String organizationId, String apiId, String version, String apiDefinition) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().updateApiDefinition(organizationId, apiId, version, apiDefinition);
	}

	public void updateApiDefinitionFromUrl(String organizationId, String apiId, String version,
			NewApiDefinition apiDefinition) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().updateApiDefinitionFromUrl(organizationId, apiId, version,
				apiDefinition);
	}

	public void removeApiDefinition(String organizationId, String apiId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().removeApiDefinition(organizationId, apiId, version);
	}

	public ApiVersionEndpointSummary getApiEndpoint(String organizationId, String apiId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiEndpoint(organizationId, apiId, version);
	}

	public void reOrderApiPolicies(String organizationId, String apiId, String version, PolicyChain reOrderPolicies) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().reOrderApiPolicies(organizationId, apiId, version, reOrderPolicies);
	}

	public ApiVersionStatus getApiVersionStatus(String organizationId, String apiId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getApiVersionStatus(organizationId, apiId, version);
	}

	public ResponseStats getApiResponseStatisticsPerClient(String organizationId, String apiId, String version,
			Date fromDate, Date toDate) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiResponseStatisticsPerClient(organizationId, apiId, version, fromDate, toDate);
	}

	public Usage getApiUsageMetricsPerClient(String organizationId, String apiId, String version, Date fromDate,
			Date toDate) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiUsageMetricsPerClient(organizationId, apiId, version, fromDate, toDate);
	}

	public ResponseStats getApiResponseStatisticsPerPlan(String organizationId, String apiId, String version,
			Date fromDate, Date toDate) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiResponseStatisticsPerPlan(organizationId, apiId, version, fromDate, toDate);
	}

	public Usage getApiUsageMetricsPerPlan(String organizationId, String apiId, String version, Date fromDate,
			Date toDate) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiUsageMetricsPerPlan(organizationId, apiId, version, fromDate, toDate);
	}

	public ResponseStatsHistogram getApiResponseStatistics(String organizationId, String apiId, String version,
			Date fromDate, Date toDate) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiResponseStatistics(organizationId, apiId, version, fromDate, toDate);
	}

	public ResponseStatsSummary getApiResponseStatisticsSummary(String organizationId, String apiId, String version,
			Date fromDate, Date toDate) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiResponseStatisticsSummary(organizationId, apiId, version, fromDate, toDate, null);
	}

	public UsageHistogram getApiUsageMetrics(String organizationId, String apiId, String version, Date fromDate,
			Date toDate, String interval) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiMetricsClient()
				.getApiUsageMetrics(organizationId, apiId, version, fromDate, toDate, interval);
	}

	public List<ApiPlanSummary> listApiPlans(String organizationId, String apiId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiPlansClient().listApiPlans(organizationId,
				apiId, version);
	}

	public PolicyChain getApiPolicyChain(String organizationId, String apiId, String version, String planId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiPlansClient()
				.getApiPolicyChain(organizationId, apiId, version, planId);
	}

	public List<PolicySummary> listAllApiPolicies(String organizationId, String apiId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiPoliciesClient()
				.listAllApiPolicies(organizationId, apiId, version);
	}

	public Policy addApiPolicy(String organizationId, String apiId, String version, NewPolicy apiPolicy) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiPoliciesClient().addApiPolicy(organizationId,
				apiId, version, apiPolicy);
	}

	public Policy getApiPolicy(String organizationId, String apiId, String version, Long policyId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getApisClient().getOrganizationApiPoliciesClient().getApiPolicy(organizationId,
				apiId, version, policyId);
	}

	public void updateApiPolicy(String organizationId, String apiId, String version, Long policyId,
			UpdatePolicy apiPolicy) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().getOrganizationApiPoliciesClient().updateApiPolicy(organizationId,
				apiId, version, policyId, apiPolicy);
	}

	public void removeApiPolicy(String organizationId, String apiId, String version, Long policyId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getApisClient().getOrganizationApiPoliciesClient().removeApiPolicy(organizationId,
				apiId, version, policyId);
	}

	public List<ClientSummary> listClients(String organizationId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().listClients(organizationId);
	}

	public Client createClient(String organizationId, NewClient client) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().createClient(organizationId, client);
	}

	public Client getClientById(String organizationId, String clientId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientById(organizationId, clientId);
	}

	public void updateClient(String organizationId, String clientId, UpdateClient client) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().updateClient(organizationId, clientId, client);
	}

	public void deleteClient(String organizationId, String clientId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().deleteClient(organizationId, clientId);
	}

	public SearchResults<AuditEntry> getClientActivity(String organizationId, String clientId, int page, int count) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientActivity(organizationId, clientId, page, count);
	}

	public List<ClientVersionSummary> listClientVersions(String organizationId, String clientId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().listClientVersions(organizationId, clientId);
	}

	public ClientVersion createClientVersion(String organizationId, String clientId, NewClientVersion clientVersion) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().createClientVersion(organizationId, clientId, clientVersion);
	}

	public ClientVersion getClientVersion(String organizationId, String clientId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientVersion(organizationId, clientId, version);
	}

	public SearchResults<AuditEntry> getClientVersionActivity(String organizationId, String clientId, String version,
			int page, int count) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientVersionActivity(organizationId, clientId, version,
				page, count);
	}

	public ApiKey getApiKey(String organizationId, String clientId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getApiKey(organizationId, clientId, version);
	}

	public ApiKey updateApiKey(String organizationId, String clientId, String version, ApiKey apiKey) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().updateApiKey(organizationId, clientId, version, apiKey);
	}

	public Usage getClientUsageMetricsPerApi(String organizationId, String clientId, String version, Date fromDate,
			Date toDate) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientUsageMetricsPerApi(organizationId, clientId,
				version, fromDate, toDate);
	}

	public void reorderClientPolicies(String organizationId, String clientId, String version,
			PolicyChain reOrderPolicies) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().reorderClientPolicies(organizationId, clientId, version,
				reOrderPolicies);
	}

	public String getJsonApiRegistry(String organizationId, String clientId, String version, String download) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsApiRegistryClient()
				.getJsonApiRegistry(organizationId, clientId, version, download);
	}

	public String getXmlApiRegistry(String organizationId, String clientId, String version, String download) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsApiRegistryClient()
				.getXmlApiRegistry(organizationId, clientId, version, download);
	}

	public List<ContractSummary> listAllContractsForClient(String organizationId, String clientId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsContractsClient()
				.listAllContractsForClient(organizationId, clientId, version);
	}

	public Contract createApiContract(String organizationId, String clientId, String version, NewContract apiContract) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsContractsClient().createApiContract(organizationId,
				clientId, version, apiContract);
	}

	public void breakAllContracts(String organizationId, String clientId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().getClientsContractsClient().breakAllContracts(organizationId,
				clientId, version);
	}

	public Contract getApiContract(String organizationId, String clientId, String version, Long contractId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsContractsClient().getApiContract(organizationId,
				clientId, version, contractId);
	}

	public void breakContract(String organizationId, String clientId, String version, Long contractId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().getClientsContractsClient().breakContract(organizationId, clientId,
				version, contractId);
	}

	public List<PolicySummary> listAllClientPolicies(String organizationId, String clientId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsPoliciesClient()
				.listAllClientPolicies(organizationId, clientId, version);
	}

	public Policy addClientPolicy(String organizationId, String clientId, String version, NewPolicy clientPolicy) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsPoliciesClient().addClientPolicy(organizationId,
				clientId, version, clientPolicy);
	}

	public Policy getClientPolicy(String organizationId, String clientId, String version, Long policyId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getClientsClient().getClientsPoliciesClient().getClientPolicy(organizationId,
				clientId, version, policyId);
	}

	public void updateClientPolicy(String organizationId, String clientId, String version, Long policyId,
			UpdatePolicy clientPolicy) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().getClientsPoliciesClient().updateClientPolicy(organizationId,
				clientId, version, policyId, clientPolicy);
	}

	public void removeClientPolicy(String organizationId, String clientId, String version, Long policyId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getClientsClient().getClientsPoliciesClient().removeClientPolicy(organizationId,
				clientId, version, policyId);
	}

	public List<Member> listOrganizationMembers(String organizationId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getMembersClient().listOrganizationMembers(organizationId);
	}

	public void revokeAllMemberships(String organizationId, String userId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getMembersClient().revokeAllMemberships(organizationId, userId);
	}

	public List<PlanSummary> listPlans(String organizationId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().listPlans(organizationId);
	}

	public Plan createPlan(String organizationId, NewPlan organizationPlan) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().createPlan(organizationId, organizationPlan);
	}

	public Plan getPlanById(String organizationId, String planId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlanById(organizationId, planId);
	}

	public void updatePlan(String organizationId, String planId, UpdatePlan organizationPlan) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getPlansClient().updatePlan(organizationId, planId, organizationPlan);
	}

	public void deletePlan(String organizationId, String planId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getPlansClient().deletePlan(organizationId, planId);
	}

	public SearchResults<AuditEntry> getPlanActivity(String organizationId, String planId, int page, int count) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlanActivity(organizationId, planId, page, count);
	}

	public List<PlanVersionSummary> listPlanVersions(String organizationId, String planId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().listPlanVersions(organizationId, planId);
	}

	public PlanVersion createPlanVersion(String organizationId, String planId, NewPlanVersion planVersion) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().createPlanVersion(organizationId, planId, planVersion);
	}

	public PlanVersion getPlanVersion(String organizationId, String planId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlanVersion(organizationId, planId, version);
	}

	public SearchResults<AuditEntry> getPlanVersionActivity(String organizationId, String planId, String version,
			int page, int count) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlanVersionActivity(organizationId, planId, version, page,
				count);
	}

	public void reorderPlanPolicies(String organizationId, String planId, String version, PolicyChain reOrderPolicies) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getPlansClient().reorderPlanPolicies(organizationId, planId, version, reOrderPolicies);
	}

	public List<PolicySummary> listAllPlanPolicies(String organizationId, String planId, String version) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlansPoliciesClient().listAllPlanPolicies(organizationId,
				planId, version);
	}

	public Policy addPlanPolicy(String organizationId, String planId, String version, NewPolicy planPolicy) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlansPoliciesClient().addPlanPolicy(organizationId, planId,
				version, planPolicy);
	}

	public Policy getPlanPolicy(String organizationId, String planId, String version, Long policyId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getOrganizationsClient().getPlansClient().getPlansPoliciesClient().getPlanPolicy(organizationId, planId,
				version, policyId);
	}

	public void updatePlanPolicy(String organizationId, String planId, String version, Long policyId,
			UpdatePolicy planPolicy) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getPlansClient().getPlansPoliciesClient().updatePlanPolicy(organizationId, planId,
				version, policyId, planPolicy);
	}

	public void removePlanPolicy(String organizationId, String planId, String version, Long policyId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getPlansClient().getPlansPoliciesClient().removePlanPolicy(organizationId, planId,
				version, policyId);
	}

	public void grantMembership(String organizationId, GrantRoles grantMemberships) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getRolesClient().grantMembership(organizationId, grantMemberships);
	}

	public void revokeSingleMembership(String organizationId, String roleId, String userId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getOrganizationsClient().getRolesClient().revokeSingleMembership(organizationId, roleId, userId);
	}

	public UserPermissions getCurrentUserPermissions() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPermissionsClient().getCurrentUserPermissions();
	}

	public List<PluginSummary> listAllPlugins() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().listAllPlugins();
	}

	public List<PolicyDefinitionSummary> getPluginPolicyDefinitions(Long pluginId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().getPluginsPolicyDefsClient().getPluginPolicyDefinitions(pluginId);
	}

	public String getPluginPolicyForm(Long pluginId, String policyDefId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPluginsClient().getPluginsPolicyDefsClient().getPluginPolicyForm(pluginId, policyDefId);
	}

	public List<PolicyDefinitionSummary> listPolicyDefinitions() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPolicyDefsClient().listPolicyDefinitions();
	}

	public PolicyDefinition getPolicyDefinitionById(String policyDefinitionId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getPolicyDefsClient().getPolicyDefinitionById(policyDefinitionId);
	}

	public List<Role> listAllRoles() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getRolesClient().listAllRoles();
	}

	public SearchResults<Role> searchForRoles(SearchCriteria rolesSearchQuery) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getRolesClient().searchForRoles(rolesSearchQuery);
	}

	public Role getRoleById(String roleId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getRolesClient().getRoleById(roleId);
	}

	public SearchResults<ApiSummary> searchForApis(SearchCriteria apisSearchQuery) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getSearchClient().searchForApis(apisSearchQuery);
	}

	public SearchResults<ClientSummary> searchForClients(SearchCriteria clientsSearchQuery) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getSearchClient().searchForClients(clientsSearchQuery);
	}

	public SearchResults<OrganizationSummary> searchForOrganizations(SearchCriteria organizationsSearchQuery) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getSearchClient().searchForOrganizations(organizationsSearchQuery);
	}

	public SearchResults<AvailableApi> searchForApisInApiCatalogue(SearchCriteria apisSearchQuery) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getSearchClient().getApiCatalogueSearchClient().searchForApisInApiCatalogue(apisSearchQuery);
	}

	public List<ApiNamespace> listAllNamespacesInApiCatalogue() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getSearchClient().getApiCatalogueSearchClient().listAllNamespacesInApiCatalogue();
	}

	public SearchResults<User> searchForUsers(SearchCriteria userSearchQuery) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getUsersClient().searchForUsers(userSearchQuery);
	}

	public User getUserById(String userId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getUsersClient().getUserById(userId);
	}

	public void updateUserById(String userId, UpdateUser user) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getUsersClient().updateUserById(userId, user);
	}

	public SearchResults<AuditEntry> getUserActivity(String userId, int page, int count) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getUsersClient().getUserActivity(userId, page, count);
	}

	public List<ApiSummary> listUserApis(String userId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getUsersClient().listUserApis(userId);
	}

	public List<ClientSummary> listUserClients(String userId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getUsersClient().listUserClients(userId);
	}

	public List<OrganizationSummary> listUserOrganizations(String userId) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getUsersClient().listUserOrganizations(userId);
	}

	public void exportData(String download) {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getSystemClient().exportData(download);
	}

	public void importData() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		getSystemClient().importData();
	}

	public SystemStatus getSystemStatus() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName());

		return getSystemClient().getSystemStatus();
	}
}
