package org.apiman.client.domain.policies;

import java.util.List;

import org.apiman.client.domain.summary.PolicySummary;

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
public class PolicyChain {

	private List<PolicySummary> policies;
}
