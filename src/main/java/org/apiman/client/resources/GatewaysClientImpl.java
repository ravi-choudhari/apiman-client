package org.apiman.client.resources;

import static org.apiman.client.util.GenericUtils.buildURL;
import static org.apiman.client.util.GenericUtils.substitute;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apiman.client.AbstractApimanClient;
import org.apiman.client.domain.gateway.Gateway;
import org.apiman.client.domain.gateway.NewGateway;
import org.apiman.client.domain.gateway.UpdateGateway;
import org.apiman.client.domain.summary.GatewaySummary;
import org.apiman.client.domain.summary.GatewayTestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GatewaysClientImpl extends AbstractApimanClient implements IGatewaysClient {

	@Autowired
	@Qualifier("redhatApimanAdminRestClient")
	protected RestTemplate adminTemplate;

	private static final String GATEWAYS_PATH = "/gateways";

	/*
	 * This endpoint returns a list of all the Gateways that have been configured.
	 * 
	 */
	@Override
	public List<GatewaySummary> listAllGateways() {

		String url = buildURL(apimanUrl, GATEWAYS_PATH);
		GatewaySummary[] gateways = restTemplate.getForObject(url, GatewaySummary[].class);

		return gateways != null ? Arrays.asList(gateways) : null;
	}

	/*
	 * This endpoint is used to test the Gateway's settings prior to either creating
	 * or updating it. The information will be used to attempt to create a link
	 * between the API Manager and the Gateway, by simply trying to ping the
	 * Gateway's "status" endpoint.
	 */
	@Override
	public GatewayTestResult testGateway(NewGateway gateway) {

		String url = buildURL(apimanUrl, GATEWAYS_PATH);
		ResponseEntity<GatewayTestResult> response = adminTemplate.exchange(url, PUT,
				new HttpEntity<NewGateway>(gateway, getHeaders()), GatewayTestResult.class);

		return response != null ? response.getBody() : null;
	}

	/*
	 * This endpoint is called to create a new Gateway.
	 * 
	 */
	@Override
	public Gateway createGateway(NewGateway gateway) {

		String url = buildURL(apimanUrl, GATEWAYS_PATH);
		return adminTemplate.postForObject(url, gateway, Gateway.class);
	}

	/*
	 * Call this endpoint to get the details of a single configured Gateway.
	 * 
	 */
	@Override
	public Gateway getGatewayById(String gatewayId) {

		String url = buildURL(apimanUrl, GATEWAYS_PATH, "/${gatewayId}");
		Map<String, String> map = new HashMap<>();
		map.put("gatewayId", gatewayId);
		url = substitute(url, map, false);

		return restTemplate.getForObject(url, Gateway.class);
	}

	/*
	 * Use this endpoint to update an existing Gateway. Note that the name of the
	 * Gateway cannot be changed, as the name is tied closely with the Gateway's ID.
	 * If you wish to rename the Gateway you must delete it and create a new one.
	 */
	@Override
	public void updateGateway(String gatewayId, UpdateGateway gateway) {

		String url = buildURL(apimanUrl, GATEWAYS_PATH, "/${gatewayId}");
		Map<String, String> map = new HashMap<>();
		map.put("gatewayId", gatewayId);
		url = substitute(url, map, false);

		adminTemplate.exchange(url, PUT, new HttpEntity<UpdateGateway>(gateway, getHeaders()), Void.class);
	}

	/*
	 * This endpoint deletes a Gateway by its unique ID.
	 * 
	 */
	@Override
	public void deleteGateway(String gatewayId) {

		String url = buildURL(apimanUrl, GATEWAYS_PATH, "/${gatewayId}");
		Map<String, String> map = new HashMap<>();
		map.put("gatewayId", gatewayId);
		url = substitute(url, map, false);

		adminTemplate.delete(url);
	}
}
