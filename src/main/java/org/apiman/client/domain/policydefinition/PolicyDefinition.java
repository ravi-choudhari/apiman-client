package org.apiman.client.domain.policydefinition;

import java.util.Set;

import org.apiman.client.domain.summary.PolicyFormType;

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
public class PolicyDefinition {
	
	private String id;
	private String policyImpl;
	private String name;
	private String description;
	private String icon;
	private Set<PolicyDefinitionTemplate> templates;
	private Long pluginId;
	private PolicyFormType formType;
	private String form;
	private Boolean deleted;
}
