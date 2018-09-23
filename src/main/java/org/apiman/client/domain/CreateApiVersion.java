package org.apiman.client.domain;

import java.util.List;

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
public class CreateApiVersion {

	private int id;
	private String version;
	private String status;
	private String modifiedOn;
	private String createdOn;
	private String modifiedBy;
	private String createdBy;
	private String retiredOn;
	private String publishedOn;
	private Api api;
	private String definitionType;
	private String endpointContentType;
//	endpointProperties:{
//	string =>string
//	}
	private String endpointType;
	private List<Plan> plans;
	private String endpoint;
	private boolean publicAPI;
	private boolean parsePayload;
	private List<Gateway> gateways;
}
