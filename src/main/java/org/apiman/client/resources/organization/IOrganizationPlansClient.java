package org.apiman.client.resources.organization;

import java.util.List;

import org.apiman.client.domain.audit.AuditEntry;
import org.apiman.client.domain.plan.NewPlan;
import org.apiman.client.domain.plan.NewPlanVersion;
import org.apiman.client.domain.plan.Plan;
import org.apiman.client.domain.plan.PlanVersion;
import org.apiman.client.domain.plan.UpdatePlan;
import org.apiman.client.domain.policies.PolicyChain;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.domain.summary.PlanSummary;
import org.apiman.client.domain.summary.PlanVersionSummary;
import org.apiman.client.resources.organization.planpolicy.IOrganizationPlansPoliciesClient;

public interface IOrganizationPlansClient {

	List<PlanSummary> listPlans(String organizationId);

	Plan createPlan(String organizationId, NewPlan organizationPlan);

	Plan getPlanById(String organizationId, String planId);

	void updatePlan(String organizationId, String planId, UpdatePlan organizationPlan);

	void deletePlan(String organizationId, String planId);

	SearchResults<AuditEntry> getPlanActivity(String organizationId, String planId, int page, int count);

	List<PlanVersionSummary> listPlanVersions(String organizationId, String planId);

	PlanVersion createPlanVersion(String organizationId, String planId, NewPlanVersion planVersion);

	PlanVersion getPlanVersion(String organizationId, String planId, String version);

	SearchResults<AuditEntry> getPlanVersionActivity(String organizationId, String planId, String version, int page,
			int count);

	void reorderPlanPolicies(String organizationId, String planId, String version, PolicyChain reOrderPolicies);
	
	IOrganizationPlansPoliciesClient getPlansPoliciesClient();

}
