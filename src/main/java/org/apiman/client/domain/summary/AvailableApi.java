package org.apiman.client.domain.summary;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apiman.client.domain.apis.ApiDefinitionType;
import org.apiman.client.domain.apis.EndpointType;

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
public class AvailableApi implements Serializable {

    private String id;
    private String icon;
    private String endpoint;
    private String routeEndpoint;
    private EndpointType endpointType = EndpointType.rest;
    private String name;
    private String description;
    private String definitionUrl;
    private String routeDefinitionUrl;
    private ApiDefinitionType definitionType;
    private String namespace;
    private Set<String> tags = new HashSet<String>();
    private boolean internal;
}
