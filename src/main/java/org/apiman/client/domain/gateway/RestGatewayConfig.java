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
public class RestGatewayConfig implements Serializable {

    private String endpoint;
    private String username;
    private String password;
}
