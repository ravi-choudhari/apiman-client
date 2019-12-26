package org.apiman.client.domain.summary;

import java.io.Serializable;
import java.util.Date;

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
public class ContractSummary implements Serializable {

    private Long contractId;
    private String clientOrganizationId;
    private String clientOrganizationName;
    private String clientId;
    private String clientName;
    private String clientVersion;
    private String apiOrganizationId;
    private String apiOrganizationName;
    private String apiId;
    private String apiName;
    private String apiVersion;
    private String apiDescription;
    private String planName;
    private String planId;
    private String planVersion;
    private Date createdOn;
}
