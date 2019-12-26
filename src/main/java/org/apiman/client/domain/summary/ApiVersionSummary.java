package org.apiman.client.domain.summary;

import java.io.Serializable;

import org.apiman.client.domain.apis.ApiStatus;
import org.apiman.client.domain.apis.UpdateApi;

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
public class ApiVersionSummary implements Serializable {

    private String organizationId;
    private String organizationName;
    private String id;
    private String name;
    private String description;
    private ApiStatus status;
    private String version;
    private boolean publicAPI;
}
