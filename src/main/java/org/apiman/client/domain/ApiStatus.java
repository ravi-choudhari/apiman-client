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
public class ApiStatus {

	private String name;
	private String id;
	private boolean done;
	private boolean optional;
	private String remediation;
}
