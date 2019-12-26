package org.apiman.client.resources.organization.clients;

public interface IClientsApiRegistryClient {

	String getJsonApiRegistry(String organizationId, String clientId, String version, String download);

	String getXmlApiRegistry(String organizationId, String clientId, String version, String download);

}
