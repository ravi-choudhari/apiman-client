package org.apiman.client.resources.organization.clients;

import java.util.List;

import org.apiman.client.domain.policies.NewPolicy;
import org.apiman.client.domain.policies.Policy;
import org.apiman.client.domain.policies.UpdatePolicy;
import org.apiman.client.domain.summary.PolicySummary;

public interface IClientsPoliciesClient {

	List<PolicySummary> listAllClientPolicies(String organizationId, String clientId, String version);

	Policy addClientPolicy(String organizationId, String clientId, String version, NewPolicy clientPolicy);

	Policy getClientPolicy(String organizationId, String clientId, String version, String policyId);

	void updateClientPolicy(String organizationId, String clientId, String version, String policyId,
			UpdatePolicy clientPolicy);

	void removeClientPolicy(String organizationId, String clientId, String version, String policyId);

}
