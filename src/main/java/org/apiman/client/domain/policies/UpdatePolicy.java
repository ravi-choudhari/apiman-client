package org.apiman.client.domain.policies;

import java.io.Serializable;
import java.util.Date;

import org.apiman.client.domain.policydefinition.PolicyDefinition;

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
public class UpdatePolicy implements Serializable {

	private String configuration;
}
