package org.apiman.client;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.apiman.client.domain.Gateway;
import org.apiman.client.domain.Plugin;
import org.apiman.client.domain.PolicyDefinition;
import org.apiman.client.domain.Role;
import org.junit.Before;
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
public class ApimanAdminRestServicesClientTest extends ApimanServiceTestBase {

	@Autowired
	@Qualifier("redhatApimanAdminRestClient")
	private RestTemplate adminTemplate;

	@Autowired
	private ApimanAdminRestServicesClient service;

	private MockRestServiceServer mockAdminServer;

	@Before
	public void before() {
		RestGatewaySupport adminGateway = new RestGatewaySupport();
		adminGateway.setRestTemplate(adminTemplate);
		mockAdminServer = MockRestServiceServer.createServer(adminTemplate);
	}

	@Test
	public void testGateway() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.testGateway(new Gateway());

		mockAdminServer.verify();
	}

	@Test
	public void createGateway() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createGateway(new Gateway());

		mockAdminServer.verify();
	}

	@Test
	public void updateGateway() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateGateway(gatewayId, new Gateway());

		mockAdminServer.verify();
	}

	@Test
	public void deleteGateway() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deleteGateway(gatewayId);

		mockAdminServer.verify();
	}

	@Test
	public void getUserPermissions() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getUserPermissions(userId);

		mockAdminServer.verify();
	}

	@Test
	public void addPlugin() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.addPlugin(new Plugin());

		mockAdminServer.verify();
	}

	@Test
	public void getPluginById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getPluginById(pluginId);

		mockAdminServer.verify();
	}

	@Test
	public void deletePluginById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deletePluginById(pluginId);

		mockAdminServer.verify();
	}

	@Test
	public void addPolicyDefinition() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.addPolicyDefinition(new PolicyDefinition());

		mockAdminServer.verify();
	}

	@Test
	public void updatePolicyDefinition() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updatePolicyDefinition(policyDefId, new PolicyDefinition());

		mockAdminServer.verify();
	}

	@Test
	public void deletePolicyDefinition() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deletePolicyDefinition(policyDefId);

		mockAdminServer.verify();
	}

	@Test
	public void createRole() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createRole(new Role());

		mockAdminServer.verify();
	}

	@Test
	public void updateRoleById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateRoleById(roleId, new Role());

		mockAdminServer.verify();
	}

	@Test
	public void deleteRoleById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deleteRoleById(roleId);

		mockAdminServer.verify();
	}

	@Test
	public void exportData() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.exportData(download);

		mockAdminServer.verify();
	}

	@Test
	public void importData() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.importData();

		mockAdminServer.verify();
	}

	@Test
	public void getSystemStatus() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockAdminServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getSystemStatus();

		mockAdminServer.verify();
	}
}
