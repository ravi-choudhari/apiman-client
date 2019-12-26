package org.apiman.client.domain.gateway;

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
public class NewGateway implements Serializable {

    private String name;
    private String description;
    private GatewayType type;
    private String configuration;
}
