package org.apiman.client;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.net.URLCodec;
import org.apiman.client.domain.Action;
import org.apiman.client.domain.Gateway;
import org.apiman.client.domain.UserInformation;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:apiman-client-applicationContext.xml")
public class TestApimanRestServicesClientUrls {
	
	@Autowired
	@Qualifier("redhatApimanRestClient")
	private RestTemplate restTemplate;
	
	@Value(value = "${apiman.url:http://localhost:8080/apiman}")
	protected String apimanUrl;
	
	@Autowired
	private ApimanRestServicesClient service;
	
	private MockRestServiceServer mockServer;
	
	private static final String downloadId = "download file";
	private static final String apiId = "my best api";
	private static final String clientId = "best client";
	private static final String version = "version 1.1";
	private static final String gatewayId = "gateway 2";
	
	private static final String ACTIONS = "/actions";
	
	private static final String CURRENTUSER = "/currentuser";
	private static final String CURRENTUSER_APIORGS = CURRENTUSER + "/apiorgs";
	private static final String CURRENTUSER_APIS = CURRENTUSER + "/apis";
	private static final String CURRENTUSER_CLIENTORGS = CURRENTUSER + "/clientorgs";
	private static final String CURRENTUSER_CLIENTS = CURRENTUSER + "/clients";
	private static final String CURRENTUSER_INFO = CURRENTUSER + "/info";
	private static final String CURRENTUSER_PLANORGS = CURRENTUSER + "/planorgs";
	
	private static final String DOWNLOADS = "/downloads";
	private static final String GATEWAYS = "/gateways";
	
	private static String DOWNLOADS_DOWNLOADID;
	private static String GATEWAYS_GATEWAYID;
	
	@BeforeClass
	public static void beforeClass() throws UnsupportedEncodingException {
		DOWNLOADS_DOWNLOADID =  String.format(DOWNLOADS + "/%s", new URLCodec().encode(downloadId, StandardCharsets.UTF_8.name()));
		GATEWAYS_GATEWAYID = String.format(GATEWAYS + "/%s", new URLCodec().encode(gatewayId, StandardCharsets.UTF_8.name()));
	}
	
	@Before
	public void before() {
		RestGatewaySupport gateway = new RestGatewaySupport();
		gateway.setRestTemplate(restTemplate);
        mockServer = MockRestServiceServer.createServer(restTemplate);
	}
	
	@Test
	public void executeEntityAction() {
		
		String url = apimanUrl + ACTIONS;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.executeEntityAction(new Action());
		
		mockServer.verify();
	}
	
	@Test
	public void getApiOrganizations() {
		
		String url = apimanUrl + CURRENTUSER_APIORGS;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiOrganizations();
		
		mockServer.verify();
	}
	
	@Test
	public void getCurrentUserApis() {
		
		String url = apimanUrl + CURRENTUSER_APIS;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getCurrentUserApis();
		
		mockServer.verify();
	}
	
	@Test
	public void getClientOrganizations() {
		
		String url = apimanUrl + CURRENTUSER_CLIENTORGS;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getClientOrganizations();
		
		mockServer.verify();
	}
	
	@Test
	public void getCurrentUserClients() {
		
		String url = apimanUrl + CURRENTUSER_CLIENTS;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getCurrentUserClients();
		
		mockServer.verify();
	}
	
	@Test
	public void getCurrentUserInformation() {
		
		String url = apimanUrl + CURRENTUSER_INFO;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getCurrentUserInformation();
		
		mockServer.verify();
	}
	
	@Test
	public void updateCurrentUserInformation() {
		
		String url = apimanUrl + CURRENTUSER_INFO;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateCurrentUserInformation(new UserInformation());
		
		mockServer.verify();
	}
	
	@Test
	public void getPlanOrganizations() {
		
		String url = apimanUrl + CURRENTUSER_PLANORGS;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getPlanOrganizations();
		
		mockServer.verify();
	}
	
	/*@Test
	public String downloadFile(String downloadId) {
		
		return downloadsClient.downloadFile(downloadId);
	}*/
	
	@Test
	public void listAllGateways() {
		
		String url = apimanUrl + GATEWAYS;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listAllGateways();
		
		mockServer.verify();
	}
	
	@Test
	public void testGateway() {
		
		String url = apimanUrl + GATEWAYS;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.testGateway(new Gateway());
		
		mockServer.verify();
	}
	
	@Test
	public void createGateway() {
		
		String url = apimanUrl + GATEWAYS;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createGateway(new Gateway());
		
		mockServer.verify();
	}
	
	@Test
	public void getGatewayById() {
		
		String url = apimanUrl + GATEWAYS_GATEWAYID;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getGatewayById(gatewayId);
		
		mockServer.verify();
	}
	
	@Test
	public void updateGateway() {
		
		String url = apimanUrl + GATEWAYS_GATEWAYID;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateGateway(gatewayId, new Gateway());
		
		mockServer.verify();
	}
	
	@Test
	public void deleteGateway() {
		
		String url = apimanUrl + GATEWAYS_GATEWAYID;
		System.out.println("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		System.out.println("Test Case    : " + url);
		
		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE)).andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deleteGateway(gatewayId);
		
		mockServer.verify();
	}
	/*
	@Test
	public Organization createOrganization(Organization organization) {
		
		return organizationsClient.createOrganization(organization);
	}
	
	@Test
	public Organization getOrganizationById(String organizationId) {
		
		return organizationsClient.getOrganizationById(organizationId);
	}
	
	@Test
	public void updateOrganization(String organizationId, Organization organization) {
		
		organizationsClient.updateOrganization(organizationId, organization);
	}
	
	@Test
	public void deleteOrganization(String organizationId) {
		
		organizationsClient.deleteOrganization(organizationId);
	}
	
	@Test
	public ActivityList getOrganizationActivity(String organizationId, int page, int count) {
		
		return organizationsClient.getOrganizationActivity(organizationId, page, count);
	}	
	
	@Test
	public List<Api> listApis(String organizationId) {
		
		return organizationsClient.getApisClient().listApis(organizationId);
	}
	
	@Test
	public Api createApi(String organizationId, CreateApi createApi) {
		
		return organizationsClient.getApisClient().createApi(organizationId, createApi);
	}
	
	@Test
	public Api getApiById(String organizationId, String apiId) {
		
		return organizationsClient.getApisClient().getApiById(organizationId, apiId);
	}
	
	@Test
	public void updateApi(String organizationId, String apiId, Api api) {
		
		organizationsClient.getApisClient().updateApi(organizationId, apiId, api);
	}
	
	@Test
	public void deleteApi(String organizationId, String apiId) {
		
		organizationsClient.getApisClient().deleteApi(organizationId, apiId);
	}
	
	@Test
	public ActivityList getApiActivity(String organizationId, String apiId, int page, int count) {
		
		return organizationsClient.getApisClient().getApiActivity(organizationId, apiId, page, count);
	}
	
	@Test
	public List<ApiVersion> listApiVersions(String organizationId, String apiId) {
		
		return organizationsClient.getApisClient().listApiVersions(organizationId, apiId);
	}
	
	@Test
	public CreateApiVersion createApiVersion(String organizationId, String apiId, CreateApiVersion createApiVersion) {
		
		return organizationsClient.getApisClient().createApiVersion(organizationId, apiId, createApiVersion);
	}
	
	@Test
	public CreateApiVersion getApiVersion(String organizationId, String apiId, String version) {
		
		return organizationsClient.getApisClient().getApiVersion(organizationId, apiId, version);
	}
	
	@Test
	public CreateApiVersion updateApiVersion(String organizationId, String apiId, String version, CreateApiVersion createApiVersion) {
		
		return organizationsClient.getApisClient().updateApiVersion(organizationId, apiId, version, createApiVersion);
	}
	
	@Test
	public ActivityList getApiVersionActivity(String organizationId, String apiId, String version, int page, int count) {
		
		return organizationsClient.getApisClient().getApiVersionActivity(organizationId, apiId, version, page, count);
	}
	
	@Test
	public List<ApiContract> listApiContracts(String organizationId, String apiId, String version, int page, int count) {
		
		return organizationsClient.getApisClient().listApiContracts(organizationId, apiId, version, page, count);
	}
	
	@Test
	public String getApiDefinition(String organizationId, String apiId, String version) {
		
		return organizationsClient.getApisClient().getApiDefinition(organizationId, apiId, version);
	}
	
	@Test
	public void updateApiDefinition(String organizationId, String apiId, String version, String apiDefinition) {
		
		organizationsClient.getApisClient().updateApiDefinition(organizationId, apiId, version, apiDefinition);
	}
	
	@Test
	public void updateApiDefinitionFromUrl(String organizationId, String apiId, String version, ApiDefinitionUrl apiDefinitionUrl) {
		
		organizationsClient.getApisClient().updateApiDefinitionFromUrl(organizationId, apiId, version, apiDefinitionUrl);
	}
	
	@Test
	public void removeApiDefinition(String organizationId, String apiId, String version) {
		
		organizationsClient.getApisClient().removeApiDefinition(organizationId, apiId, version);
	}
	
	@Test
	public Endpoint getApiEndpoint(String organizationId, String apiId, String version) {
		
		return organizationsClient.getApisClient().getApiEndpoint(organizationId, apiId, version);
	}
	
	@Test
	public void reOrderApiPolicies(String organizationId, String apiId, String version, ReOrderPolicies reOrderPolicies) {
		
		organizationsClient.getApisClient().reOrderApiPolicies(organizationId, apiId, version, reOrderPolicies);
	}

	@Test
	public ApiStatusList getApiVersionStatus(String organizationId, String apiId, String version) {
		
		return organizationsClient.getApisClient().getApiVersionStatus(organizationId, apiId, version);
	}
	
	@Test
	public ApiMetrics getApiResponseStatisticsPerClient(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiResponseStatisticsPerClient(organizationId, apiId, version, fromDate, toDate);
	}
	
	@Test
	public ApiMetrics getApiUsageMetricsPerClient(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiUsageMetricsPerClient(organizationId, apiId, version, fromDate, toDate);
	}
	
	@Test
	public ApiMetrics getApiResponseStatisticsPerPlan(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiResponseStatisticsPerPlan(organizationId, apiId, version, fromDate, toDate);
	}
	
	@Test
	public ApiMetrics getApiUsageMetricsPerPlan(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiUsageMetricsPerPlan(organizationId, apiId, version, fromDate, toDate);
	}
	
	@Test
	public ApiMetrics getApiResponseStatistics(String organizationId, String apiId, String version, String fromDate, String toDate) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiResponseStatistics(organizationId, apiId, version, fromDate, toDate);
	}
	
	@Test
	public ApiMetricsList getApiResponseStatisticsSummary(String organizationId, String apiId, String version, String fromDate, String toDate, String interval) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiResponseStatisticsSummary(organizationId, apiId, version, fromDate, toDate, interval);
	}
	
	@Test
	public ApiMetricsList getApiUsageMetrics(String organizationId, String apiId, String version, String fromDate, String toDate, String interval) {
		
		return organizationsClient.getApisClient().getOrganizationApiMetricsClient().getApiUsageMetrics(organizationId, apiId, version, fromDate, toDate, interval);
	}
	
	@Test
	public List<Plan> listApiPlans(String organizationId, String apiId, String version) {
		
		return organizationsClient.getApisClient().getOrganizationApiPlansClient().listApiPlans(organizationId, apiId, version);
	}
	
	@Test
	public ApiPolicyList getApiPolicyChain(String organizationId, String apiId, String version, String planId) {
		
		return organizationsClient.getApisClient().getOrganizationApiPlansClient().getApiPolicyChain(organizationId, apiId, version, planId);
	}
	
	@Test
	public List<Policy> listAllApiPolicies(String organizationId, String apiId, String version) {
		
		return organizationsClient.getApisClient().getOrganizationApiPoliciesClient().listAllApiPolicies(organizationId, apiId, version);
	}
	
	@Test
	public Policy addApiPolicy(String organizationId, String apiId, String version, Policy apiPolicy) {
		
		return organizationsClient.getApisClient().getOrganizationApiPoliciesClient().addApiPolicy(organizationId, apiId, version, apiPolicy);
	}
	
	@Test
	public Policy getApiPolicy(String organizationId, String apiId, String version, String policyId) {
		
		return organizationsClient.getApisClient().getOrganizationApiPoliciesClient().getApiPolicy(organizationId, apiId, version, policyId);
	}
	
	@Test
	public void updateApiPolicy(String organizationId, String apiId, String version, String policyId, Policy apiPolicy) {
		
		organizationsClient.getApisClient().getOrganizationApiPoliciesClient().updateApiPolicy(organizationId, apiId, version, policyId, apiPolicy);
	}
	
	@Test
	public void removeApiPolicy(String organizationId, String apiId, String version, String policyId) {
		
		organizationsClient.getApisClient().getOrganizationApiPoliciesClient().removeApiPolicy(organizationId, apiId, version, policyId);
	}
	
	@Test
	public List<Client> listClients(String organizationId) {
		
		return organizationsClient.getClientsClient().listClients(organizationId);
	}
	
	@Test
	public Client createClient(String organizationId, Client client) {
		
		return organizationsClient.getClientsClient().createClient(organizationId, client);
	}
	
	@Test
	public Client getClientById(String organizationId, String clientId) {
		
		return organizationsClient.getClientsClient().getClientById(organizationId, clientId);
	}
	
	@Test
	public void updateClient(String organizationId, String clientId, Client client) {
		
		organizationsClient.getClientsClient().updateClient(organizationId, clientId, client);
	}
	
	@Test
	public void deleteClient(String organizationId, String clientId) {
		
		organizationsClient.getClientsClient().deleteClient(organizationId, clientId);
	}
	
	@Test
	public ActivityList getClientActivity(String organizationId, String clientId, int page, int count) {
		
		return organizationsClient.getClientsClient().getClientActivity(organizationId, clientId, page, count);
	}
	
	@Test
	public List<ClientVersion> listClientVersions(String organizationId, String clientId) {
		
		return organizationsClient.getClientsClient().listClientVersions(organizationId, clientId);
	}
	
	@Test
	public ClientVersion createClientVersion(String organizationId, String clientId, ClientVersion clientVersion) {
		
		return organizationsClient.getClientsClient().createClientVersion(organizationId, clientId, clientVersion);
	}
	
	@Test
	public ClientVersion getClientVersion(String organizationId, String clientId, String version) {
		
		return organizationsClient.getClientsClient().getClientVersion(organizationId, clientId, version);
	}
	
	@Test
	public ActivityList getClientVersionActivity(String organizationId, String clientId, String version) {
		
		return organizationsClient.getClientsClient().getClientVersionActivity(organizationId, clientId, version);
	}
	
	@Test
	public ApiKey getApiKey(String organizationId, String clientId, String version) {
		
		return organizationsClient.getClientsClient().getApiKey(organizationId, clientId, version);
	}
	
	@Test
	public ApiKey updateApiKey(String organizationId, String clientId, String version, ApiKey apiKey) {
		
		return organizationsClient.getClientsClient().updateApiKey(organizationId, clientId, version, apiKey);
	}
	
	@Test
	public ApiMetrics getClientUsageMetricsPerApi(String organizationId, String clientId, String version, String fromDate, String toDate) {
		
		return organizationsClient.getClientsClient().getClientUsageMetricsPerApi(organizationId, clientId, version, fromDate, toDate);
	}
	
	@Test
	public void reorderClientPolicies(String organizationId, String clientId, String version, ReOrderPolicies reOrderPolicies) {
		
		organizationsClient.getClientsClient().reorderClientPolicies(organizationId, clientId, version, reOrderPolicies);
	}
	
	@Test
	public String getJsonApiRegistry(String organizationId, String clientId, String version, String download) {
		
		return organizationsClient.getClientsClient().getClientsApiRegistryClient().getJsonApiRegistry(organizationId, clientId, version, download);
	}
	
	@Test
	public String getXmlApiRegistry(String organizationId, String clientId, String version, String download) {
		
		return organizationsClient.getClientsClient().getClientsApiRegistryClient().getXmlApiRegistry(organizationId, clientId, version, download);
	}
	
	@Test
	public List<ClientContract> listAllContractsForClient(String organizationId, String clientId, String version) {
		
		return organizationsClient.getClientsClient().getClientsContractsClient().listAllContractsForClient(organizationId, clientId, version);
	}
	
	@Test
	public ClientContract creareApiContract(String organizationId, String clientId, String version, ClientContract apiContract) {
		
		return organizationsClient.getClientsClient().getClientsContractsClient().creareApiContract(organizationId, clientId, version, apiContract);
	}
	
	@Test
	public void breakAllContracts(String organizationId, String clientId, String version) {
		
		organizationsClient.getClientsClient().getClientsContractsClient().breakAllContracts(organizationId, clientId, version);
	}
	
	@Test
	public ClientContract getApiContract(String organizationId, String clientId, String version, String contractId) {
		
		return organizationsClient.getClientsClient().getClientsContractsClient().getApiContract(organizationId, clientId, version, contractId);
	}
	
	@Test
	public void breakContract(String organizationId, String clientId, String version, String contractId) {
		
		organizationsClient.getClientsClient().getClientsContractsClient().breakContract(organizationId, clientId, version, contractId);
	}
	
	@Test
	public List<ClientPolicy> listAllClientPolicies(String organizationId, String clientId, String version) {
		
		return organizationsClient.getClientsClient().getClientsPoliciesClient().listAllClientPolicies(organizationId, clientId, version);
	}
	
	@Test
	public ClientPolicy addClientPolicy(String organizationId, String clientId, String version, ClientPolicy clientPolicy) {
		
		return organizationsClient.getClientsClient().getClientsPoliciesClient().addClientPolicy(organizationId, clientId, version, clientPolicy);
	}
	
	@Test
	public ClientPolicy getClientPolicy(String organizationId, String clientId, String version, String policyId) {
		
		return organizationsClient.getClientsClient().getClientsPoliciesClient().getClientPolicy(organizationId, clientId, version, policyId);
	}
	
	@Test
	public void updateClientPolicy(String organizationId, String clientId, String version, String policyId, ClientPolicy clientPolicy) {
		
		organizationsClient.getClientsClient().getClientsPoliciesClient().updateClientPolicy(organizationId, clientId, version, policyId, clientPolicy);
	}
	
	@Test
	public void removeClientPolicy(String organizationId, String clientId, String version, String policyId) {
		
		organizationsClient.getClientsClient().getClientsPoliciesClient().removeClientPolicy(organizationId, clientId, version, policyId);
	}
	
	@Test
	public List<OrganizationMember> listOrganizationMembers(String organizationId) {
		
		return organizationsClient.getMembersClient().listOrganizationMembers(organizationId);
	}
	
	@Test
	public void revokeAllMemberships(String organizationId, String userId) {
		
		organizationsClient.getMembersClient().revokeAllMemberships(organizationId, userId);
	}
	
	@Test
	public List<OrganizationPlan> listPlans(String organizationId) {
		
		return organizationsClient.getPlansClient().listPlans(organizationId);
	}
	
	@Test
	public OrganizationPlan createPlan(String organizationId, OrganizationPlan organizationPlan) {
		
		return organizationsClient.getPlansClient().createPlan(organizationId, organizationPlan);
	}
	
	@Test
	public OrganizationPlan getPlanById(String organizationId, String planId) {
		
		return organizationsClient.getPlansClient().getPlanById(organizationId, planId);
	}
	
	@Test
	public void updatePlan(String organizationId, String planId, OrganizationPlan organizationPlan) {
		
		organizationsClient.getPlansClient().updatePlan(organizationId, planId, organizationPlan);
	}
	
	@Test
	public void deletePlan(String organizationId, String planId) {
		
		organizationsClient.getPlansClient().deletePlan(organizationId, planId);
	}
	
	@Test
	public ActivityList getPlanActivity(String organizationId, String planId, int page, int count) {
		
		return organizationsClient.getPlansClient().getPlanActivity(organizationId, planId, page, count);
	}
	
	@Test
	public List<PlanVersion> listPlanVersions(String organizationId, String planId) {
		
		return organizationsClient.getPlansClient().listPlanVersions(organizationId, planId);
	}
	
	@Test
	public PlanVersion createPlanVersion(String organizationId, String planId, PlanVersion planVersion) {
		
		return organizationsClient.getPlansClient().createPlanVersion(organizationId, planId, planVersion);
	}
	
	@Test
	public PlanVersion getPlanVersion(String organizationId, String planId, String version) {
		
		return organizationsClient.getPlansClient().getPlanVersion(organizationId, planId, version);
	}
	
	@Test
	public ActivityList getPlanVersionActivity(String organizationId, String planId, String version, int page, int count) {
		
		return organizationsClient.getPlansClient().getPlanVersionActivity(organizationId, planId, version, page, count);
	}
	
	@Test
	public void reorderPlanPolicies(String organizationId, String planId, String version, ReOrderPolicies reOrderPolicies) {
		
		organizationsClient.getPlansClient().reorderPlanPolicies(organizationId, planId, version, reOrderPolicies);
	}
	
	@Test
	public List<Policy> listAllPlanPolicies(String organizationId, String planId, String version) {
		
		return organizationsClient.getPlansClient().getPlansPoliciesClient().listAllPlanPolicies(organizationId, planId, version);
	}
	
	@Test
	public Policy addPlanPolicy(String organizationId, String planId, String version, Policy planPolicy) {
		
		return organizationsClient.getPlansClient().getPlansPoliciesClient().addPlanPolicy(organizationId, planId, version, planPolicy);
	}
	
	@Test
	public Policy getPlanPolicy(String organizationId, String planId, String version, String policyId) {
		
		return organizationsClient.getPlansClient().getPlansPoliciesClient().getPlanPolicy(organizationId, planId, version, policyId);
	}
	
	@Test
	public void updatePlanPolicy(String organizationId, String planId, String version, String policyId, Policy planPolicy) {
		
		organizationsClient.getPlansClient().getPlansPoliciesClient().updatePlanPolicy(organizationId, planId, version, policyId, planPolicy);
	}
	
	@Test
	public void removePlanPolicy(String organizationId, String planId, String version, String policyId) {
		
		organizationsClient.getPlansClient().getPlansPoliciesClient().removePlanPolicy(organizationId, planId, version, policyId);
	}
	
	@Test
	public void grantMembership(String organizationId, GrantMemberships grantMemberships) {
		
		organizationsClient.getRolesClient().grantMembership(organizationId, grantMemberships);
	}
	
	@Test
	public void revokeSingleMembership(String organizationId, String roleId, String userId) {
		
		organizationsClient.getRolesClient().revokeSingleMembership(organizationId, roleId, userId);
	}
	
	@Test
	public PermissionsList getCurrentUserPermissions() {
		
		return permissionsClient.getCurrentUserPermissions();
	}
	
	@Test
	public PermissionsList getUserPermissions(String userId) {
		
		return permissionsClient.getUserPermissions(userId);
	}
	
	@Test
	public List<Plugin> listAllPlugins() {
		
		return pluginsClient.listAllPlugins();
	}
	
	@Test
	public Plugin addPlugin(Plugin plugin) {
		
		return pluginsClient.addPlugin(plugin);
	}
	
	@Test
	public List<Plugin> listAvailablePlugins() {
		
		return pluginsClient.listAvailablePlugins();
	}
	
	@Test
	public Plugin getPluginById(String pluginId) {
		
		return pluginsClient.getPluginById(pluginId);
	}
	
	@Test
	public void deletePluginById(String pluginId) {
		
		pluginsClient.deletePluginById(pluginId);
	}
	
	@Test
	public List<PolicyDefinition> getPluginPolicyDefinitions(String pluginId) {
		
		return pluginsClient.getPluginsPolicyDefsClient().getPluginPolicyDefinitions(pluginId);
	}
	
	@Test
	public String getPluginPolicyForm(String pluginId, String policyDefId) {
		
		return pluginsClient.getPluginsPolicyDefsClient().getPluginPolicyForm(pluginId, policyDefId);
	}
	
	@Test
	public List<PolicyDefinition> listPolicyDefinitions() {
		
		return policyDefsClient.listPolicyDefinitions();
	}
	
	@Test
	public PolicyDefinition addPolicyDefinition(PolicyDefinition policyDefinition) {
		
		return policyDefsClient.addPolicyDefinition(policyDefinition);
	}
	
	@Test
	public PolicyDefinition getPolicyDefinitionById(String policyDefinitionId) {
		
		return policyDefsClient.getPolicyDefinitionById(policyDefinitionId);
	}
	
	@Test
	public void updatePolicyDefinition(String policyDefinitionId, PolicyDefinition policyDefinition) {
		
		policyDefsClient.updatePolicyDefinition(policyDefinitionId, policyDefinition);
	}
	
	@Test
	public void deletePolicyDefinition(String policyDefinitionId) {
		
		policyDefsClient.deletePolicyDefinition(policyDefinitionId);
	}
	
	@Test
	public List<Role> listAllRoles() {
		
		return rolesClient.listAllRoles();
	}
	
	@Test
	public Role createRole(Role role) {
		
		return rolesClient.createRole(role);
	}
	
	@Test
	public SearchResult searchForRoles(SearchQuery rolesSearchQuery) {
		
		return rolesClient.searchForRoles(rolesSearchQuery);
	}
	
	@Test
	public Role getRoleById(String roleId) {
		
		return rolesClient.getRoleById(roleId);
	}
	
	@Test
	public void updateRoleById(String roleId, Role role) {
		
		rolesClient.updateRoleById(roleId, role);
	}
	
	@Test
	public void deleteRoleById(String roleId) {
		
		rolesClient.deleteRoleById(roleId);
	}
	
	@Test
	public SearchResult searchForApis(SearchQuery apisSearchQuery) {
		
		return searchClient.searchForApis(apisSearchQuery);
	}
	
	@Test
	public SearchResult searchForClients(SearchQuery clientsSearchQuery) {
		
		return searchClient.searchForClients(clientsSearchQuery);
	}
	
	@Test
	public SearchResult searchForOrganizations(SearchQuery organizationsSearchQuery) {
		
		return searchClient.searchForOrganizations(organizationsSearchQuery);
	}
	
	@Test
	public SearchResult searchForApisInApiCatalogue() {
		
		return searchClient.getApiCatalogueSearchClient().searchForApisInApiCatalogue();
	}
	
	@Test
	public List<Namespace> listAllNamespacesInApiCatalogue() {
		
		return searchClient.getApiCatalogueSearchClient().listAllNamespacesInApiCatalogue();
	}
	
	@Test
	public void exportData(String download) {
		
		systemClient.exportData(download);
	}
	
	@Test
	public void importData() {
		
		systemClient.importData();
	}
	
	@Test
	public SystemStatus getSystemStatus() {
		
		return systemClient.getSystemStatus();
	}
	
	@Test
	public SearchResult searchForUsers(SearchQuery userSearchQuery) {
		
		return usersClient.searchForUsers(userSearchQuery);
	}
	
	@Test
	public User getUserById(String userId) {
		
		return usersClient.getUserById(userId);
	}
	
	@Test
	public void updateUserById(String userId, User user) {
		
		usersClient.updateUserById(userId, user);
	}
	
	@Test
	public ActivityList getUserActivity(String userId, int page, int count) {
		
		return usersClient.getUserActivity(userId, page, count);
	}
	
	@Test
	public List<Api> listUserApis(String userId) {
		
		return usersClient.listUserApis(userId);
	}
	
	@Test
	public List<Client> listUserClients(String userId) {
		
		return usersClient.listUserClients(userId);
	}
	
	@Test
	public List<Organization> listUserOrganizations(String userId) {
		
		return usersClient.listUserOrganizations(userId);
	}
	*/
}
