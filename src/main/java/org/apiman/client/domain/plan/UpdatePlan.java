package org.apiman.client.domain.plan;

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
public class UpdatePlan {

	private String description;
}
