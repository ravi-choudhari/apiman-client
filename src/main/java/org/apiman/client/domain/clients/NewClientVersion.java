package org.apiman.client.domain.clients;

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
public class NewClientVersion {

    private String version;
    private boolean clone;
    private String cloneVersion;
    private String apiKey;
}
