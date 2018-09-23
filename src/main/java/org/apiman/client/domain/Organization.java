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
public class Organization {
	
	private String name;
	private String id;
	private String description;
	private String modifiedOn;
	private String createdOn;
	private String modifiedBy;
	private String createdBy;
	private int numClients;
	private int numMembers;
	private int numApis;
}
