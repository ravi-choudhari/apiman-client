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
public class PolicyDefinition implements Serializable {

	private String name;
	private String id;
	private String description;
	private boolean deleted;
	private String form;
	private String policyImpl;
	private List<Template> templates;
	private String formType;
	private int pluginId;
	private String icon;
}
