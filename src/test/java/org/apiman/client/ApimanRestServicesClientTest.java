package org.apiman.client;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.apiman.client.domain.Action;
import org.apiman.client.domain.Api;
import org.apiman.client.domain.ApiDefinitionUrl;
import org.apiman.client.domain.ApiKey;
import org.apiman.client.domain.Client;
import org.apiman.client.domain.ClientContract;
import org.apiman.client.domain.ClientPolicy;
import org.apiman.client.domain.ClientVersion;
import org.apiman.client.domain.CreateApi;
import org.apiman.client.domain.CreateApiVersion;
import org.apiman.client.domain.GrantMemberships;
import org.apiman.client.domain.Organization;
import org.apiman.client.domain.OrganizationPlan;
import org.apiman.client.domain.PlanVersion;
import org.apiman.client.domain.Policy;
import org.apiman.client.domain.ReOrderPolicies;
import org.apiman.client.domain.User;
import org.apiman.client.domain.UserInformation;
import org.apiman.client.domain.search.SearchQuery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:apiman-client-applicationContext.xml")
@Slf4j
public class ApimanRestServicesClientTest extends ApimanServiceTestBase {

	@Autowired
	@Qualifier("redhatApimanRestClient")
	private RestTemplate restTemplate;
	
	@Autowired
	private ApimanRestServicesClient service;

	private MockRestServiceServer mockServer;

	@Before
	public void before() {
		RestGatewaySupport gateway = new RestGatewaySupport();
		gateway.setRestTemplate(restTemplate);
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void executeEntityAction() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService      : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case    : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.executeEntityAction(new Action());

		mockServer.verify();
	}

	@Test
	public void getApiOrganizations() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiOrganizations();

		mockServer.verify();
	}

	@Test
	public void getCurrentUserApis() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getCurrentUserApis();

		mockServer.verify();
	}

	@Test
	public void getClientOrganizations() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getClientOrganizations();

		mockServer.verify();
	}

	@Test
	public void getCurrentUserClients() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getCurrentUserClients();

		mockServer.verify();
	}

	@Test
	public void getCurrentUserInformation() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getCurrentUserInformation();

		mockServer.verify();
	}

	@Test
	public void updateCurrentUserInformation() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateCurrentUserInformation(new UserInformation());

		mockServer.verify();
	}

	@Test
	public void getPlanOrganizations() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getPlanOrganizations();

		mockServer.verify();
	}

	@Test
	public void downloadFile() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.downloadFile(downloadId);

		mockServer.verify();
	}

	@Test
	public void listAllGateways() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listAllGateways();

		mockServer.verify();
	}

	@Test
	public void getGatewayById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getGatewayById(gatewayId);

		mockServer.verify();
	}

	@Test
	public void createOrganization() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createOrganization(new Organization());

		mockServer.verify();
	}

	@Test
	public void getOrganizationById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getOrganizationById(organizationId);

		mockServer.verify();
	}

	@Test
	public void updateOrganization() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateOrganization(organizationId, new Organization());

		mockServer.verify();
	}

	@Test
	public void deleteOrganization() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deleteOrganization(organizationId);

		mockServer.verify();
	}

	@Test
	public void getOrganizationActivity() {
		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getOrganizationActivity(organizationId, PAGE, COUNT);

		mockServer.verify();
	}

	@Test
	public void listApis() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listApis(organizationId);

		mockServer.verify();
	}

	@Test
	public void createApi() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createApi(organizationId, new CreateApi());

		mockServer.verify();
	}

	@Test
	public void getApiById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiById(organizationId, apiId);

		mockServer.verify();
	}

	@Test
	public void updateApi() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateApi(organizationId, apiId, new Api());

		mockServer.verify();
	}

	@Test
	public void deleteApi() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deleteApi(organizationId, apiId);

		mockServer.verify();
	}

	@Test
	public void getApiActivity() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiActivity(organizationId, apiId, PAGE, COUNT);

		mockServer.verify();
	}

	@Test
	public void listApiVersions() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listApiVersions(organizationId, apiId);

		mockServer.verify();
	}

	@Test
	public void createApiVersion() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createApiVersion(organizationId, apiId, new CreateApiVersion());

		mockServer.verify();
	}

	@Test
	public void getApiVersion() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiVersion(organizationId, apiId, version);

		mockServer.verify();
	}

	@Test
	public void updateApiVersion() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateApiVersion(organizationId, apiId, version, new CreateApiVersion());

		mockServer.verify();
	}

	@Test
	public void getApiVersionActivity() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiVersionActivity(organizationId, apiId, version, PAGE, COUNT);

		mockServer.verify();
	}

	@Test
	public void listApiContracts() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listApiContracts(organizationId, apiId, version, PAGE, COUNT);

		mockServer.verify();
	}

	@Test
	public void getApiDefinition() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiDefinition(organizationId, apiId, version);

		mockServer.verify();
	}

	@Test
	public void updateApiDefinition() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateApiDefinition(organizationId, apiId, version, apiDefinition);

		mockServer.verify();
	}

	@Test
	public void updateApiDefinitionFromUrl() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateApiDefinitionFromUrl(organizationId, apiId, version, new ApiDefinitionUrl());

		mockServer.verify();
	}

	@Test
	public void removeApiDefinition() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.removeApiDefinition(organizationId, apiId, version);

		mockServer.verify();
	}

	@Test
	public void getApiEndpoint() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiEndpoint(organizationId, apiId, version);

		mockServer.verify();
	}

	@Test
	public void reOrderApiPolicies() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.reOrderApiPolicies(organizationId, apiId, version, new ReOrderPolicies());

		mockServer.verify();
	}

	@Test
	public void getApiVersionStatus() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiVersionStatus(organizationId, apiId, version);

		mockServer.verify();
	}

	@Test
	public void getApiResponseStatisticsPerClient() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiResponseStatisticsPerClient(organizationId, apiId, version, FROM_DATE, TO_DATE);

		mockServer.verify();
	}

	@Test
	public void getApiUsageMetricsPerClient() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiUsageMetricsPerClient(organizationId, apiId, version, FROM_DATE, TO_DATE);

		mockServer.verify();
	}

	@Test
	public void getApiResponseStatisticsPerPlan() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiResponseStatisticsPerPlan(organizationId, apiId, version, FROM_DATE, TO_DATE);

		mockServer.verify();
	}

	@Test
	public void getApiUsageMetricsPerPlan() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiUsageMetricsPerPlan(organizationId, apiId, version, FROM_DATE, TO_DATE);

		mockServer.verify();
	}

	@Test
	public void getApiResponseStatistics() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiResponseStatistics(organizationId, apiId, version, FROM_DATE, TO_DATE);

		mockServer.verify();
	}

	//@Test
	@Ignore
	public void getApiResponseStatisticsSummary() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiResponseStatisticsSummary(organizationId, apiId, version, FROM_DATE, TO_DATE);

		mockServer.verify();
	}

	@Test
	public void getApiUsageMetrics() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiUsageMetrics(organizationId, apiId, version, FROM_DATE, TO_DATE,
				INTERVAL_DAY.toString().toLowerCase());

		mockServer.verify();
	}

	@Test
	public void listApiPlans() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listApiPlans(organizationId, apiId, version);

		mockServer.verify();
	}

	@Test
	public void getApiPolicyChain() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiPolicyChain(organizationId, apiId, version, planId);

		mockServer.verify();
	}

	@Test
	public void listAllApiPolicies() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listAllApiPolicies(organizationId, apiId, version);

		mockServer.verify();
	}

	@Test
	public void addApiPolicy() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.addApiPolicy(organizationId, apiId, version, new Policy());

		mockServer.verify();
	}

	@Test
	public void getApiPolicy() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiPolicy(organizationId, apiId, version, policyId);

		mockServer.verify();
	}

	@Test
	public void updateApiPolicy() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateApiPolicy(organizationId, apiId, version, policyId, new Policy());

		mockServer.verify();
	}

	@Test
	public void removeApiPolicy() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.removeApiPolicy(organizationId, apiId, version, policyId);

		mockServer.verify();
	}

	@Test
	public void listClients() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listClients(organizationId);

		mockServer.verify();
	}

	@Test
	public void createClient() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createClient(organizationId, new Client());

		mockServer.verify();
	}

	@Test
	public void getClientById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getClientById(organizationId, clientId);

		mockServer.verify();
	}

	@Test
	public void updateClient() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateClient(organizationId, clientId, new Client());

		mockServer.verify();
	}

	@Test
	public void deleteClient() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deleteClient(organizationId, clientId);

		mockServer.verify();
	}

	@Test
	public void getClientActivity() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getClientActivity(organizationId, clientId, PAGE, COUNT);

		mockServer.verify();
	}

	@Test
	public void listClientVersions() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listClientVersions(organizationId, clientId);

		mockServer.verify();
	}

	@Test
	public void createClientVersion() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createClientVersion(organizationId, clientId, new ClientVersion());

		mockServer.verify();
	}

	@Test
	public void getClientVersion() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getClientVersion(organizationId, clientId, version);

		mockServer.verify();
	}

	@Test
	public void getClientVersionActivity() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getClientVersionActivity(organizationId, clientId, version, PAGE, COUNT);

		mockServer.verify();
	}

	@Test
	public void getApiKey() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiKey(organizationId, clientId, version);

		mockServer.verify();
	}

	@Test
	public void updateApiKey() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateApiKey(organizationId, clientId, version, new ApiKey());

		mockServer.verify();
	}

	@Test
	public void getClientUsageMetricsPerApi() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getClientUsageMetricsPerApi(organizationId, clientId, version, FROM_DATE, TO_DATE);

		mockServer.verify();
	}

	@Test
	public void reorderClientPolicies() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.reorderClientPolicies(organizationId, clientId, version, new ReOrderPolicies());

		mockServer.verify();
	}

	@Test
	public void getJsonApiRegistry() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getJsonApiRegistry(organizationId, clientId, version, download);

		mockServer.verify();
	}

	@Test
	public void getXmlApiRegistry() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getXmlApiRegistry(organizationId, clientId, version, download);

		mockServer.verify();
	}

	@Test
	public void listAllContractsForClient() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listAllContractsForClient(organizationId, clientId, version);

		mockServer.verify();
	}

	@Test
	public void createApiContract() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createApiContract(organizationId, clientId, version, new ClientContract());

		mockServer.verify();
	}

	@Test
	public void breakAllContracts() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.breakAllContracts(organizationId, clientId, version);

		mockServer.verify();
	}

	@Test
	public void getApiContract() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getApiContract(organizationId, clientId, version, contractId);

		mockServer.verify();
	}

	@Test
	public void breakContract() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.breakContract(organizationId, clientId, version, contractId);

		mockServer.verify();
	}

	@Test
	public void listAllClientPolicies() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listAllClientPolicies(organizationId, clientId, version);

		mockServer.verify();
	}

	@Test
	public void addClientPolicy() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.addClientPolicy(organizationId, clientId, version, new ClientPolicy());

		mockServer.verify();
	}

	@Test
	public void getClientPolicy() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getClientPolicy(organizationId, clientId, version, policyId);

		mockServer.verify();
	}

	@Test
	public void updateClientPolicy() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateClientPolicy(organizationId, clientId, version, policyId, new ClientPolicy());

		mockServer.verify();
	}

	@Test
	public void removeClientPolicy() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.removeClientPolicy(organizationId, clientId, version, policyId);

		mockServer.verify();
	}

	@Test
	public void listOrganizationMembers() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listOrganizationMembers(organizationId);

		mockServer.verify();
	}

	@Test
	public void revokeAllMemberships() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.revokeAllMemberships(organizationId, userId);

		mockServer.verify();
	}

	@Test
	public void listPlans() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listPlans(organizationId);

		mockServer.verify();
	}

	@Test
	public void createPlan() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createPlan(organizationId, new OrganizationPlan());

		mockServer.verify();
	}

	@Test
	public void getPlanById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getPlanById(organizationId, planId);

		mockServer.verify();
	}

	@Test
	public void updatePlan() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updatePlan(organizationId, planId, new OrganizationPlan());

		mockServer.verify();
	}

	@Test
	public void deletePlan() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deletePlan(organizationId, planId);

		mockServer.verify();
	}

	@Test
	public void getPlanActivity() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getPlanActivity(organizationId, planId, PAGE, COUNT);

		mockServer.verify();
	}

	@Test
	public void listPlanVersions() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listPlanVersions(organizationId, planId);

		mockServer.verify();
	}

	@Test
	public void createPlanVersion() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createPlanVersion(organizationId, planId, new PlanVersion());

		mockServer.verify();
	}

	@Test
	public void getPlanVersion() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getPlanVersion(organizationId, planId, version);

		mockServer.verify();
	}

	@Test
	public void getPlanVersionActivity() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getPlanVersionActivity(organizationId, planId, version, PAGE, COUNT);

		mockServer.verify();
	}

	@Test
	public void reorderPlanPolicies() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.reorderPlanPolicies(organizationId, planId, version, new ReOrderPolicies());

		mockServer.verify();
	}

	@Test
	public void listAllPlanPolicies() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listAllPlanPolicies(organizationId, planId, version);

		mockServer.verify();
	}

	@Test
	public void addPlanPolicy() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.addPlanPolicy(organizationId, planId, version, new Policy());

		mockServer.verify();
	}

	@Test
	public void getPlanPolicy() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getPlanPolicy(organizationId, planId, version, policyId);

		mockServer.verify();
	}

	@Test
	public void updatePlanPolicy() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updatePlanPolicy(organizationId, planId, version, policyId, new Policy());

		mockServer.verify();
	}

	@Test
	public void removePlanPolicy() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.removePlanPolicy(organizationId, planId, version, policyId);

		mockServer.verify();
	}

	@Test
	public void grantMembership() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.grantMembership(organizationId, new GrantMemberships());

		mockServer.verify();
	}

	@Test
	public void revokeSingleMembership() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.revokeSingleMembership(organizationId, roleId, userId);

		mockServer.verify();
	}

	@Test
	public void getCurrentUserPermissions() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getCurrentUserPermissions();

		mockServer.verify();
	}

	@Test
	public void listAllPlugins() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listAllPlugins();

		mockServer.verify();
	}

	@Test
	public void listAvailablePlugins() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listAvailablePlugins();

		mockServer.verify();
	}

	@Test
	public void getPluginPolicyDefinitions() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getPluginPolicyDefinitions(pluginId);

		mockServer.verify();
	}

	@Test
	public void getPluginPolicyForm() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getPluginPolicyForm(pluginId, policyDefId);

		mockServer.verify();
	}

	@Test
	public void listPolicyDefinitions() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listPolicyDefinitions();

		mockServer.verify();
	}

	@Test
	public void getPolicyDefinitionById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getPolicyDefinitionById(policyDefId);

		mockServer.verify();
	}

	@Test
	public void listAllRoles() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listAllRoles();

		mockServer.verify();
	}

	@Test
	public void searchForRoles() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.searchForRoles(new SearchQuery());

		mockServer.verify();
	}

	@Test
	public void getRoleById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getRoleById(roleId);

		mockServer.verify();
	}

	@Test
	public void searchForApis() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.searchForApis(new SearchQuery());

		mockServer.verify();
	}

	@Test
	public void searchForClients() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.searchForClients(new SearchQuery());

		mockServer.verify();
	}

	@Test
	public void searchForOrganizations() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.searchForOrganizations(new SearchQuery());

		mockServer.verify();
	}

	@Test
	public void searchForApisInApiCatalogue() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.searchForApisInApiCatalogue(new SearchQuery());

		mockServer.verify();
	}

	@Test
	public void listAllNamespacesInApiCatalogue() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listAllNamespacesInApiCatalogue();

		mockServer.verify();
	}
	
	@Test
	public void searchForUsers() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.searchForUsers(new SearchQuery());

		mockServer.verify();
	}

	@Test
	public void getUserById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getUserById(userId);

		mockServer.verify();
	}

	@Test
	public void updateUserById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateUserById(userId, new User());

		mockServer.verify();
	}

	@Test
	public void getUserActivity() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getUserActivity(userId, PAGE, COUNT);

		mockServer.verify();
	}

	@Test
	public void listUserApis() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listUserApis(userId);

		mockServer.verify();
	}

	@Test
	public void listUserClients() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listUserClients(userId);

		mockServer.verify();
	}

	@Test
	public void listUserOrganizations() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.listUserOrganizations(userId);

		mockServer.verify();
	}
}
