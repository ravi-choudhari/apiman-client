package org.apiman.client.domain;

import java.io.Serializable;
import java.util.List;

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
public class Role implements Serializable {

	private String name;
	private String description;
	private List<String> permissions;
	private boolean autoGrant;
	private String id;
	private String createdBy;
	private String createdOn;
}
