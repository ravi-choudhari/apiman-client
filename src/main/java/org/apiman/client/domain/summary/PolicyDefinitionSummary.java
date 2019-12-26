package org.apiman.client.domain.summary;

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
public class PolicyDefinitionSummary implements Serializable {

    private String id;
    private String policyImpl;
    private String name;
    private String description;
    private String icon;
    private PolicyFormType formType;
    private Long pluginId;
}
