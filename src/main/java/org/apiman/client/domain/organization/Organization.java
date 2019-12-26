package org.apiman.client.domain.organization;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apiman.client.domain.apis.Api;
import org.apiman.client.domain.clients.Client;
import org.apiman.client.domain.plan.Plan;

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
public class Organization implements Serializable {

    private String id;
    private String name;
    private String description;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
    private Set<Plan> planSet;
    private Set<Api> apiSet;
    private Set<Client> clientSet;
}
