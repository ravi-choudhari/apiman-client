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
public class Gateway implements Serializable {

	private String name;
	private String id;
	private String gatewayId;
	private String type;
	private String description;
	private String configuration;
	private String modifiedOn;
	private String createdOn;
	private String modifiedBy;
	private String createdBy;
}
