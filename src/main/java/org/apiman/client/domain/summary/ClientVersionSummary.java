package org.apiman.client.domain.summary;

import java.io.Serializable;

import org.apiman.client.domain.clients.ClientStatus;

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
public class ClientVersionSummary implements Serializable {

    private String organizationId;
    private String organizationName;
    private String id;
    private String name;
    private String description;
    private ClientStatus status;
    private String version;
    private String apiKey;
}
