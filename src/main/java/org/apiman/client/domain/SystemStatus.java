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
public class SystemStatus {

	private String name;
	private String id;
	private String description;
	private String version;
	private boolean up;
	private String buildOn;
	private String moreInfo;
}
