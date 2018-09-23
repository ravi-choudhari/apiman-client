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
public class ClientPolicy {

	private String name;
	private int id;
	private String type;
	private PolicyDefinition definition;
	private String configuration;
	private String description;
	private String modifiedOn;
	private String createdOn;
	private String modifiedBy;
	private String createdBy;
	private String entityVersion;
	private String entityId;
	private String organizationId;
	private int orderIndex;
}
