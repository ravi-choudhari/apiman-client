package org.apiman.client;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.apiman.client.domain.Gateway;
import org.apiman.client.domain.GrantMemberships;
import org.apiman.client.domain.Organization;
import org.apiman.client.domain.OrganizationPlan;
import org.apiman.client.domain.PlanVersion;
import org.apiman.client.domain.Plugin;
import org.apiman.client.domain.Policy;
import org.apiman.client.domain.PolicyDefinition;
import org.apiman.client.domain.ReOrderPolicies;
import org.apiman.client.domain.Role;
import org.apiman.client.domain.User;
import org.apiman.client.domain.UserInformation;
import org.apiman.client.domain.search.SearchQuery;
import org.apiman.client.util.GenericUtils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
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

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:apiman-client-applicationContext.xml")
@Slf4j
public class ApimanRestServicesClientTest {

	@Autowired
	@Qualifier("redhatApimanRestClient")
	private RestTemplate restTemplate;

	@Value(value = "${apiman.url:http://localhost:8080/apiman}")
	protected String apimanUrl;

	@Autowired
	private ApimanRestServicesClient service;

	private MockRestServiceServer mockServer;

	private static final int PAGE = 10;
	private static final int COUNT = 20;
	private static final String PAGE_AND_COUNT = "?page=" + PAGE + "&count=" + COUNT;

	private static String downloadId = "download file";
	private static String apiId = "my best api";
	private static String clientId = "best client";
	private static String version = "version 1.1";
	private static String gatewayId = "gateway 1";
	private static String organizationId = "organization 1";
	private static String userId = "userId 1";
	private static String apiDefinition = "";
	private static String planId = "plan 1";
	private static String policyId = "policy 1";
	private static String contractId = "contract 1";
	private static String roleId = "role 1";
	private static String policyDefId = "policy Definition 1";
	private static String pluginId = "plugin 1";

	private static final String download = String.valueOf(true);
	private static final Date FROM_DATE = new Date();
	private static final Date TO_DATE = new Date();
	private static final String FROM_TO_DATES = "?from="
			+ GenericUtils.formatDate(FROM_DATE != null ? FROM_DATE : new Date()) + "&to="
			+ GenericUtils.formatDate(TO_DATE != null ? TO_DATE : new Date());

	private static final GenericUtils.INTERVAL INTERVAL_DAY = GenericUtils.INTERVAL.DAY;
	private static final String INTERVAL = "interval=" + INTERVAL_DAY.toString().toLowerCase();

	private static final Map<String, String> valuesMap = new HashMap<String, String>();
	private static final Map<String, String> urlsMap = new HashMap<String, String>();

	private static String GET_API_RESPONSE_STATISTICS_PER_CLIENT = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/metrics/clientResponseStats"
			+ FROM_TO_DATES;
	private static String EXECUTE_ENTITY_ACTION = "/actions";
	private static String GET_API_ORGANIZATIONS = "/currentuser/apiorgs";
	private static String GET_CURRENT_USER_APIS = "/currentuser/apis";
	private static String GET_CLIENT_ORGANIZATIONS = "/currentuser/clientorgs";
	private static String GET_CURRENT_USER_CLIENTS = "/currentuser/clients";
	private static String GET_CURRENT_USER_INFORMATION = "/currentuser/info";
	private static String UPDATE_CURRENT_USER_INFORMATION = "/currentuser/info";
	private static String GET_PLAN_ORGANIZATIONS = "/currentuser/planorgs";
	private static String CREATE_ORGANIZATION = "/organizations";
	private static String GET_ORGANIZATION_BY_ID = "/organizations/${organizationId}";
	private static String UPDATE_ORGANIZATION = "/organizations/${organizationId}";
	private static String DELETE_ORGANIZATION = "/organizations/${organizationId}";
	private static String GET_ORGANIZATION_ACTIVITY = "/organizations/${organizationId}/activity" + PAGE_AND_COUNT;
	private static String GET_API_VERSION_ACTIVITY = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/activity"
			+ PAGE_AND_COUNT;
	private static String UPDATE_API_DEFINITION = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/definition";
	private static String UPDATE_API_DEFINITION_FROM_URL = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/definition";
	private static String REMOVE_API_DEFINITION = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/definition";
	private static String RE_ORDER_API_POLICIES = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/reorderPolicies";
	private static String GET_API_VERSION_STATUS = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/status";
	private static String GET_API_USAGE_METRICS_PER_CLIENT = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/metrics/clientUsage"
			+ FROM_TO_DATES;
	private static String GET_API_RESPONSE_STATISTICS_PER_PLAN = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/metrics/planResponseStats"
			+ FROM_TO_DATES;
	private static String GET_API_USAGE_METRICS_PER_PLAN = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/metrics/planUsage"
			+ FROM_TO_DATES;
	private static String GET_API_RESPONSE_STATISTICS = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/metrics/responseStats"
			+ FROM_TO_DATES;
	//private static String GET_API_RESPONSE_STATISTICS_SUMMARY = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/metrics/summaryResponseStats"
	//		+ FROM_TO_DATES + "&" + INTERVAL;
	private static String GET_API_USAGE_METRICS = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/metrics/usage"
			+ FROM_TO_DATES + "&" + INTERVAL;
	private static String GET_API_POLICY_CHAIN = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/plans/${planId}/policyChain";
	private static String DOWNLOAD_FILE = "/downloads/${downloadId}";
	private static String LIST_ALL_GATEWAYS = "/gateways";
	private static String TEST_GATEWAY = "/gateways";
	private static String CREATE_GATEWAY = "/gateways";
	private static String GET_GATEWAY_BY_ID = "/gateways/${gatewayId}";
	private static String UPDATE_GATEWAY = "/gateways/${gatewayId}";
	private static String DELETE_GATEWAY = "/gateways/${gatewayId}";
	private static String LIST_APIS = "/organizations/${organizationId}/apis";
	private static String CREATE_API = "/organizations/${organizationId}/apis";
	private static String GET_API_BY_ID = "/organizations/${organizationId}/apis/${apiId}";
	private static String UPDATE_API = "/organizations/${organizationId}/apis/${apiId}";
	private static String DELETE_API = "/organizations/${organizationId}/apis/${apiId}";
	private static String GET_API_ACTIVITY = "/organizations/${organizationId}/apis/${apiId}/activity" + PAGE_AND_COUNT;
	private static String LIST_API_VERSIONS = "/organizations/${organizationId}/apis/${apiId}/versions";
	private static String CREATE_API_VERSION = "/organizations/${organizationId}/apis/${apiId}/versions";
	private static String GET_API_VERSION = "/organizations/${organizationId}/apis/${apiId}/versions/${version}";
	private static String UPDATE_API_VERSION = "/organizations/${organizationId}/apis/${apiId}/versions/${version}";
	private static String LIST_API_CONTRACTS = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/contracts" + PAGE_AND_COUNT;
	private static String GET_API_DEFINITION = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/definition";
	private static String GET_API_ENDPOINT = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/endpoint";
	private static String LIST_API_PLANS = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/plans";
	private static String ADD_API_POLICY = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/policies";
	private static String GET_API_POLICY = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/policies/${policyId}";
	private static String UPDATE_API_POLICY = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/policies/${policyId}";
	private static String REMOVE_API_POLICY = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/policies/${policyId}";
	private static String LIST_CLIENTS = "/organizations/${organizationId}/clients";
	private static String CREATE_CLIENT = "/organizations/${organizationId}/clients";
	private static String GET_CLIENT_BY_ID = "/organizations/${organizationId}/clients/${clientId}";
	private static String UPDATE_CLIENT = "/organizations/${organizationId}/clients/${clientId}";
	private static String DELETE_CLIENT = "/organizations/${organizationId}/clients/${clientId}";
	private static String GET_CLIENT_VERSION = "/organizations/${organizationId}/clients/${clientId}/versions/${version}";
	private static String GET_API_KEY = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/apikey";
	private static String UPDATE_API_KEY = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/apikey";
	private static String GET_API_CONTRACT = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/contracts/${contractId}";
	private static String BREAK_CONTRACT = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/contracts/${contractId}";
	private static String ADD_CLIENT_POLICY = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/policies";
	private static String GET_CLIENT_POLICY = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/policies/${policyId}";
	private static String LIST_PLANS = "/organizations/${organizationId}/plans";
	private static String CREATE_PLAN = "/organizations/${organizationId}/plans";
	private static String GET_PLAN_BY_ID = "/organizations/${organizationId}/plans/${planId}";
	private static String UPDATE_PLAN = "/organizations/${organizationId}/plans/${planId}";
	private static String DELETE_PLAN = "/organizations/${organizationId}/plans/${planId}";
	private static String GET_PLAN_ACTIVITY = "/organizations/${organizationId}/plans/${planId}/activity" + PAGE_AND_COUNT;
	private static String LIST_ALL_API_POLICIES = "/organizations/${organizationId}/apis/${apiId}/versions/${version}/policies";
	private static String GET_CLIENT_ACTIVITY = "/organizations/${organizationId}/clients/${clientId}/activity"
			+ PAGE_AND_COUNT;
	private static String LIST_CLIENT_VERSIONS = "/organizations/${organizationId}/clients/${clientId}/versions";
	private static String CREATE_CLIENT_VERSION = "/organizations/${organizationId}/clients/${clientId}/versions";
	private static String GET_CLIENT_VERSION_ACTIVITY = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/activity"
			+ PAGE_AND_COUNT;
	private static String GET_CLIENT_USAGE_METRICS_PER_API = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/metrics/apiUsage"
			+ FROM_TO_DATES;
	private static String REORDER_CLIENT_POLICIES = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/reorderPolicies";
	private static String GET_JSON_API_REGISTRY = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/apiregistry/json?download=${download}";
	private static String GET_XML_API_REGISTRY = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/apiregistry/xml?download=${download}";
	private static String LIST_ALL_CONTRACTS_FOR_CLIENT = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/contracts";
	private static String CREATE_API_CONTRACT = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/contracts";
	private static String BREAK_ALL_CONTRACTS = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/contracts";
	private static String LIST_ALL_CLIENT_POLICIES = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/policies";
	private static String UPDATE_CLIENT_POLICY = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/policies/${policyId}";
	private static String REMOVE_CLIENT_POLICY = "/organizations/${organizationId}/clients/${clientId}/versions/${version}/policies/${policyId}";
	private static String LIST_ORGANIZATION_MEMBERS = "/organizations/${organizationId}/members";
	private static String REVOKE_ALL_MEMBERSHIPS = "/organizations/${organizationId}/members/${userId}";
	private static String CREATE_PLAN_VERSION = "/organizations/${organizationId}/plans/${planId}/versions";
	private static String GET_PLAN_VERSION_ACTIVITY = "/organizations/${organizationId}/plans/${planId}/versions/${version}/activity"
			+ PAGE_AND_COUNT;
	private static String REORDER_PLAN_POLICIES = "/organizations/${organizationId}/plans/${planId}/versions/${version}/reorderPolicies";
	private static String LIST_ALL_PLAN_POLICIES = "/organizations/${organizationId}/plans/${planId}/versions/${version}/policies";
	private static String REVOKE_SINGLE_MEMBERSHIP = "/organizations/${organizationId}/roles/${roleId}/${userId}";
	private static String GET_CURRENT_USER_PERMISSIONS = "/permissions";
	private static String GET_USER_PERMISSIONS = "/permissions/${userId}";
	private static String LIST_AVAILABLE_PLUGINS = "/plugins/availablePlugins";
	private static String GET_PLUGIN_POLICY_DEFINITIONS = "/plugins/${pluginId}/policyDefs";
	private static String GET_PLUGIN_POLICY_FORM = "/plugins/${pluginId}/policyDefs/${policyDefId}/form";
	private static String LIST_POLICY_DEFINITIONS = "/policyDefs";
	private static String ADD_POLICY_DEFINITION = "/policyDefs";
	private static String GET_POLICY_DEFINITION_BY_ID = "/policyDefs/${policyDefId}";
	private static String UPDATE_POLICY_DEFINITION = "/policyDefs/${policyDefId}";
	private static String DELETE_POLICY_DEFINITION = "/policyDefs/${policyDefId}";
	private static String SEARCH_FOR_ORGANIZATIONS = "/search/organizations";
	private static String SEARCH_FOR_APIS_IN_API_CATALOGUE = "/search/apiCatalog/entries";
	private static String LIST_ALL_NAMESPACES_IN_API_CATALOGUE = "/search/apiCatalog/namespaces";
	private static String LIST_USER_ORGANIZATIONS = "/users/${userId}/organizations";
	private static String LIST_PLAN_VERSIONS = "/organizations/${organizationId}/plans/${planId}/versions";
	private static String GET_PLAN_VERSION = "/organizations/${organizationId}/plans/${planId}/versions/${version}";
	private static String ADD_PLAN_POLICY = "/organizations/${organizationId}/plans/${planId}/versions/${version}/policies";
	private static String GET_PLAN_POLICY = "/organizations/${organizationId}/plans/${planId}/versions/${version}/policies/${policyId}";
	private static String UPDATE_PLAN_POLICY = "/organizations/${organizationId}/plans/${planId}/versions/${version}/policies/${policyId}";
	private static String REMOVE_PLAN_POLICY = "/organizations/${organizationId}/plans/${planId}/versions/${version}/policies/${policyId}";
	private static String GRANT_MEMBERSHIP = "/organizations/${organizationId}/roles";
	private static String LIST_ALL_PLUGINS = "/plugins";
	private static String ADD_PLUGIN = "/plugins";
	private static String GET_PLUGIN_BY_ID = "/plugins/${pluginId}";
	private static String DELETE_PLUGIN_BY_ID = "/plugins/${pluginId}";
	private static String LIST_ALL_ROLES = "/roles";
	private static String CREATE_ROLE = "/roles";
	private static String SEARCH_FOR_ROLES = "/roles/search";
	private static String GET_ROLE_BY_ID = "/roles/${roleId}";
	private static String UPDATE_ROLE_BY_ID = "/roles/${roleId}";
	private static String DELETE_ROLE_BY_ID = "/roles/${roleId}";
	private static String SEARCH_FOR_APIS = "/search/apis";
	private static String SEARCH_FOR_CLIENTS = "/search/clients";
	private static String EXPORT_DATA = "/system/export?download=${download}";
	private static String IMPORT_DATA = "/system/import";
	private static String GET_SYSTEM_STATUS = "/system/status";
	private static String SEARCH_FOR_USERS = "/users/search";
	private static String GET_USER_BY_ID = "/users/${userId}";
	private static String UPDATE_USER_BY_ID = "/users/${userId}";
	private static String GET_USER_ACTIVITY = "/users/${userId}/activity" + PAGE_AND_COUNT;
	private static String LIST_USER_APIS = "/users/${userId}/apis";
	private static String LIST_USER_CLIENTS = "/users/${userId}/clients";

	@BeforeClass
	public static void beforeClass() throws UnsupportedEncodingException {
		valuesMap.put("downloadId", downloadId);
		valuesMap.put("apiId", apiId);
		valuesMap.put("clientId", clientId);
		valuesMap.put("version", version);
		valuesMap.put("gatewayId", gatewayId);
		valuesMap.put("organizationId", organizationId);
		valuesMap.put("userId", userId);
		valuesMap.put("planId", planId);
		valuesMap.put("contractId", contractId);
		valuesMap.put("roleId", roleId);
		valuesMap.put("policyId", policyId);
		valuesMap.put("policyDefId", policyDefId);
		valuesMap.put("pluginId", pluginId);
		valuesMap.put("download", download);

		GenericUtils.encode(valuesMap);

		GET_API_RESPONSE_STATISTICS_PER_CLIENT = GenericUtils.substitute(GET_API_RESPONSE_STATISTICS_PER_CLIENT,
				valuesMap, false);
		EXECUTE_ENTITY_ACTION = GenericUtils.substitute(EXECUTE_ENTITY_ACTION, valuesMap, false);
		GET_API_ORGANIZATIONS = GenericUtils.substitute(GET_API_ORGANIZATIONS, valuesMap, false);
		GET_CURRENT_USER_APIS = GenericUtils.substitute(GET_CURRENT_USER_APIS, valuesMap, false);
		GET_CLIENT_ORGANIZATIONS = GenericUtils.substitute(GET_CLIENT_ORGANIZATIONS, valuesMap, false);
		GET_CURRENT_USER_CLIENTS = GenericUtils.substitute(GET_CURRENT_USER_CLIENTS, valuesMap, false);
		GET_CURRENT_USER_INFORMATION = GenericUtils.substitute(GET_CURRENT_USER_INFORMATION, valuesMap, false);
		UPDATE_CURRENT_USER_INFORMATION = GenericUtils.substitute(UPDATE_CURRENT_USER_INFORMATION, valuesMap, false);
		GET_PLAN_ORGANIZATIONS = GenericUtils.substitute(GET_PLAN_ORGANIZATIONS, valuesMap, false);
		CREATE_ORGANIZATION = GenericUtils.substitute(CREATE_ORGANIZATION, valuesMap, false);
		GET_ORGANIZATION_BY_ID = GenericUtils.substitute(GET_ORGANIZATION_BY_ID, valuesMap, false);
		UPDATE_ORGANIZATION = GenericUtils.substitute(UPDATE_ORGANIZATION, valuesMap, false);
		DELETE_ORGANIZATION = GenericUtils.substitute(DELETE_ORGANIZATION, valuesMap, false);
		GET_ORGANIZATION_ACTIVITY = GenericUtils.substitute(GET_ORGANIZATION_ACTIVITY, valuesMap, false);
		GET_API_VERSION_ACTIVITY = GenericUtils.substitute(GET_API_VERSION_ACTIVITY, valuesMap, false);
		UPDATE_API_DEFINITION = GenericUtils.substitute(UPDATE_API_DEFINITION, valuesMap, false);
		UPDATE_API_DEFINITION_FROM_URL = GenericUtils.substitute(UPDATE_API_DEFINITION_FROM_URL, valuesMap, false);
		REMOVE_API_DEFINITION = GenericUtils.substitute(REMOVE_API_DEFINITION, valuesMap, false);
		RE_ORDER_API_POLICIES = GenericUtils.substitute(RE_ORDER_API_POLICIES, valuesMap, false);
		GET_API_VERSION_STATUS = GenericUtils.substitute(GET_API_VERSION_STATUS, valuesMap, false);
		GET_API_USAGE_METRICS_PER_CLIENT = GenericUtils.substitute(GET_API_USAGE_METRICS_PER_CLIENT, valuesMap, false);
		GET_API_RESPONSE_STATISTICS_PER_PLAN = GenericUtils.substitute(GET_API_RESPONSE_STATISTICS_PER_PLAN, valuesMap,
				false);
		GET_API_USAGE_METRICS_PER_PLAN = GenericUtils.substitute(GET_API_USAGE_METRICS_PER_PLAN, valuesMap, false);
		GET_API_RESPONSE_STATISTICS = GenericUtils.substitute(GET_API_RESPONSE_STATISTICS, valuesMap, false);
		//GET_API_RESPONSE_STATISTICS_SUMMARY = GenericUtils.substitute(GET_API_RESPONSE_STATISTICS_SUMMARY, valuesMap,
		//		false);
		GET_API_USAGE_METRICS = GenericUtils.substitute(GET_API_USAGE_METRICS, valuesMap, false);
		GET_API_POLICY_CHAIN = GenericUtils.substitute(GET_API_POLICY_CHAIN, valuesMap, false);
		DOWNLOAD_FILE = GenericUtils.substitute(DOWNLOAD_FILE, valuesMap, false);
		LIST_ALL_GATEWAYS = GenericUtils.substitute(LIST_ALL_GATEWAYS, valuesMap, false);
		TEST_GATEWAY = GenericUtils.substitute(TEST_GATEWAY, valuesMap, false);
		CREATE_GATEWAY = GenericUtils.substitute(CREATE_GATEWAY, valuesMap, false);
		GET_GATEWAY_BY_ID = GenericUtils.substitute(GET_GATEWAY_BY_ID, valuesMap, false);
		UPDATE_GATEWAY = GenericUtils.substitute(UPDATE_GATEWAY, valuesMap, false);
		DELETE_GATEWAY = GenericUtils.substitute(DELETE_GATEWAY, valuesMap, false);
		LIST_APIS = GenericUtils.substitute(LIST_APIS, valuesMap, false);
		CREATE_API = GenericUtils.substitute(CREATE_API, valuesMap, false);
		GET_API_BY_ID = GenericUtils.substitute(GET_API_BY_ID, valuesMap, false);
		UPDATE_API = GenericUtils.substitute(UPDATE_API, valuesMap, false);
		DELETE_API = GenericUtils.substitute(DELETE_API, valuesMap, false);
		GET_API_ACTIVITY = GenericUtils.substitute(GET_API_ACTIVITY, valuesMap, false);
		LIST_API_VERSIONS = GenericUtils.substitute(LIST_API_VERSIONS, valuesMap, false);
		CREATE_API_VERSION = GenericUtils.substitute(CREATE_API_VERSION, valuesMap, false);
		GET_API_VERSION = GenericUtils.substitute(GET_API_VERSION, valuesMap, false);
		UPDATE_API_VERSION = GenericUtils.substitute(UPDATE_API_VERSION, valuesMap, false);
		LIST_API_CONTRACTS = GenericUtils.substitute(LIST_API_CONTRACTS, valuesMap, false);
		GET_API_DEFINITION = GenericUtils.substitute(GET_API_DEFINITION, valuesMap, false);
		GET_API_ENDPOINT = GenericUtils.substitute(GET_API_ENDPOINT, valuesMap, false);
		LIST_API_PLANS = GenericUtils.substitute(LIST_API_PLANS, valuesMap, false);
		ADD_API_POLICY = GenericUtils.substitute(ADD_API_POLICY, valuesMap, false);
		GET_API_POLICY = GenericUtils.substitute(GET_API_POLICY, valuesMap, false);
		UPDATE_API_POLICY = GenericUtils.substitute(UPDATE_API_POLICY, valuesMap, false);
		REMOVE_API_POLICY = GenericUtils.substitute(REMOVE_API_POLICY, valuesMap, false);
		LIST_CLIENTS = GenericUtils.substitute(LIST_CLIENTS, valuesMap, false);
		CREATE_CLIENT = GenericUtils.substitute(CREATE_CLIENT, valuesMap, false);
		GET_CLIENT_BY_ID = GenericUtils.substitute(GET_CLIENT_BY_ID, valuesMap, false);
		UPDATE_CLIENT = GenericUtils.substitute(UPDATE_CLIENT, valuesMap, false);
		DELETE_CLIENT = GenericUtils.substitute(DELETE_CLIENT, valuesMap, false);
		GET_CLIENT_VERSION = GenericUtils.substitute(GET_CLIENT_VERSION, valuesMap, false);
		GET_API_KEY = GenericUtils.substitute(GET_API_KEY, valuesMap, false);
		UPDATE_API_KEY = GenericUtils.substitute(UPDATE_API_KEY, valuesMap, false);
		GET_API_CONTRACT = GenericUtils.substitute(GET_API_CONTRACT, valuesMap, false);
		BREAK_CONTRACT = GenericUtils.substitute(BREAK_CONTRACT, valuesMap, false);
		ADD_CLIENT_POLICY = GenericUtils.substitute(ADD_CLIENT_POLICY, valuesMap, false);
		GET_CLIENT_POLICY = GenericUtils.substitute(GET_CLIENT_POLICY, valuesMap, false);
		LIST_PLANS = GenericUtils.substitute(LIST_PLANS, valuesMap, false);
		CREATE_PLAN = GenericUtils.substitute(CREATE_PLAN, valuesMap, false);
		GET_PLAN_BY_ID = GenericUtils.substitute(GET_PLAN_BY_ID, valuesMap, false);
		UPDATE_PLAN = GenericUtils.substitute(UPDATE_PLAN, valuesMap, false);
		DELETE_PLAN = GenericUtils.substitute(DELETE_PLAN, valuesMap, false);
		GET_PLAN_ACTIVITY = GenericUtils.substitute(GET_PLAN_ACTIVITY, valuesMap, false);
		LIST_ALL_API_POLICIES = GenericUtils.substitute(LIST_ALL_API_POLICIES, valuesMap, false);
		GET_CLIENT_ACTIVITY = GenericUtils.substitute(GET_CLIENT_ACTIVITY, valuesMap, false);
		LIST_CLIENT_VERSIONS = GenericUtils.substitute(LIST_CLIENT_VERSIONS, valuesMap, false);
		CREATE_CLIENT_VERSION = GenericUtils.substitute(CREATE_CLIENT_VERSION, valuesMap, false);
		GET_CLIENT_VERSION_ACTIVITY = GenericUtils.substitute(GET_CLIENT_VERSION_ACTIVITY, valuesMap, false);
		GET_CLIENT_USAGE_METRICS_PER_API = GenericUtils.substitute(GET_CLIENT_USAGE_METRICS_PER_API, valuesMap, false);
		REORDER_CLIENT_POLICIES = GenericUtils.substitute(REORDER_CLIENT_POLICIES, valuesMap, false);
		GET_JSON_API_REGISTRY = GenericUtils.substitute(GET_JSON_API_REGISTRY, valuesMap, false);
		GET_XML_API_REGISTRY = GenericUtils.substitute(GET_XML_API_REGISTRY, valuesMap, false);
		LIST_ALL_CONTRACTS_FOR_CLIENT = GenericUtils.substitute(LIST_ALL_CONTRACTS_FOR_CLIENT, valuesMap, false);
		CREATE_API_CONTRACT = GenericUtils.substitute(CREATE_API_CONTRACT, valuesMap, false);
		BREAK_ALL_CONTRACTS = GenericUtils.substitute(BREAK_ALL_CONTRACTS, valuesMap, false);
		LIST_ALL_CLIENT_POLICIES = GenericUtils.substitute(LIST_ALL_CLIENT_POLICIES, valuesMap, false);
		UPDATE_CLIENT_POLICY = GenericUtils.substitute(UPDATE_CLIENT_POLICY, valuesMap, false);
		REMOVE_CLIENT_POLICY = GenericUtils.substitute(REMOVE_CLIENT_POLICY, valuesMap, false);
		LIST_ORGANIZATION_MEMBERS = GenericUtils.substitute(LIST_ORGANIZATION_MEMBERS, valuesMap, false);
		REVOKE_ALL_MEMBERSHIPS = GenericUtils.substitute(REVOKE_ALL_MEMBERSHIPS, valuesMap, false);
		CREATE_PLAN_VERSION = GenericUtils.substitute(CREATE_PLAN_VERSION, valuesMap, false);
		GET_PLAN_VERSION_ACTIVITY = GenericUtils.substitute(GET_PLAN_VERSION_ACTIVITY, valuesMap, false);
		REORDER_PLAN_POLICIES = GenericUtils.substitute(REORDER_PLAN_POLICIES, valuesMap, false);
		LIST_ALL_PLAN_POLICIES = GenericUtils.substitute(LIST_ALL_PLAN_POLICIES, valuesMap, false);
		REVOKE_SINGLE_MEMBERSHIP = GenericUtils.substitute(REVOKE_SINGLE_MEMBERSHIP, valuesMap, false);
		GET_CURRENT_USER_PERMISSIONS = GenericUtils.substitute(GET_CURRENT_USER_PERMISSIONS, valuesMap, false);
		GET_USER_PERMISSIONS = GenericUtils.substitute(GET_USER_PERMISSIONS, valuesMap, false);
		LIST_AVAILABLE_PLUGINS = GenericUtils.substitute(LIST_AVAILABLE_PLUGINS, valuesMap, false);
		GET_PLUGIN_POLICY_DEFINITIONS = GenericUtils.substitute(GET_PLUGIN_POLICY_DEFINITIONS, valuesMap, false);
		GET_PLUGIN_POLICY_FORM = GenericUtils.substitute(GET_PLUGIN_POLICY_FORM, valuesMap, false);
		LIST_POLICY_DEFINITIONS = GenericUtils.substitute(LIST_POLICY_DEFINITIONS, valuesMap, false);
		ADD_POLICY_DEFINITION = GenericUtils.substitute(ADD_POLICY_DEFINITION, valuesMap, false);
		GET_POLICY_DEFINITION_BY_ID = GenericUtils.substitute(GET_POLICY_DEFINITION_BY_ID, valuesMap, false);
		UPDATE_POLICY_DEFINITION = GenericUtils.substitute(UPDATE_POLICY_DEFINITION, valuesMap, false);
		DELETE_POLICY_DEFINITION = GenericUtils.substitute(DELETE_POLICY_DEFINITION, valuesMap, false);
		SEARCH_FOR_ORGANIZATIONS = GenericUtils.substitute(SEARCH_FOR_ORGANIZATIONS, valuesMap, false);
		SEARCH_FOR_APIS_IN_API_CATALOGUE = GenericUtils.substitute(SEARCH_FOR_APIS_IN_API_CATALOGUE, valuesMap, false);
		LIST_ALL_NAMESPACES_IN_API_CATALOGUE = GenericUtils.substitute(LIST_ALL_NAMESPACES_IN_API_CATALOGUE, valuesMap,
				false);
		LIST_USER_ORGANIZATIONS = GenericUtils.substitute(LIST_USER_ORGANIZATIONS, valuesMap, false);
		LIST_PLAN_VERSIONS = GenericUtils.substitute(LIST_PLAN_VERSIONS, valuesMap, false);
		GET_PLAN_VERSION = GenericUtils.substitute(GET_PLAN_VERSION, valuesMap, false);
		ADD_PLAN_POLICY = GenericUtils.substitute(ADD_PLAN_POLICY, valuesMap, false);
		GET_PLAN_POLICY = GenericUtils.substitute(GET_PLAN_POLICY, valuesMap, false);
		UPDATE_PLAN_POLICY = GenericUtils.substitute(UPDATE_PLAN_POLICY, valuesMap, false);
		REMOVE_PLAN_POLICY = GenericUtils.substitute(REMOVE_PLAN_POLICY, valuesMap, false);
		GRANT_MEMBERSHIP = GenericUtils.substitute(GRANT_MEMBERSHIP, valuesMap, false);
		LIST_ALL_PLUGINS = GenericUtils.substitute(LIST_ALL_PLUGINS, valuesMap, false);
		ADD_PLUGIN = GenericUtils.substitute(ADD_PLUGIN, valuesMap, false);
		GET_PLUGIN_BY_ID = GenericUtils.substitute(GET_PLUGIN_BY_ID, valuesMap, false);
		DELETE_PLUGIN_BY_ID = GenericUtils.substitute(DELETE_PLUGIN_BY_ID, valuesMap, false);
		LIST_ALL_ROLES = GenericUtils.substitute(LIST_ALL_ROLES, valuesMap, false);
		CREATE_ROLE = GenericUtils.substitute(CREATE_ROLE, valuesMap, false);
		SEARCH_FOR_ROLES = GenericUtils.substitute(SEARCH_FOR_ROLES, valuesMap, false);
		GET_ROLE_BY_ID = GenericUtils.substitute(GET_ROLE_BY_ID, valuesMap, false);
		UPDATE_ROLE_BY_ID = GenericUtils.substitute(UPDATE_ROLE_BY_ID, valuesMap, false);
		DELETE_ROLE_BY_ID = GenericUtils.substitute(DELETE_ROLE_BY_ID, valuesMap, false);
		SEARCH_FOR_APIS = GenericUtils.substitute(SEARCH_FOR_APIS, valuesMap, false);
		SEARCH_FOR_CLIENTS = GenericUtils.substitute(SEARCH_FOR_CLIENTS, valuesMap, false);
		EXPORT_DATA = GenericUtils.substitute(EXPORT_DATA, valuesMap, false);
		IMPORT_DATA = GenericUtils.substitute(IMPORT_DATA, valuesMap, false);
		GET_SYSTEM_STATUS = GenericUtils.substitute(GET_SYSTEM_STATUS, valuesMap, false);
		SEARCH_FOR_USERS = GenericUtils.substitute(SEARCH_FOR_USERS, valuesMap, false);
		GET_USER_BY_ID = GenericUtils.substitute(GET_USER_BY_ID, valuesMap, false);
		UPDATE_USER_BY_ID = GenericUtils.substitute(UPDATE_USER_BY_ID, valuesMap, false);
		GET_USER_ACTIVITY = GenericUtils.substitute(GET_USER_ACTIVITY, valuesMap, false);
		LIST_USER_APIS = GenericUtils.substitute(LIST_USER_APIS, valuesMap, false);
		LIST_USER_CLIENTS = GenericUtils.substitute(LIST_USER_CLIENTS, valuesMap, false);

		urlsMap.put("getApiResponseStatisticsPerClient", GET_API_RESPONSE_STATISTICS_PER_CLIENT);
		urlsMap.put("downloadFile", DOWNLOAD_FILE);
		urlsMap.put("listAllGateways", LIST_ALL_GATEWAYS);
		urlsMap.put("testGateway", TEST_GATEWAY);
		urlsMap.put("createGateway", CREATE_GATEWAY);
		urlsMap.put("getGatewayById", GET_GATEWAY_BY_ID);
		urlsMap.put("executeEntityAction", EXECUTE_ENTITY_ACTION);
		urlsMap.put("getApiOrganizations", GET_API_ORGANIZATIONS);
		urlsMap.put("getCurrentUserApis", GET_CURRENT_USER_APIS);
		urlsMap.put("getClientOrganizations", GET_CLIENT_ORGANIZATIONS);
		urlsMap.put("getCurrentUserClients", GET_CURRENT_USER_CLIENTS);
		urlsMap.put("getCurrentUserInformation", GET_CURRENT_USER_INFORMATION);
		urlsMap.put("updateCurrentUserInformation", UPDATE_CURRENT_USER_INFORMATION);
		urlsMap.put("getPlanOrganizations", GET_PLAN_ORGANIZATIONS);
		urlsMap.put("createOrganization", CREATE_ORGANIZATION);
		urlsMap.put("getOrganizationById", GET_ORGANIZATION_BY_ID);
		urlsMap.put("updateOrganization", UPDATE_ORGANIZATION);
		urlsMap.put("deleteOrganization", DELETE_ORGANIZATION);
		urlsMap.put("getOrganizationActivity", GET_ORGANIZATION_ACTIVITY);
		urlsMap.put("getApiVersionActivity", GET_API_VERSION_ACTIVITY);
		urlsMap.put("updateApiDefinition", UPDATE_API_DEFINITION);
		urlsMap.put("updateApiDefinitionFromUrl", UPDATE_API_DEFINITION_FROM_URL);
		urlsMap.put("removeApiDefinition", REMOVE_API_DEFINITION);
		urlsMap.put("reOrderApiPolicies", RE_ORDER_API_POLICIES);
		urlsMap.put("getApiVersionStatus", GET_API_VERSION_STATUS);
		urlsMap.put("getApiUsageMetricsPerClient", GET_API_USAGE_METRICS_PER_CLIENT);
		urlsMap.put("getApiResponseStatisticsPerPlan", GET_API_RESPONSE_STATISTICS_PER_PLAN);
		urlsMap.put("getApiUsageMetricsPerPlan", GET_API_USAGE_METRICS_PER_PLAN);
		urlsMap.put("getApiResponseStatistics", GET_API_RESPONSE_STATISTICS);
		//urlsMap.put("getApiResponseStatisticsSummary", GET_API_RESPONSE_STATISTICS_SUMMARY);
		urlsMap.put("getApiUsageMetrics", GET_API_USAGE_METRICS);
		urlsMap.put("getApiPolicyChain", GET_API_POLICY_CHAIN);
		urlsMap.put("listAllApiPolicies", LIST_ALL_API_POLICIES);
		urlsMap.put("getClientActivity", GET_CLIENT_ACTIVITY);
		urlsMap.put("listClientVersions", LIST_CLIENT_VERSIONS);
		urlsMap.put("createClientVersion", CREATE_CLIENT_VERSION);
		urlsMap.put("getClientVersionActivity", GET_CLIENT_VERSION_ACTIVITY);
		urlsMap.put("getClientUsageMetricsPerApi", GET_CLIENT_USAGE_METRICS_PER_API);
		urlsMap.put("reorderClientPolicies", REORDER_CLIENT_POLICIES);
		urlsMap.put("getJsonApiRegistry", GET_JSON_API_REGISTRY);
		urlsMap.put("getXmlApiRegistry", GET_XML_API_REGISTRY);
		urlsMap.put("listAllContractsForClient", LIST_ALL_CONTRACTS_FOR_CLIENT);
		urlsMap.put("createApiContract", CREATE_API_CONTRACT);
		urlsMap.put("breakAllContracts", BREAK_ALL_CONTRACTS);
		urlsMap.put("listAllClientPolicies", LIST_ALL_CLIENT_POLICIES);
		urlsMap.put("updateClientPolicy", UPDATE_CLIENT_POLICY);
		urlsMap.put("updateGateway", UPDATE_GATEWAY);
		urlsMap.put("deleteGateway", DELETE_GATEWAY);
		urlsMap.put("listApis", LIST_APIS);
		urlsMap.put("createApi", CREATE_API);
		urlsMap.put("getApiById", GET_API_BY_ID);
		urlsMap.put("updateApi", UPDATE_API);
		urlsMap.put("deleteApi", DELETE_API);
		urlsMap.put("getApiActivity", GET_API_ACTIVITY);
		urlsMap.put("listApiVersions", LIST_API_VERSIONS);
		urlsMap.put("createApiVersion", CREATE_API_VERSION);
		urlsMap.put("getApiVersion", GET_API_VERSION);
		urlsMap.put("updateApiVersion", UPDATE_API_VERSION);
		urlsMap.put("listApiContracts", LIST_API_CONTRACTS);
		urlsMap.put("getApiDefinition", GET_API_DEFINITION);
		urlsMap.put("getApiEndpoint", GET_API_ENDPOINT);
		urlsMap.put("listApiPlans", LIST_API_PLANS);
		urlsMap.put("addApiPolicy", ADD_API_POLICY);
		urlsMap.put("getApiPolicy", GET_API_POLICY);
		urlsMap.put("updateApiPolicy", UPDATE_API_POLICY);
		urlsMap.put("removeApiPolicy", REMOVE_API_POLICY);
		urlsMap.put("listClients", LIST_CLIENTS);
		urlsMap.put("createClient", CREATE_CLIENT);
		urlsMap.put("getClientById", GET_CLIENT_BY_ID);
		urlsMap.put("updateClient", UPDATE_CLIENT);
		urlsMap.put("deleteClient", DELETE_CLIENT);
		urlsMap.put("getClientVersion", GET_CLIENT_VERSION);
		urlsMap.put("getApiKey", GET_API_KEY);
		urlsMap.put("updateApiKey", UPDATE_API_KEY);
		urlsMap.put("getApiContract", GET_API_CONTRACT);
		urlsMap.put("breakContract", BREAK_CONTRACT);
		urlsMap.put("addClientPolicy", ADD_CLIENT_POLICY);
		urlsMap.put("getClientPolicy", GET_CLIENT_POLICY);
		urlsMap.put("listPlans", LIST_PLANS);
		urlsMap.put("createPlan", CREATE_PLAN);
		urlsMap.put("getPlanById", GET_PLAN_BY_ID);
		urlsMap.put("updatePlan", UPDATE_PLAN);
		urlsMap.put("deletePlan", DELETE_PLAN);
		urlsMap.put("getPlanActivity", GET_PLAN_ACTIVITY);
		urlsMap.put("listPlanVersions", LIST_PLAN_VERSIONS);
		urlsMap.put("getPlanVersion", GET_PLAN_VERSION);
		urlsMap.put("addPlanPolicy", ADD_PLAN_POLICY);
		urlsMap.put("getPlanPolicy", GET_PLAN_POLICY);
		urlsMap.put("updatePlanPolicy", UPDATE_PLAN_POLICY);
		urlsMap.put("removePlanPolicy", REMOVE_PLAN_POLICY);
		urlsMap.put("grantMembership", GRANT_MEMBERSHIP);
		urlsMap.put("listAllPlugins", LIST_ALL_PLUGINS);
		urlsMap.put("addPlugin", ADD_PLUGIN);
		urlsMap.put("getPluginById", GET_PLUGIN_BY_ID);
		urlsMap.put("deletePluginById", DELETE_PLUGIN_BY_ID);
		urlsMap.put("listAllRoles", LIST_ALL_ROLES);
		urlsMap.put("createRole", CREATE_ROLE);
		urlsMap.put("searchForRoles", SEARCH_FOR_ROLES);
		urlsMap.put("getRoleById", GET_ROLE_BY_ID);
		urlsMap.put("updateRoleById", UPDATE_ROLE_BY_ID);
		urlsMap.put("deleteRoleById", DELETE_ROLE_BY_ID);
		urlsMap.put("searchForApis", SEARCH_FOR_APIS);
		urlsMap.put("searchForClients", SEARCH_FOR_CLIENTS);
		urlsMap.put("exportData", EXPORT_DATA);
		urlsMap.put("importData", IMPORT_DATA);
		urlsMap.put("getSystemStatus", GET_SYSTEM_STATUS);
		urlsMap.put("searchForUsers", SEARCH_FOR_USERS);
		urlsMap.put("removeClientPolicy", REMOVE_CLIENT_POLICY);
		urlsMap.put("listOrganizationMembers", LIST_ORGANIZATION_MEMBERS);
		urlsMap.put("revokeAllMemberships", REVOKE_ALL_MEMBERSHIPS);
		urlsMap.put("createPlanVersion", CREATE_PLAN_VERSION);
		urlsMap.put("getPlanVersionActivity", GET_PLAN_VERSION_ACTIVITY);
		urlsMap.put("reorderPlanPolicies", REORDER_PLAN_POLICIES);
		urlsMap.put("listAllPlanPolicies", LIST_ALL_PLAN_POLICIES);
		urlsMap.put("revokeSingleMembership", REVOKE_SINGLE_MEMBERSHIP);
		urlsMap.put("getCurrentUserPermissions", GET_CURRENT_USER_PERMISSIONS);
		urlsMap.put("getUserPermissions", GET_USER_PERMISSIONS);
		urlsMap.put("listAvailablePlugins", LIST_AVAILABLE_PLUGINS);
		urlsMap.put("getPluginPolicyDefinitions", GET_PLUGIN_POLICY_DEFINITIONS);
		urlsMap.put("getPluginPolicyForm", GET_PLUGIN_POLICY_FORM);
		urlsMap.put("listPolicyDefinitions", LIST_POLICY_DEFINITIONS);
		urlsMap.put("addPolicyDefinition", ADD_POLICY_DEFINITION);
		urlsMap.put("getPolicyDefinitionById", GET_POLICY_DEFINITION_BY_ID);
		urlsMap.put("updatePolicyDefinition", UPDATE_POLICY_DEFINITION);
		urlsMap.put("deletePolicyDefinition", DELETE_POLICY_DEFINITION);
		urlsMap.put("searchForOrganizations", SEARCH_FOR_ORGANIZATIONS);
		urlsMap.put("searchForApisInApiCatalogue", SEARCH_FOR_APIS_IN_API_CATALOGUE);
		urlsMap.put("listAllNamespacesInApiCatalogue", LIST_ALL_NAMESPACES_IN_API_CATALOGUE);
		urlsMap.put("listUserOrganizations", LIST_USER_ORGANIZATIONS);
		urlsMap.put("getUserById", GET_USER_BY_ID);
		urlsMap.put("updateUserById", UPDATE_USER_BY_ID);
		urlsMap.put("getUserActivity", GET_USER_ACTIVITY);
		urlsMap.put("listUserApis", LIST_USER_APIS);
		urlsMap.put("listUserClients", LIST_USER_CLIENTS);
	}

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
	public void testGateway() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.testGateway(new Gateway());

		mockServer.verify();
	}

	@Test
	public void createGateway() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createGateway(new Gateway());

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
	public void updateGateway() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateGateway(gatewayId, new Gateway());

		mockServer.verify();
	}

	@Test
	public void deleteGateway() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deleteGateway(gatewayId);

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
	public void getUserPermissions() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getUserPermissions(userId);

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
	public void addPlugin() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.addPlugin(new Plugin());

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
	public void getPluginById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getPluginById(pluginId);

		mockServer.verify();
	}

	@Test
	public void deletePluginById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deletePluginById(pluginId);

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
	public void addPolicyDefinition() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.addPolicyDefinition(new PolicyDefinition());

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
	public void updatePolicyDefinition() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updatePolicyDefinition(policyDefId, new PolicyDefinition());

		mockServer.verify();
	}

	@Test
	public void deletePolicyDefinition() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deletePolicyDefinition(policyDefId);

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
	public void createRole() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.createRole(new Role());

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
	public void updateRoleById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.PUT))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.updateRoleById(roleId, new Role());

		mockServer.verify();
	}

	@Test
	public void deleteRoleById() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.DELETE))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.deleteRoleById(roleId);

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
	public void exportData() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.exportData(download);

		mockServer.verify();
	}

	@Test
	public void importData() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.POST))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.importData();

		mockServer.verify();
	}

	@Test
	public void getSystemStatus() {

		String url = apimanUrl + urlsMap.get(Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("\nService : " + Thread.currentThread().getStackTrace()[1].getMethodName());
		log.debug("Test Case : " + url);

		mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		service.getSystemStatus();

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
