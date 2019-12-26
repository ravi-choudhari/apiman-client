package org.apiman.client.domain.policydefinition;

import java.io.Serializable;
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
public class UpdatePolicyDefinition implements Serializable {

    private String name;
    private String description;
    private String icon;
}
