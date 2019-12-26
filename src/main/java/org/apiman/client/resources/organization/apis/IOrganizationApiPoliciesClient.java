package org.apiman.client.resources.organization.apis;

import java.util.List;

import org.apiman.client.domain.policies.NewPolicy;
import org.apiman.client.domain.policies.Policy;
import org.apiman.client.domain.policies.UpdatePolicy;
import org.apiman.client.domain.summary.PolicySummary;

public interface IOrganizationApiPoliciesClient {

	List<PolicySummary> listAllApiPolicies(String organizationId, String apiId, String version);

	Policy addApiPolicy(String organizationId, String apiId, String version, NewPolicy apiPolicy);

	Policy getApiPolicy(String organizationId, String apiId, String version, String policyId);

	void updateApiPolicy(String organizationId, String apiId, String version, String policyId, UpdatePolicy apiPolicy);

	void removeApiPolicy(String organizationId, String apiId, String version, String policyId);

}
