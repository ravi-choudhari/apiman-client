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
public class CreateApi {

	private String name;
	private String description;
	private String definitionUrl;
	private String endpointContentType;
	private String definitionType;
	private String initialVersion;
	private String endpoint;
	private boolean publicAPI;
	private List<Plan> plans;
	private String endpointType;
	private boolean parsePayload;
}
