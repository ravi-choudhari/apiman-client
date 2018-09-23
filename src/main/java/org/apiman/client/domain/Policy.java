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
public class Policy {

	private String name;
	private String description;
	private int id;
	private String createdBy;
	private String createdOn;
	private String policyDefinitionId;
	private String icon;
}
