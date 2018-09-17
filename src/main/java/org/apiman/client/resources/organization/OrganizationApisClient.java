package org.apiman.client.resources.organization;

import org.apiman.client.ApiClient;
import org.apiman.client.resources.organization.apis.OrganizationApiMetricsClient;
import org.apiman.client.resources.organization.apis.OrganizationApiPlansClient;
import org.apiman.client.resources.organization.apis.OrganizationApiPoliciesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationApisClient extends ApiClient {

	private static final String ORGANIZATION_API_PATH = ORGANIZATIONS_PATH + "/{organizationId}/apis";
	
	@Autowired
	private OrganizationApiMetricsClient organizationApiMetricsClient;
	@Autowired
	private OrganizationApiPlansClient organizationApiPlansClient;
	@Autowired
	private OrganizationApiPoliciesClient organizationApiPoliciesClient;
	
	public OrganizationApiMetricsClient getOrganizationApiMetricsClient() {
		return organizationApiMetricsClient;
	}
	public OrganizationApiPlansClient getOrganizationApiPlansClient() {
		return organizationApiPlansClient;
	}
	public OrganizationApiPoliciesClient getOrganizationApiPoliciesClient() {
		return organizationApiPoliciesClient;
	}

	/* Use this endpoint to get a list of all APIs in the Organization.
	 * 
	 */
	public String listApis() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to create a new API. Note that it is important to also create an initial version of the API (e.g. 1.0). 
	 * This can either be done by including the 'initialVersion' property in the request, or by immediately following up with a 
	 * call to "Create API Version". If the former is done, then a first API version will be created automatically by this endpoint.
	 */
	public String createApi() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to retrieve information about a single API by ID. Note that this only returns information about the API, 
	 * not about any particular *version* of the API.
	 */
	public String getApiById() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to update information about an API.
	 * 
	 */
	public String updateApi() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to delete an API. There are multiple restrictions on this capability. Specifically, the API must not 
	 * have any published versions. If you try to delete an API with one or more published versions, it will fail with 
	 * an EntityStillActiveException error.
	 */
	public String deleteApi() {
		
		return apimanUrl;
	}
	
	/* This endpoint returns audit activity information about the API.
	 * 
	 */
	public String getApiActivity() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to list all of the versions of an API.
	 * 
	 */
	public String listApiVersions() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to create a new version of the API.
	 * 
	 */
	public String createApiVersion() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get detailed information about a single version of an API.
	 * 
	 */
	public String getApiVersion() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to update information about a single version of an API.
	 * 
	 */
	public String updateApiVersion() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get audit activity information for a single version of the API.
	 * 
	 */
	public String getApiVersionActivity() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get a list of all Contracts created with this API. This will return Contracts created by 
	 * between any Client and through any Plan.
	 */
	public String listApiContracts() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to retrieve the API's definition document. An API definition document can be several different 
	 * types, depending on the API type and technology used to define the API. For example, this endpoint might return a 
	 * WSDL document, or a Swagger JSON document.
	 */
	public String getApiDefinition() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to update the API's definition document. An API definition will vary depending on the type of API, 
	 * and the type of definition used. For example, it might be a Swagger document or a WSDL file. To use this endpoint, 
	 * simply PUT the updated API Definition document in its entirety, making sure to set the Content-Type appropriately 
	 * for the type of definition document. The content will be stored and the API's "Definition Type" field will be updated. 
	 * Whenever an API's definition is updated, the "definitionType" property of that API is automatically set based on the 
	 * request Content-Type. There is no other way to set the API's definition type property. The following is a map 
	 * of Content-Type to API definition type.
	 * Content Type	API Definition Type
	 * application/json	SwaggerJSON
	 * application/x-yaml	SwaggerYAML
	 * application/wsdl+xml	WSDL
	 */
	public String updateApiDefinition() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to update the API's definition document by providing a URL (reference) to the definition. 
	 * This is an alternative to providing the full API definition document via a PUT to the same endpoint. This endpoint 
	 * can be used to either add a definition if one does not already exist, as well as update/replace an existing definition. 
	 * Note that apiman will not store the definition reference, but instead will download the API definition document and store it. 
	 * Additionally, the the API's "Definition Type" field will be updated.
	 */
	public String updateApiDefinitionFromUrl() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to delete an API's definition document. When this is done, the "definitionType" field on the API will be set to None.
	 * 
	 */
	public String removeApiDefinition() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to get information about the Managed API's gateway endpoint. In other words, this returns the actual 
	 * live endpoint on the API Gateway - the endpoint that a client should use when invoking the API.
	 */
	public String getApiEndpoint() {
		
		return apimanUrl;
	}
	
	/* Use this endpoint to change the order of Policies for an API. When a Policy is added to the API, it is added as the last 
	 * Policy in the list of API Policies. Sometimes the order of Policies is important, so it is often useful to re-order the 
	 * Policies by invoking this endpoint. The body of the request should include all of the Policies for the API, in the new 
	 * desired order. Note that only the IDs of each of the Policies is actually required in the request, at a minimum.
	 */
	public String reorderApiPolicies() {
		
		return apimanUrl;
	}

	/* Use this endpoint to get detailed status information for a single version of an API. This is useful to figure out why 
	 * an API is not yet in the 'Ready' state (which is required before it can be published to a Gateway).
	 */
	public String getApiVersionStatus() {
		
		return apimanUrl;
	}		
}
