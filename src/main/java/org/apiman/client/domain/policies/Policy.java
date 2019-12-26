package org.apiman.client.domain.policies;

import java.io.Serializable;
import java.util.Date;

import org.apiman.client.domain.policydefinition.PolicyDefinition;

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
public class Policy implements Serializable {

    private Long id;
    private PolicyType type;
    private String organizationId;
    private String entityId;
    private String entityVersion;
    private String name;
    private String description;
    private String configuration;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
    private PolicyDefinition definition;
    private int orderIndex;
}
