package org.apiman.client.domain.apis;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apiman.client.domain.organization.Organization;

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
public class NewApi implements Serializable {

    private String name;
    private String description;
    private String initialVersion;
    private String endpoint;
    private EndpointType endpointType;
    private EndpointContentType endpointContentType;
    private Boolean publicAPI;
    private Boolean parsePayload;
    private Set<ApiPlan> plans;
    private String definitionUrl;
    private ApiDefinitionType definitionType;
}
