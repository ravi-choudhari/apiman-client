package org.apiman.client.domain.system;

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
public class SystemStatus implements Serializable {

    private String id;
    private String name;
    private String description;
    private String moreInfo;
    private String version;
    private String builtOn;
    private boolean up;
}
