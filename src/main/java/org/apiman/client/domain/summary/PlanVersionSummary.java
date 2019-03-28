package org.apiman.client.domain.summary;

import org.apiman.client.domain.plan.PlanStatus;

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
public class PlanVersionSummary {

    private String organizationId;
    private String organizationName;
    private String id;
    private String name;
    private String description;
    private PlanStatus status;
    private String version;
}
