package org.apiman.client.domain.contracts;

import java.util.Date;

import org.apiman.client.domain.apis.ApiVersion;
import org.apiman.client.domain.clients.ClientVersion;
import org.apiman.client.domain.plan.PlanVersion;

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
public class Contract {

    private Long id;
    private ClientVersion client;
    private ApiVersion api;
    private PlanVersion plan;
    private String createdBy;
    private Date createdOn;
}
