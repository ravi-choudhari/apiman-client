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
public class Plugin {

	private String name;
	private int id;
	private String type;
	private String artifactId;
	private String description;
	private String version;
	private String classifier;
	private String groupId;
	private String createdOn;
	private String createdBy;
	private boolean upgrade;
	private boolean deleted;
}
