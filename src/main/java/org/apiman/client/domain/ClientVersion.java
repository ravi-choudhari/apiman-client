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
public class ClientVersion implements Serializable {
	
	private String name;
	private String id;
	private String description;
	private String version;
	private String status;
	private String apiKey;
	private String modifiedOn;
	private String createdOn;
	private String modifiedBy;
	private String createdBy;
	private String retiredOn;
	private String publishedOn;
	private String organizationName;
	private String organizationId;
	private Client client;
}
