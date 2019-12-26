package org.apiman.client.resources;

import java.util.List;

import org.apiman.client.domain.policydefinition.PolicyDefinition;
import org.apiman.client.domain.policydefinition.UpdatePolicyDefinition;
import org.apiman.client.domain.summary.PolicyDefinitionSummary;

public interface IPolicyDefsClient {

	PolicyDefinition addPolicyDefinition(PolicyDefinition policyDefinition);

	void updatePolicyDefinition(String policyDefinitionId, UpdatePolicyDefinition policyDefinition);

	void deletePolicyDefinition(String policyDefinitionId);

	List<PolicyDefinitionSummary> listPolicyDefinitions();

	PolicyDefinition getPolicyDefinitionById(String policyDefinitionId);

}
