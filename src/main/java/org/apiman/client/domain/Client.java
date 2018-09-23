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
public class Client {

	private String name;
	private String id;
	private String description;
	private Organization organization;
	private String organizationName;
	private String organizationId;
	private int numContracts;
	private String createdOn;
	private String createdBy;
	private String initialVersion;
}
