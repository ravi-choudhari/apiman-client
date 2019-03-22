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
public class PlanVersion implements Serializable {

	private String name;
	private String id;
	private String description;
	private String version;
	private String status;
	private String organizationName;
	private String organizationId;
}
