package org.apiman.client.domain.apis;

import java.util.Map;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateApiVersion {

    private String endpoint;
    private EndpointType endpointType;
    private EndpointContentType endpointContentType;
    private Map<String, String> endpointProperties;
    private Set<ApiGateway> gateways;
    private Boolean parsePayload;
    private Boolean publicAPI;
    private Set<ApiPlan> plans;
}
