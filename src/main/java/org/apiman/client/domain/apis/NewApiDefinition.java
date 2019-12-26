package org.apiman.client.domain.apis;

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
public class NewApiDefinition implements Serializable {

    private String definitionUrl;
    private ApiDefinitionType definitionType;
}
