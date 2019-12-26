package org.apiman.client.domain.clients;

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
public class NewClientVersion implements Serializable {

    private String version;
    private boolean clone;
    private String cloneVersion;
    private String apiKey;
}
