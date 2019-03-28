package org.apiman.client.domain.summary;

import org.apiman.client.domain.gateway.GatewayType;

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
public class GatewaySummary {

    private String id;
    private String name;
    private String description;
    private GatewayType type;
}
