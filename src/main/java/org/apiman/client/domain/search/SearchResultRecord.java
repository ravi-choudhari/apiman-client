package org.apiman.client.domain.search;

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
public class SearchResultRecord implements Serializable {

	private int id;
	private String data;
	private String createdOn;
	private String entityVersion;
	private String entityId;
	private String organizationId;
	private String entityType;
	private String what;
	private String who;
}
