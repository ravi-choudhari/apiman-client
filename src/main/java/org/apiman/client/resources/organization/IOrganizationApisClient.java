package org.apiman.client.resources.organization;

import java.util.List;

import org.apiman.client.domain.apis.Api;
import org.apiman.client.domain.apis.ApiVersion;
import org.apiman.client.domain.apis.ApiVersionStatus;
import org.apiman.client.domain.apis.NewApi;
import org.apiman.client.domain.apis.NewApiDefinition;
import org.apiman.client.domain.apis.NewApiVersion;
import org.apiman.client.domain.apis.UpdateApi;
import org.apiman.client.domain.apis.UpdateApiVersion;
import org.apiman.client.domain.audit.AuditEntry;
import org.apiman.client.domain.policies.PolicyChain;
import org.apiman.client.domain.search.SearchResults;
import org.apiman.client.domain.summary.ApiSummary;
import org.apiman.client.domain.summary.ApiVersionEndpointSummary;
import org.apiman.client.domain.summary.ApiVersionSummary;
import org.apiman.client.domain.summary.ContractSummary;
import org.apiman.client.resources.organization.apis.IOrganizationApiMetricsClient;
import org.apiman.client.resources.organization.apis.IOrganizationApiPlansClient;
import org.apiman.client.resources.organization.apis.IOrganizationApiPoliciesClient;

public interface IOrganizationApisClient {

	List<ApiSummary> listApis(String organizationId);

	Api createApi(String organizationId, NewApi createApi);

	Api getApiById(String organizationId, String apiId);

	void updateApi(String organizationId, String apiId, UpdateApi api);

	void deleteApi(String organizationId, String apiId);

	SearchResults<AuditEntry> getApiActivity(String organizationId, String apiId, int page, int count);

	List<ApiVersionSummary> listApiVersions(String organizationId, String apiId);

	ApiVersion createApiVersion(String organizationId, String apiId, NewApiVersion createApiVersion);

	ApiVersion getApiVersion(String organizationId, String apiId, String version);

	ApiVersion updateApiVersion(String organizationId, String apiId, String version, UpdateApiVersion createApiVersion);

	SearchResults<AuditEntry> getApiVersionActivity(String organizationId, String apiId, String version, int page,
			int count);

	List<ContractSummary> listApiContracts(String organizationId, String apiId, String version, int page, int count);

	String getApiDefinition(String organizationId, String apiId, String version);

	void updateApiDefinition(String organizationId, String apiId, String version, String apiDefinition);

	void updateApiDefinitionFromUrl(String organizationId, String apiId, String version,
			NewApiDefinition apiDefinition);

	void removeApiDefinition(String organizationId, String apiId, String version);

	ApiVersionEndpointSummary getApiEndpoint(String organizationId, String apiId, String version);

	void reOrderApiPolicies(String organizationId, String apiId, String version, PolicyChain reOrderPolicies);

	ApiVersionStatus getApiVersionStatus(String organizationId, String apiId, String version);
	
	IOrganizationApiMetricsClient getOrganizationApiMetricsClient();
	
	IOrganizationApiPlansClient getOrganizationApiPlansClient();
	
	IOrganizationApiPoliciesClient getOrganizationApiPoliciesClient();

}
