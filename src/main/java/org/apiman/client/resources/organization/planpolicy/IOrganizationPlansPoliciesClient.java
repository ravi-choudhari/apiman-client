package org.apiman.client.resources.organization.planpolicy;

import java.util.List;

import org.apiman.client.domain.policies.NewPolicy;
import org.apiman.client.domain.policies.Policy;
import org.apiman.client.domain.policies.UpdatePolicy;
import org.apiman.client.domain.summary.PolicySummary;

public interface IOrganizationPlansPoliciesClient {

	List<PolicySummary> listAllPlanPolicies(String organizationId, String planId, String version);

	Policy addPlanPolicy(String organizationId, String planId, String version, NewPolicy planPolicy);

	Policy getPlanPolicy(String organizationId, String planId, String version, Long policyId);

	void updatePlanPolicy(String organizationId, String planId, String version, Long policyId,
			UpdatePolicy planPolicy);

	void removePlanPolicy(String organizationId, String planId, String version, Long policyId);

}
