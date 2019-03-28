package org.apiman.client.domain.summary;

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
public class PlanSummary {

    private String organizationId;
    private String organizationName;
    private String id;
    private String name;
    private String description;
}
