package org.apiman.client.resources;

import java.util.List;

import org.apiman.client.domain.gateway.Gateway;
import org.apiman.client.domain.gateway.NewGateway;
import org.apiman.client.domain.gateway.UpdateGateway;
import org.apiman.client.domain.summary.GatewaySummary;
import org.apiman.client.domain.summary.GatewayTestResult;

public interface IGatewaysClient {

	GatewayTestResult testGateway(NewGateway gateway);

	Gateway createGateway(NewGateway gateway);

	void updateGateway(String gatewayId, UpdateGateway gateway);

	void deleteGateway(String gatewayId);

	List<GatewaySummary> listAllGateways();

	Gateway getGatewayById(String gatewayId);

}
