package org.apiman.client.domain.apis;

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
public class NewApiDefinition {

    private String definitionUrl;
    private ApiDefinitionType definitionType;
}
