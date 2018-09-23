package org.apiman.client.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ApiContract {

	private String createdOn;
	private String clientVersion;
	private String apiVersion;
	private String planVersion;
	private String clientOrganizationId;
	private String clientOrganizationName;
	private String apiOrganizationId;
	private String apiOrganizationName;
	private String apiDescription;
	private String clientId;
	private String planName;
	private int contractId;
	private String apiName;
	private String clientName;
	private String planId;
	private String apiId;
}
