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
public class Api {

	private String name;
	private String id;
	private String description;
	private String createdOn;
	private String organizationName;
	private String organizationId;
	private Organization organization;
	private String createdBy;
	private int numPublished;
	
}
