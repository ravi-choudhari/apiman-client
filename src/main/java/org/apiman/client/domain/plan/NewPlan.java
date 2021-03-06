package org.apiman.client.domain.plan;

import java.io.Serializable;

import org.apiman.client.domain.summary.PlanSummary;

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
public class NewPlan implements Serializable {

    private String name;
    private String description;
    private String initialVersion;
}
