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
public class PolicySummary implements Serializable {

    private String policyDefinitionId;
    private Long id;
    private String name;
    private String description;
    private String icon;
    private String createdBy;
    private Date createdOn;
}
