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
public class Policy implements Serializable {

	private String name;
	private String description;
	private int id;
	private String createdBy;
	private String createdOn;
	private String policyDefinitionId;
	private String icon;
}
