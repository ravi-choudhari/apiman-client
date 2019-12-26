package org.apiman.client.domain.apis;

import java.io.Serializable;
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
public class NewApiVersion implements Serializable {

    private String version;
    private boolean clone;
    private String cloneVersion;
    private String endpoint;
    private EndpointType endpointType;
    private EndpointContentType endpointContentType;
    private Boolean publicAPI;
    private Boolean parsePayload;
    private Set<ApiPlan> plans;
    private String definitionUrl;
    private ApiDefinitionType definitionType;
}
