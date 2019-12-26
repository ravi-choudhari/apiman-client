package org.apiman.client.resources.organization.apis;

import java.util.List;

import org.apiman.client.domain.policies.PolicyChain;
import org.apiman.client.domain.summary.ApiPlanSummary;

public interface IOrganizationApiPlansClient {

	List<ApiPlanSummary> listApiPlans(String organizationId, String apiId, String version);

	PolicyChain getApiPolicyChain(String organizationId, String apiId, String version, String planId);

}
