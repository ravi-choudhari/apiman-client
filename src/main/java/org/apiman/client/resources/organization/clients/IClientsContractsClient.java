package org.apiman.client.resources.organization.clients;

import java.util.List;

import org.apiman.client.domain.contracts.Contract;
import org.apiman.client.domain.contracts.NewContract;
import org.apiman.client.domain.summary.ContractSummary;

public interface IClientsContractsClient {

	List<ContractSummary> listAllContractsForClient(String organizationId, String clientId, String version);

	Contract createApiContract(String organizationId, String clientId, String version, NewContract apiContract);

	void breakAllContracts(String organizationId, String clientId, String version);

	Contract getApiContract(String organizationId, String clientId, String version, Long contractId);

	void breakContract(String organizationId, String clientId, String version, Long contractId);

}
