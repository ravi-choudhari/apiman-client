package org.apiman.client.domain;

import java.io.Serializable;

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
public class OrganizationPlan implements Serializable {

	private String name;
	private int id;
	private String description;
	private String createdOn;
	private String createdBy;
	private Organization organization;
	private String organizationName;
	private String organizationId;
}
