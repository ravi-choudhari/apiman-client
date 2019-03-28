package org.apiman.client.domain.apis;

import java.util.Date;
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
public class ApiVersion {

    private Long id;
    private Api api;
    private ApiStatus status;
    private String endpoint;
    private EndpointType endpointType;
    private EndpointContentType endpointContentType;
    private Map<String, String> endpointProperties;
    private Set<ApiGateway> gateways;
    private boolean publicAPI;
    private Set<ApiPlan> plans;
    private String version;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
    private Date publishedOn;
    private Date retiredOn;
    private ApiDefinitionType definitionType;
    private ApiDefinition apiDefinition;
    private boolean parsePayload;
}
